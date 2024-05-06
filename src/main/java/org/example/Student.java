package org.example;

import java.time.LocalDate;

public class Student {

    private final String name;
    protected final Program curriculum;
    private final LocalDate startDate;

    public Student(String name, Program curriculum, LocalDate start_date) {

        this.name = name;
        this.curriculum = curriculum;
        this.startDate = start_date;
    }

    public String getName() {
        return this.name;
    }

    public LocalDate getStartDate() {
        return this.startDate;
    }

    public Integer courseDuration() {
        return this.curriculum.sumOfHours();
    }

}
