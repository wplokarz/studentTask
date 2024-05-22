package org.example.writereadstudents;

import org.example.moduls.Student;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ReaderStudent {
    private final ObjectInputStream objectInputStream;
    private final List<Student> students = new ArrayList<>();


    public ReaderStudent() throws IOException {
        FileInputStream fileInputStream = new FileInputStream("students.txt");
        objectInputStream = new ObjectInputStream(fileInputStream);
    }

    public void createStudentsList() throws IOException, ClassNotFoundException {
        boolean hasNext = true;
        while (hasNext) {
            try {
                Student student = (Student) objectInputStream.readObject();
                students.add(student);
            }
            catch (EOFException e) {
                hasNext = false;
            }
        }
    }

    public List<Student> getStudents() throws IOException, ClassNotFoundException {
        this.createStudentsList();
        return students;
    }
}

