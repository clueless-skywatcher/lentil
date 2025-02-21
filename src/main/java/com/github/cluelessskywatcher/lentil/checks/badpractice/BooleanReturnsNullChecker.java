package com.github.cluelessskywatcher.lentil.checks.badpractice;

import java.text.MessageFormat;

import org.apache.commons.lang3.tuple.ImmutablePair;

import com.github.cluelessskywatcher.lentil.checks.AbstractChecker;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.stmt.ReturnStmt;

public class BooleanReturnsNullChecker extends AbstractChecker {
    public BooleanReturnsNullChecker() {
        issueCode = "BP0001";
        messageFormat = "{0}: Bad practice! Boolean method is returning null";
        description = "A method that should return a boolean is returning a null value. Null values can cause NullPointerException if not handled properly";
    }

    @Override
    public void check(CompilationUnit cu) {
        cu.findAll(MethodDeclaration.class).forEach(
            method -> {
                if (method.getTypeAsString().equals("Boolean")){
                    method.findAll(ReturnStmt.class).forEach(
                        stmt -> {
                            if (stmt.getExpression().get().toString().equals("null")){
                                ImmutablePair<Integer, String> report = new ImmutablePair<Integer,String>(
                                    stmt.getBegin().get().line, 
                                    MessageFormat.format(
                                        messageFormat,
                                        issueCode
                                    )
                                );
                                reports.add(report);
                            }
                        }
                    );
                }
            }
        );
    }
}
