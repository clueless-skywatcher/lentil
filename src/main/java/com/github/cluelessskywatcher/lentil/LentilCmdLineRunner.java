package com.github.cluelessskywatcher.lentil;

import java.text.MessageFormat;

import com.github.cluelessskywatcher.lentil.report.LentilRunner;

import picocli.CommandLine;

public class LentilCmdLineRunner implements Runnable {
    @CommandLine.Parameters(index = "0", description = "File to execute the runner on")
    private String filePath;

    @CommandLine.Option(names = {"-c", "--config"}, description = "Configuration file for the checker. By default it is analyzer-config.json")
    private String configFilePath;

    @Override
    public void run() {
        // TODO Auto-generated method stub
        try {
            LentilRunner runner;
            if (configFilePath != null){
                runner = new LentilRunner(filePath, configFilePath);
            }
            else {
                runner = new LentilRunner(filePath);
            }
            System.out.println(MessageFormat.format("{0} issues found", runner.getReports().size()));
            for (String report : runner.getReports()){
                System.out.println(report);
            }
        } 
        catch (Exception e){
            e.printStackTrace();
        }           
    }    
}
