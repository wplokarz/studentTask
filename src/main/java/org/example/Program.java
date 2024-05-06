package org.example;

import java.util.HashMap;

class Program {

    private final String name;

    Program(String name) {
        this.name = name;
    }
    private final HashMap<String, Integer> course = new HashMap<>();

    public void addClass(String name, Integer duration) {
        this.course.put(name, duration);
    }

    public HashMap<String, Integer> getClasses() {
        return this.course;
    }

    public Integer sumOfHours() {
        return this.course.values().stream().reduce(0, Integer::sum);
    }

    public String getProgramName() {
        return this.name;
    }
}


