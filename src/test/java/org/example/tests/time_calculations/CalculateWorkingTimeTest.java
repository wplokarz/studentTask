package org.example.tests.time_calculations;

import org.example.data.ProgramsList;
import org.example.moduls.Program;
import org.example.moduls.Student;
import org.example.factories.StudentFactory;
import org.example.timecalculations.CalculateWorkingTime;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class CalculateWorkingTimeTest {

    Program javaProgram = new ProgramsList().getJavaProgram();

    @Test
    void calculateRemainingTrainingTime() {
        LocalDate startDate = LocalDate.of(2024,5,1);
        LocalDateTime startDateWithTime = LocalDateTime.of(startDate, LocalTime.of(10,0));
        Student testStudent = StudentFactory.createStudent("Name Surname", javaProgram, startDate);
        String actualResult = new CalculateWorkingTime().calculateRemainingTrainingTime(testStudent);

        int workingHoursSinceStartOfTheCourse =  new CalculateWorkingTime().calculateWorkingHoursBetweenNowAndCertainDateTime(startDateWithTime) - javaProgram.sumOfHours();
        String daysAndHoursPassedSinceCourseEnd = new CalculateWorkingTime().convertHoursToDaysAndHours(workingHoursSinceStartOfTheCourse);
        String expectedResult = "Training completed. " + daysAndHoursPassedSinceCourseEnd + "have passed since the end.";
        assertEquals(expectedResult, actualResult);
    }
}