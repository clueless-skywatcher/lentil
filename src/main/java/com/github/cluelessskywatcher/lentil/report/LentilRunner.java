package com.github.cluelessskywatcher.lentil.report;

import java.io.File;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.tuple.ImmutablePair;

import com.github.cluelessskywatcher.lentil.AnalyzerConfig;
import com.github.cluelessskywatcher.lentil.checks.AbstractChecker;
import com.github.cluelessskywatcher.lentil.utilities.ConfigJSONReader;
import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;

public class LentilRunner {
    private AnalyzerConfig config;
    private CompilationUnit compilationUnit;
    private List<ImmutablePair<Integer, String>> reports;

    public LentilRunner(String sourcePath, String configPath) throws Exception {
        File source = new File(sourcePath);
        JavaParser parser = new JavaParser();
        compilationUnit = parser.parse(source).getResult().get();

        config = ConfigJSONReader.readConfig(configPath);
        reports = new ArrayList<>();
    }

    public LentilRunner(String sourcePath) throws Exception{
        this(sourcePath, "analyzer-config.json");
    }

    public List<String> getReports() throws Exception {
        for (Class checkerClass : config.getCheckerClasses()){
            AbstractChecker checker = (AbstractChecker) checkerClass.getDeclaredConstructor().newInstance();
            checker.check(compilationUnit);
            reports.addAll(checker.getReports());
        }

        reports.sort((a1, a2) -> {
            return a1.left.compareTo(a2.left);
        });

        List<String> finalReports = new ArrayList<>();
        reports.forEach(report -> {
            finalReports.add(MessageFormat.format("Line {0}: {1}", report.left, report.right));
        });

        return finalReports;
    }
}
