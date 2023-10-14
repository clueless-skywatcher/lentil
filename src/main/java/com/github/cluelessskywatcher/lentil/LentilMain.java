package com.github.cluelessskywatcher.lentil;

import picocli.CommandLine;

public class LentilMain {
    public static void main(String[] args) throws Exception{
        new CommandLine(new LentilCmdLineRunner()).execute(args);
    }
}
