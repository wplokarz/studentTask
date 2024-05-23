package org.example.utilis.writereadstudents;

import org.example.models.Student;

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
            for (Student student:students) {
                objectOutputStream.writeObject(student);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        objectOutputStream.close();
        fileOutputStream.close();
    }
}

