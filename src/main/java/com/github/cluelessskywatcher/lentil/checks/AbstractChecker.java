package com.github.cluelessskywatcher.lentil.checks;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.tuple.ImmutablePair;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import lombok.Getter;

@Getter
public abstract class AbstractChecker extends VoidVisitorAdapter<List<String>> {
    protected List<ImmutablePair<Integer, String>> reports = new ArrayList<>();
    protected String description;
    protected String issueCode;
    protected String messageFormat;

    public abstract void check(CompilationUnit cu);
}
