package org.example.tests.utilis.timecalculations;

import org.example.data.ProgramsList;
import org.example.factories.StudentFactory;
import org.example.models.Program;
import org.example.models.Student;
import org.example.utilis.timecalculations.EstimatedFinishDate;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EstimatedFinishDateTest {

    Program javaProgram = new ProgramsList().getJavaProgram();
    Program qaProgram = new ProgramsList().getQaProgram();


    @ParameterizedTest
    @CsvSource({
            "2024-05-06, 2024-05-14",
            "2024-01-01, 2024-01-09",
            "2024-12-30, 2025-01-07"
    })
    void estimatedFinishDateTestJavaProgram(String startDateStr, String expectedResultDateStr) {
        Student testStudent = StudentFactory.createStudent("Name Surname", javaProgram, LocalDate.parse(startDateStr));
        LocalDate actualResult = EstimatedFinishDate.calculateCourseFinishDate(testStudent);
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
        LocalDate actualResult = EstimatedFinishDate.calculateCourseFinishDate(testStudent);
        LocalDate expectedResult = LocalDate.parse(expectedResultDateStr);
        assertEquals(expectedResult, actualResult);
    }
}