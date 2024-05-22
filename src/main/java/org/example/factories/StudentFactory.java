package org.example.factories;

import org.example.moduls.Program;
import org.example.moduls.Student;

import java.time.LocalDate;

public class StudentFactory {

    public static Student createStudent(String name, Program program, LocalDate startDate) {
        return new Student(name, program, startDate);
    }
}
