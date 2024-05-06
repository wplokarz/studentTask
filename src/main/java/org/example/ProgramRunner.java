package org.example;

public class ProgramRunner {

    public static Program javaProgramSet() {
        Program javaProgram = new Program("JAVA Developer");
        javaProgram.addClass("JAVA", 16);
        javaProgram.addClass("JDBC", 24);
        javaProgram.addClass("Spring", 16);
        return javaProgram;
    }

    public static Program qaProgramSet() {
        Program qaProgram = new Program("AQA");
        qaProgram.addClass("Test design", 10);
        qaProgram.addClass("Page object", 16);
        qaProgram.addClass("Selenium", 16);
        return qaProgram;
    }

}
