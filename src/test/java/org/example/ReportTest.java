package org.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ReportTest {
    Program javaProgram = ProgramFactory.createJavaProgram();
    Student testStudent = StudentFactory.createStudent("Name Surname", javaProgram, LocalDate.of(2024,5,6));
    Report report = new Report(testStudent);

    @ParameterizedTest
    @CsvSource({
            "2024-05-06, 2024-05-14",
            "2024-01-01, 2024-01-09",
            "2024-12-30, 2025-01-07"
    })
    void estimatedFinishDateTest(String startDateStr, String expectedResultDateStr) {
        Student testStudent = StudentFactory.createStudent("Name Surname", javaProgram, LocalDate.parse(startDateStr));
        Report report = new Report(testStudent);
        LocalDate actualResult = report.estimatedFinishDate(javaProgram.sumOfHours());
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
    void convertHoursToDaysAndHoursTest() {
//        report.
    }
}