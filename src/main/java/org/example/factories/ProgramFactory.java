package org.example.factories;

import org.example.moduls.Program;

import java.util.Map;

public class ProgramFactory {

    public Program createProgram(String name, Map<String, Integer> courses) {
        Program program = new Program(name);
        for (Map.Entry<String, Integer> entry: courses.entrySet()) {
            program.addClass(entry.getKey(), entry.getValue());
        }
        return program;
    }
}
