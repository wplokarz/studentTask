package org.example;

import java.io.*;
import java.util.ArrayList;

public class WriterStudent {
    private final FileOutputStream f1;
    private final ObjectOutputStream o1;

    private final ArrayList<Student> students;

    public WriterStudent(ArrayList<Student> students) throws IOException {
        f1 = new FileOutputStream("students.txt");
        o1 = new ObjectOutputStream(f1);
        this.students = students;
    }

    public void saveStudentsToFile() throws IOException {
        try {
            for (Student stu:this.students) {
                o1.writeObject(stu);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        o1.close();
        f1.close();
    }
}

