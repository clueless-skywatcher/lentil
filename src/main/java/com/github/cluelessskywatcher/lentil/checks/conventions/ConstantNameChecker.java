package com.github.cluelessskywatcher.lentil.checks.conventions;

import java.text.MessageFormat;
import java.util.regex.Pattern;

import org.apache.commons.lang3.tuple.ImmutablePair;

import com.github.cluelessskywatcher.lentil.checks.AbstractChecker;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.VariableDeclarator;

public class ConstantNameChecker extends AbstractChecker {
    private final static String UPPER_SNAKE_CASE_PATTERN = "[A-Z]+(_[A-Z]+)*";

    public ConstantNameChecker() {
        issueCode = "N0001";
        messageFormat = "{0}: Col {1}: Final static variable (constant) name ({2}) should be in UPPER_SNAKECASE";
        description = "A final static variable (constant) is declared with a name that is not in UPPER_SNAKECASE";
    }

    @Override
    public void check(CompilationUnit cu) {
        // TODO Auto-generated method stub
        cu.findAll(FieldDeclaration.class).forEach(
            field -> {
                if (field.isStatic()){
                    field.getModifiers().forEach(
                        mod -> {
                            if (mod.toString().contains("final")){
                                for (VariableDeclarator var : field.getVariables()){
                                    if (!Pattern.matches(UPPER_SNAKE_CASE_PATTERN, var.getNameAsString())){
                                        ImmutablePair<Integer, String> report = new ImmutablePair<Integer,String>(
                                            var.getBegin().get().line, 
                                            MessageFormat.format(
                                                messageFormat,
                                                issueCode,
                                                var.getBegin().get().column,
                                                var.getNameAsString()
                                            )
                                        );
                                        reports.add(report);
                                    }
                                }
                            }
                        }
                    );
                }
            }
        );
    }

}
