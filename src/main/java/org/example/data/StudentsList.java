package org.example.data;

import org.example.factories.StudentFactory;
import org.example.models.Student;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class StudentsList {

    static Student firstStudent = StudentFactory.createStudent("Ivanov Ivan", new ProgramsList().getJavaProgram(), LocalDate.of(2024, 4, 25));
    static Student secondStudent = StudentFactory.createStudent("Sidorov Ivan", new ProgramsList().getQaProgram(), LocalDate.of(2024, 4, 20));

    public static List<Student> createStudentList() {
        List<Student> students = new ArrayList<>();
        students.add(firstStudent);
        students.add(secondStudent);
        return students;
    }

}
