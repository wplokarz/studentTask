package org.example;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class ReportRunner {


    public static ArrayList<Student> getStudentList() throws IOException, ClassNotFoundException {
        File file = new File("students.txt");
        if (file.exists()) {
            ReaderStudent reader = new ReaderStudent();
            return reader.getStudents();
        }
        else {
            WriterStudent writerStudent = new WriterStudent(StudentsList.createStudentList());
            writerStudent.saveStudentsToFile();
            ReaderStudent reader = new ReaderStudent();
            return reader.getStudents();
        }
    }
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        ArrayList<Student> students = getStudentList();
        for (Student student: students) {
            Report report = new Report(student);
            report.generateReport();
            report.generateReport(1);

        }

    }
}
