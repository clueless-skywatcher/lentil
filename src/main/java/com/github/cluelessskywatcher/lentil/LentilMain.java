package com.github.cluelessskywatcher.lentil;

import com.github.cluelessskywatcher.lentil.report.LentilRunner;

public class LentilMain {
    private static final String FILE_PATH = "src/main/java/com/github/cluelessskywatcher/lentil/samples/Somefile.java";
    public static void main(String[] args) throws Exception{
        LentilRunner runner = new LentilRunner(FILE_PATH);
        for (String report : runner.getReports()){
            System.out.println(report);
        }
    }
}
