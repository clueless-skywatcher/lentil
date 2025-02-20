package com.github.cluelessskywatcher.lentil;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AnalyzerConfig {
    private List<Class<?>> checkerClasses;

    public AnalyzerConfig(){
        checkerClasses = new ArrayList<>();
    }

    public void addClass(String className) throws ClassNotFoundException{
        Class<?> checkerClass = Class.forName(className);
        checkerClasses.add(checkerClass);
    }
}
