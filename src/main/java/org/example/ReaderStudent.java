package org.example;

import java.io.*;
import java.util.ArrayList;

public class ReaderStudent {
    private final ObjectInputStream o1;
    private final ArrayList<Student> students = new ArrayList<>();


    public ReaderStudent() throws IOException {
        FileInputStream f1 = new FileInputStream("students.txt");
        o1 = new ObjectInputStream(f1);
    }

    public void createStudentsList() throws IOException, ClassNotFoundException {
        try {
            while (true) {
                Student student = (Student) o1.readObject();
                this.students.add(student);
            }
        }
        catch (EOFException e) {
        }
    }

    public ArrayList<Student> getStudents() throws IOException, ClassNotFoundException {
        this.createStudentsList();
        return this.students;
    }
}

