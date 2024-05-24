package org.example;

import org.example.data.StudentsList;
import org.example.models.Student;
import org.example.reporting.ReportGenerator;
import org.example.utilis.writereadstudents.ReaderStudent;
import org.example.utilis.writereadstudents.WriterStudent;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class ReportRunner {

    public static List<Student> getStudentList() throws IOException, ClassNotFoundException {
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
        List<Student> students = getStudentList();
        for (Student student: students) {
            ReportGenerator reportGenerator = new ReportGenerator(student);
            reportGenerator.printReport();
            reportGenerator.printReport(1);
        }
    }
}
