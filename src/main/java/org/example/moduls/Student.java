package org.example.moduls;

import java.io.Serializable;
import java.time.LocalDate;

public class Student implements Serializable {

    private final String name;
    private final Program curriculum;
    private final LocalDate startDate;

    public Student(String name, Program curriculum, LocalDate start_date) {

        this.name = name;
        this.curriculum = curriculum;
        this.startDate = start_date;
    }

    public String getName() {
        return name;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public Integer courseDuration() {
        return curriculum.sumOfHours();
    }

    public Program getProgram() {
        return curriculum;
    }

}
