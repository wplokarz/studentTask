package org.example;

import java.util.ArrayList;
import java.util.List;

public class WorkingTime {

    private static final List<String> days_to_exclude = List.of("SATURDAY", "SUNDAY");
    public static List<String> EXCLUDED_DAYS = new ArrayList<>(days_to_exclude);
    public static Integer START_HOUR = 10;
    public static Integer END_HOUR = 18;
}
