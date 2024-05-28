package org.example.utilis.timecalculations;

import org.example.models.Student;
import org.example.utilis.dateprovider.CurrentDateTimeProvider;
import org.example.utilis.dateprovider.SystemCurrentDateTimeProvider;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public class CalculateWorkingTime {

    private static final Integer START_HOUR = 10;
    private static final Integer END_HOUR = 18;

    private static final List<String> EXCLUDED_DAYS = List.of("SATURDAY", "SUNDAY");

    private static final String trainingCompleted = "Training completed.";

    private static final String trainingJustFinished = "Training completed minutes ago.";

    private static final String trainingInProgress = "Training is not finished.";

    private final CurrentDateTimeProvider currentDateTimeProvider;

    public CalculateWorkingTime(CurrentDateTimeProvider currentDateTimeProvider) {
        this.currentDateTimeProvider = currentDateTimeProvider;
    }

    public CalculateWorkingTime() {
        this(new SystemCurrentDateTimeProvider());
    }

    public String calculateRemainingTrainingTime(Student student) {
        LocalTime startTime = LocalTime.of(START_HOUR,0);

        // LocalDateTime variable with date as course start date and time specified in WorkingTime class
        LocalDateTime courseStartDateTime = LocalDateTime.of(student.getStartDate(), startTime);

        // variable to store how many working hours are between now and certain dateTime
        int hoursToFinishCourseOrAfterFinishCourse = calculateWorkingHoursBetweenNowAndCertainDateTime(courseStartDateTime);

        // if more working hours passed since course start date than now return message that training is completed and how much time passed
        if (hoursToFinishCourseOrAfterFinishCourse > student.courseDuration()) {
            int timePast = hoursToFinishCourseOrAfterFinishCourse - student.courseDuration();
            String timePastInDaysAndHours = convertHoursToDaysAndHours(timePast);
            return String.format("%s %s have passed since the end.", trainingCompleted,timePastInDaysAndHours);
        }

        // if working hours passed since course start is equal to course duration then print that training's been just completed
        else if (hoursToFinishCourseOrAfterFinishCourse == student.courseDuration()) {
            return trainingJustFinished;
        }

        // if less working hours passed since course start than course duration time
        else {
            int timePast = student.courseDuration() - hoursToFinishCourseOrAfterFinishCourse;
            String timePastInDaysAndHours = convertHoursToDaysAndHours(timePast);
            return String.format("%s %s are left until the end.", trainingInProgress,timePastInDaysAndHours);
        }
    }

    public String convertHoursToDaysAndHours(int timePast) {
        if (timePast <= 0) throw new IllegalArgumentException("Invalid argument");
        if (timePast > 8) {
            return timePast % 8 != 0 ? timePast / 8 + " d " + timePast % 8 + " hours" : timePast / 8 + " d ";
        }
        else {
            return timePast + " hours";
        }
    }

    public int calculateWorkingHoursBetweenNowAndCertainDateTime(LocalDateTime startDate) {
        LocalDateTime now = currentDateTimeProvider.getCurrentDateTime();
        int workingHours = 0;

        while (startDate.isBefore(now)) {
            // Check if the current day is not a weekend
            if (!EXCLUDED_DAYS.contains(startDate.getDayOfWeek().toString())) {

                // Check if the current time is within working hours (10:00 to 18:00)
                if (startDate.toLocalTime().isAfter(LocalTime.of(START_HOUR, 0)) &&
                        startDate.toLocalTime().isBefore(LocalTime.of(END_HOUR, 1))) {
                    workingHours++;
                }
            }
            // Move to the next hour
            startDate = startDate.plusHours(1);
        }
        return workingHours;
    }

    public static int getStartHour() {
        return START_HOUR;
    }

    public static int getFinishHour() {
        return END_HOUR;
    }

    public static List<String> getExcludedDays() {
        return EXCLUDED_DAYS;
    }
}
