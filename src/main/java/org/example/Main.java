package org.example;


import java.time.LocalDate;

class Main {
    public static void main(String[] args) {
        Student firstStudent = StudentFactory.createStudent("Ivanov Ivan", ProgramRunner.javaProgramSet(), LocalDate.of(2024, 4, 25));
        Student secondStudent = StudentFactory.createStudent("Sidorov Ivan", ProgramRunner.qaProgramSet(), LocalDate.of(2024, 4, 20));
        Report rep = new Report(firstStudent);

        rep.generateReport(1);
        rep.generateReport();

    }

}


