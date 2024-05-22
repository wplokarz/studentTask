package org.example.writereadstudents;

import org.example.moduls.Student;

import java.io.*;
import java.util.List;

public class WriterStudent {
    private final FileOutputStream fileOutputStream;
    private final ObjectOutputStream objectOutputStream;

    private final List<Student> students;

    public WriterStudent(List<Student> students) throws IOException {
        fileOutputStream = new FileOutputStream("students.txt");
        objectOutputStream = new ObjectOutputStream(fileOutputStream);
        this.students = students;
    }

    public void saveStudentsToFile() throws IOException {
        try {
            for (Student stu:students) {
                objectOutputStream.writeObject(stu);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        objectOutputStream.close();
        fileOutputStream.close();
    }
}

