package org.example.models;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Program implements Serializable {

    private final String name;

    public Program(String name) {
        this.name = name;
    }
    protected Map<String, Integer> course = new HashMap<>();

    public void addClass(String name, Integer duration) {
        course.put(name, duration);
    }

    public Map<String, Integer> getClasses() {
        return course;
    }

    public Integer sumOfHours() {
        return course.values().stream().reduce(0, Integer::sum);
    }

    public String getProgramName() {
        return name;
    }
}


