package org.example.tests.reporting;

import org.example.data.ProgramsList;
import org.example.factories.StudentFactory;
import org.example.models.Program;
import org.example.models.Student;
import org.example.reporting.ReportGenerator;
import org.example.utilis.timecalculations.CalculateWorkingTime;
import org.example.utilis.timecalculations.EstimatedFinishDate;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static com.github.stefanbirkner.systemlambda.SystemLambda.tapSystemOut;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ReportGeneratorTest {

    Program javaProgram = new ProgramsList().getJavaProgram();
    Student testStudent = StudentFactory.createStudent("Name Surname", javaProgram, LocalDate.of(2024,5,6));


    @Test
    void generateShortReportTest() throws Exception {
        ReportGenerator reportGenerator = new ReportGenerator(testStudent);
        LocalDateTime now = LocalDateTime.now();
        String nowTimeString = now.getDayOfMonth() + " " + now.getMonth() + " " + now.getYear() + ", " + now.getDayOfWeek() + ", " + now.getHour() + ":" + now.getMinute();
        String expectedResult = "Generating report date - " + nowTimeString +
                "\nName Surname (JAVA Developer) - " + new CalculateWorkingTime().calculateRemainingTrainingTime(testStudent);
        String actualResult = tapSystemOut(reportGenerator::printReport);
        assertEquals(expectedResult, actualResult.trim());
    }

    @Test
    void generateLongReportTest() throws Exception {
        ReportGenerator reportGenerator = new ReportGenerator(testStudent);
        String expectedResult = "STUDENT: Name Surname\nWORKING TIME: from 10 to 18\nCURRICULUM: JAVA Developer\nCOURSE:56 hours\nJAVA \t16\nJDBC \t24\nSpring \t16\nSTART DATE: 2024-05-06\nEND DATE: "
                + EstimatedFinishDate.calculateCourseFinishDate(testStudent) + "\n" + new CalculateWorkingTime().calculateRemainingTrainingTime(testStudent);
        String actualResult = tapSystemOut(()->{
            reportGenerator.printReport(1);
        });
        assertEquals(expectedResult, actualResult.trim());
    }
}