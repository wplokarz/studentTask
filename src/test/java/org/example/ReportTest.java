package org.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static com.github.stefanbirkner.systemlambda.SystemLambda.tapSystemOut;
import static org.junit.jupiter.api.Assertions.*;

class ReportTest {
    Program javaProgram = ProgramFactory.createJavaProgram();
    Program qaProgram = ProgramFactory.createQAProgram();
    Student testStudent = StudentFactory.createStudent("Name Surname", javaProgram, LocalDate.of(2024,5,6));
    Report report = new Report(testStudent);

    @ParameterizedTest
    @CsvSource({
            "2024-05-06, 2024-05-14",
            "2024-01-01, 2024-01-09",
            "2024-12-30, 2025-01-07"
    })
    void estimatedFinishDateTestJavaProgram(String startDateStr, String expectedResultDateStr) {
        Student testStudent = StudentFactory.createStudent("Name Surname", javaProgram, LocalDate.parse(startDateStr));
        Report report = new Report(testStudent);
        LocalDate actualResult = report.estimatedFinishDate(javaProgram.sumOfHours());
        LocalDate expectedResult = LocalDate.parse(expectedResultDateStr);
        assertEquals(expectedResult, actualResult);
    }

    @ParameterizedTest
    @CsvSource({
            "2024-05-06, 2024-05-13",
            "2024-01-01, 2024-01-08",
            "2024-12-28, 2025-01-06"
    })
    void estimatedFinishDateTestQAProgram(String startDateStr, String expectedResultDateStr) {
        Student testStudent = StudentFactory.createStudent("Name Surname", qaProgram, LocalDate.parse(startDateStr));
        Report report = new Report(testStudent);
        LocalDate actualResult = report.estimatedFinishDate(qaProgram.sumOfHours());
        LocalDate expectedResult = LocalDate.parse(expectedResultDateStr);
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void calculateRemainingTrainingTimeTest() {
        String expectedResult = "Training completed.";
        String actualResult = report.calculateRemainingTrainingTime().substring(0,19);
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void generateReportTest() throws Exception {
        Report report = new Report(testStudent);
        report.generateReport();
        LocalDateTime now = LocalDateTime.now();
        String nowTimeString = now.getDayOfMonth() + " " + now.getMonth() + " " + now.getYear() + ", " + now.getDayOfWeek() + ", " + now.getHour() + ":" + now.getMinute();
        String expectedResult = "Generating report date - " + nowTimeString +
                "\nName Surname (JAVA Developer) - " + report.calculateRemainingTrainingTime();
        String actualResult = tapSystemOut(report::generateReport);
        assertEquals(expectedResult, actualResult.trim());
    }
}