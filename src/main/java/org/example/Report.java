package org.example;

import java.time.*;

public class Report {

    private final Student student;

    public Report(Student student) {

        this.student = student;
    }

    public void generateReport() {
        LocalDateTime now = LocalDateTime.now();
        String nowTimeString = now.getDayOfMonth() + " " + now.getMonth() + " " + now.getYear() + ", " + now.getDayOfWeek() + ", " + now.getHour() + ":" + now.getMinute();
        System.out.format("Generating report date - %s\n", nowTimeString);
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

        // LocalDateTime variable with date as course start date and time specified in WorkingTime class
        LocalDateTime courseStartDateTime = LocalDateTime.of(this.student.getStartDate(), startTime);

        // variable to store how many working hours are between now and certain dateTime
        int hoursToFinishCourseOrAfterFinishCourse = calculateWorkingHoursBetweenNowAndCertainDateTime(courseStartDateTime);

        // if more working hours passed since course start date than now return message that training is completed and how much time passed
        if (hoursToFinishCourseOrAfterFinishCourse > this.student.courseDuration()) {
            int timePast = hoursToFinishCourseOrAfterFinishCourse - this.student.courseDuration();
            String timePastInDaysAndHours = convertHoursToDaysAndHours(timePast);
            return String.format("Training completed. %shave passed since the end.", timePastInDaysAndHours);
        }

        // if working hours passed since course start is equal to course duration then print that training's been just completed
        else if (hoursToFinishCourseOrAfterFinishCourse == this.student.courseDuration()) {
            return "Training completed minutes ago.";
        }

        // if less working hours passed since course start than course duration time
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
        LocalDate finishDate = this.student.getStartDate();
        float daysPassed = 0f;

        // get how may working days is required to finish course
        while (daysPassed < (float) courseDuration /8) {
            if (!WorkingTime.EXCLUDED_DAYS.contains(finishDate.getDayOfWeek().toString())) {
                daysPassed++;
            }
            finishDate = finishDate.plusDays(1);
        }

        // after last iteration of the loop Date is always one day ahead than it should so one day has to be subtracted
        return finishDate.minusDays(1);
    }

    private static int calculateWorkingHoursBetweenNowAndCertainDateTime(LocalDateTime startDate) {
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
