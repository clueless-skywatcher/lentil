package com.github.cluelessskywatcher.lentil.checks.code;

import java.text.MessageFormat;

import org.apache.commons.lang3.tuple.ImmutablePair;

import com.github.cluelessskywatcher.lentil.checks.AbstractChecker;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.expr.BinaryExpr;

public class IdenticalExpressionComparisonChecker extends AbstractChecker {
    public IdenticalExpressionComparisonChecker(){
        issueCode = "C0002";
        messageFormat = "Comparing two identical expressions: {0}";
        description = messageFormat;
    }

    private boolean isComparisonOperator(String op) {
        return op.equals("==") || op.equals("<") || op.equals(">")
            || op.equals("!=") || op.equals("<=") || op.equals(">=");
    }

    @Override
    public void check(CompilationUnit cu) {
        cu.findAll(BinaryExpr.class).forEach(
            binaryExp -> {
                if (isComparisonOperator(binaryExp.getOperator().asString())) {
                    if (binaryExp.getLeft().equals(binaryExp.getRight())) {
                        reports.add(new ImmutablePair<Integer,String>(
                            binaryExp.getBegin().get().line,
                            MessageFormat.format(messageFormat, binaryExp.toString())
                        ));
                    }
                }
            }
        );
    }
    
}
