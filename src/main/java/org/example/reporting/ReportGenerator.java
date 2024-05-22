package org.example.reporting;

import org.example.moduls.Student;
import org.example.timecalculations.CalculateWorkingTime;
import org.example.timecalculations.EstimatedFinishDate;

import java.time.*;

public class ReportGenerator {

    private final Student student;

    public ReportGenerator(Student student) {
        this.student = student;
    }

    public void print() {
        LocalDateTime now = LocalDateTime.now();
        String nowTimeString = now.getDayOfMonth() + " " + now.getMonth() + " " + now.getYear() + ", " + now.getDayOfWeek() + ", " + now.getHour() + ":" + now.getMinute();
        System.out.format("Generating report date - %s\n", nowTimeString);
        System.out.println(student.getName() + " (" + student.getProgram().getProgramName() + ") - " + new CalculateWorkingTime().calculateRemainingTrainingTime(student));
    }

    public void print(Integer param) {
        if (param == 0) {
            print();
        }
        else {
            System.out.println("STUDENT: " + student.getName());
            System.out.println("WORKING TIME: from " + CalculateWorkingTime.getStartHour() + " to " + CalculateWorkingTime.getFinishHour());
            System.out.println("CURRICULUM: " + student.getProgram().getProgramName());
            System.out.println("COURSE:" + student.getProgram().sumOfHours() + " hours");
            for (String key: student.getProgram().getClasses().keySet()) {
                System.out.println(key + " \t" + student.getProgram().getClasses().get(key));
            }
            System.out.println("START DATE: " + student.getStartDate());
            System.out.println("END DATE: " + EstimatedFinishDate.calculateCourseFinishDate(student));
            System.out.println(new CalculateWorkingTime().calculateRemainingTrainingTime(student));
        }
    }
}
