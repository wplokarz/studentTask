package org.example.timecalculations;
import org.example.moduls.Student;

import java.time.LocalDate;

public class EstimatedFinishDate {
    public static LocalDate calculateCourseFinishDate(Student student) {
        int courseDuration = student.getProgram().sumOfHours();
        LocalDate finishDate = student.getStartDate();
        float daysPassed = 0f;

        // loop over days in course
        while (daysPassed < (float) courseDuration /8) {
            if (!CalculateWorkingTime.getExcludedDays().contains(finishDate.getDayOfWeek().toString())) {
                daysPassed++;
            }
            finishDate = finishDate.plusDays(1);
        }

        // after last iteration of the loop Date is always one day ahead than it should so one day has to be subtracted
        return finishDate.minusDays(1);
    }
}
