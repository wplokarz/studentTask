package org.example.tests.utilis.timecalculations;

import org.example.data.ProgramsList;
import org.example.factories.StudentFactory;
import org.example.models.Program;
import org.example.models.Student;
import org.example.utilis.timecalculations.CalculateWorkingTime;
import org.example.utilis.dateprovider.CurrentDateTimeProvider;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
        String expectedResult = "Training completed. " + daysAndHoursPassedSinceCourseEnd + " have passed since the end.";
        assertEquals(expectedResult, actualResult);
    }

    @ParameterizedTest
    @CsvSource({
            "20, 2 d 4 hours",
            "100, 12 d 4 hours"
    })
    void convertHoursToDaysAndHoursTest(int timePast, String expectedResult) {
        String actualResult = new CalculateWorkingTime().convertHoursToDaysAndHours(timePast);
        assertEquals(expectedResult, actualResult);
    }

    @ParameterizedTest
    @CsvSource({
            "0",
            "-100"
    })
    void convertHoursToDaysAndHoursRaiseException(int timePast) {
        assertThrows(IllegalArgumentException.class, () -> new CalculateWorkingTime().convertHoursToDaysAndHours(timePast));
    }

    @Test
    void calculateWorkingHoursBetweenNowAndCertainDateTimeTest() {
        CurrentDateTimeProvider mockDateTimeProvider = new CurrentDateTimeProvider() {
            @Override
            public LocalDateTime getCurrentDateTime() {
                return LocalDateTime.of(2024, 5, 10, 18, 1);
            }
        };
        int actualResult = new CalculateWorkingTime(mockDateTimeProvider).calculateWorkingHoursBetweenNowAndCertainDateTime(LocalDateTime.of(2024,5,9,10,0));
        int expectedResult = 16;
        assertEquals(expectedResult, actualResult);
    }
}