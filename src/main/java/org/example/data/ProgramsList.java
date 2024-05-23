package org.example.data;

import org.example.factories.ProgramFactory;
import org.example.models.Program;

import java.util.Map;

public class ProgramsList {

    Map<String, Integer> javaCourses = Map.of("JAVA", 16, "JDBC", 24, "Spring", 16);
    Map<String, Integer> qaCourses = Map.of("Test design", 10, "Page object", 16, "Selenium", 16);
    ProgramFactory factory = new ProgramFactory();
    private final Program javaDeveloper = factory.createProgram("JAVA Developer", javaCourses);

    private final Program qaProgram = factory.createProgram("AQA", qaCourses);

    public Program getJavaProgram() {
        return javaDeveloper;
    }

    public Program getQaProgram() {
        return qaProgram;
    }

}
