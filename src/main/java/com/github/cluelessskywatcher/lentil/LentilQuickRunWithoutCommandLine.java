package com.github.cluelessskywatcher.lentil;

import java.text.MessageFormat;

import com.github.cluelessskywatcher.lentil.report.LentilRunner;

public class LentilQuickRunWithoutCommandLine {
    public static void main(String[] args) throws Exception {
        final String filePath = "src/main/java/com/github/cluelessskywatcher/lentil/samples/AnnotationExample.java";
        LentilRunner runner;
        runner = new LentilRunner(filePath);
        System.out.println(MessageFormat.format("{0} issues found", runner.getReports().size()));
        for (String report : runner.getReports()){
            System.out.println(report);
        }
    }
}
