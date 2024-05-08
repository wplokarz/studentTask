package org.example;

import java.time.*;

public class Report {

    private final Student student;

    public Report(Student student) {

        this.student = student;
    }

    public void generateReport() {
        System.out.println(this.student.getName() + " (" + this.student.curriculum.getProgramName() + ") - " + calculateRemainingTrainingTime());
    }

    public void generateReport(Integer param) {
        if (param == 0) {
            generateReport();
        }
        else {
            System.out.println("STUDENT: " + this.student.getName());
            System.out.println("WORKING TIME: from " + WorkingTime.START_HOUR + " to " + WorkingTime.END_HOUR);
            System.out.println("CURRICULUM: " + this.student.curriculum.getProgramName());
            System.out.println("COURSE:" + this.student.curriculum.sumOfHours() + " hours");
            for (String key: this.student.curriculum.getClasses().keySet()) {
                System.out.println(key + " \t" +this.student.curriculum.getClasses().get(key));
            }
            System.out.println("START DATE: " + this.student.getStartDate());
            System.out.println("END DATE: " + estimatedFinishDate(this.student.curriculum.sumOfHours()));
            System.out.println(calculateRemainingTrainingTime());
        }
    }

    public String calculateRemainingTrainingTime() {
        LocalTime startTime = LocalTime.of(WorkingTime.START_HOUR,0);
        LocalDateTime course_start = LocalDateTime.of(this.student.getStartDate(), startTime);
        int hoursToFinishCourseOrAfterFinishCourse = calculateWorkingHours(course_start);
        if (hoursToFinishCourseOrAfterFinishCourse > this.student.courseDuration()) {
            int timePast = hoursToFinishCourseOrAfterFinishCourse - this.student.courseDuration();
            String timePastInDaysAndHours = convertHoursToDaysAndHours(timePast);
            return String.format("Training completed. %shave passed since the end.", timePastInDaysAndHours);
        }
        else if (hoursToFinishCourseOrAfterFinishCourse == this.student.courseDuration()) {
            return "Training completed minutes ago.";
        }
        else {
            int timePast = this.student.courseDuration() - hoursToFinishCourseOrAfterFinishCourse;
            String timePastInDaysAndHours = convertHoursToDaysAndHours(timePast);
            return String.format("Training is not finished. %sare left until the end.", timePastInDaysAndHours);
        }
    }

    private String convertHoursToDaysAndHours(int timePast) {
        if (timePast > 8) {
            return timePast % 8 != 0 ? timePast / 8 + " d " + timePast % 8 + " hours " : timePast / 8 + " d ";
        }
        else {
            return timePast + " hours ";
        }
    }

    public LocalDate estimatedFinishDate(int courseDuration) {
        LocalTime startTime = LocalTime.of(WorkingTime.START_HOUR,0);
        LocalDateTime course_start = LocalDateTime.of(this.student.getStartDate(), startTime);
        float daysPassed = 0f;
        LocalDate finishDate = LocalDate.of(this.student.getStartDate().getYear(), this.student.getStartDate().getMonth(),this.student.getStartDate().getDayOfMonth());
        // get how may working days is required to finish course
        while (daysPassed < (float) courseDuration /8) {
            if (!WorkingTime.EXCLUDED_DAYS.contains(course_start.getDayOfWeek().toString())) {
                finishDate = finishDate.plusDays(1);
                daysPassed++;
            }
            course_start = course_start.plusDays(1);
        }
        course_start = course_start.minusDays(1);
        return LocalDate.of(course_start.getYear(), course_start.getMonth(), course_start.getDayOfMonth());
    }

    public static int calculateWorkingHours(LocalDateTime startDate) {
        LocalDateTime now = LocalDateTime.now();
        int workingHours = 0;

        while (startDate.isBefore(now)) {
            // Check if the current day is not a weekend
            if (!WorkingTime.EXCLUDED_DAYS.contains(startDate.getDayOfWeek().toString())) {

                // Check if the current time is within working hours (10:00 to 18:00)
                if (startDate.toLocalTime().isAfter(LocalTime.of(WorkingTime.START_HOUR, 0)) &&
                        startDate.toLocalTime().isBefore(LocalTime.of(WorkingTime.END_HOUR, 1))) {
                    workingHours++;
                }
            }
            // Move to the next hour
            startDate = startDate.plusHours(1);
        }
        return workingHours;
    }
}
