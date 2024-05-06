package org.example;

import java.time.LocalDate;

public class StudentFactory {

    public static Student createStudent(String name, Program program, LocalDate startDate) {
        return new Student(name, program, startDate);
    }
}
