package org.example;

import java.time.LocalDate;
import java.util.ArrayList;

public class StudentsList {

    static Student firstStudent = StudentFactory.createStudent("Ivanov Ivan", ProgramRunner.javaProgramSet(), LocalDate.of(2024, 4, 25));
    static Student secondStudent = StudentFactory.createStudent("Sidorov Ivan", ProgramRunner.qaProgramSet(), LocalDate.of(2024, 4, 20));

    public static ArrayList<Student> createStudentList() {
        ArrayList<Student> students = new ArrayList<>();
        students.add(firstStudent);
        students.add(secondStudent);
        return students;
    }

}
