package com.github.cluelessskywatcher.lentil.checks;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.tuple.ImmutablePair;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import lombok.Getter;

@Getter
public abstract class AbstractChecker extends VoidVisitorAdapter<Set<String>> {
    protected Set<ImmutablePair<Integer, String>> reports = new HashSet<>();
    protected String description;
    protected String issueCode;
    protected String messageFormat;

    public abstract void check(CompilationUnit cu);
}
