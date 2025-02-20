package com.github.cluelessskywatcher.lentil.checks.conventions;

import java.text.MessageFormat;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang3.tuple.ImmutablePair;

import com.github.cluelessskywatcher.lentil.checks.AbstractChecker;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.EnumDeclaration;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.VariableDeclarator;

public class UnderscoreNameChecker extends AbstractChecker {
    public UnderscoreNameChecker() {
        issueCode = "C0002";
        messageFormat = "{0}: Col {1}: Name ({2}) contains underscores";
        description = "A non-constant, non-enum-field variable or class has a name with underscores";
    }

    @Override
    public void check(CompilationUnit cu) {
        cu.findAll(MethodDeclaration.class).forEach(
            method -> {
                if (method.getNameAsString().contains("_")){
                    reports.add(
                        new ImmutablePair<Integer,String>(
                            method.getBegin().get().line,
                            MessageFormat.format(
                                messageFormat,
                                issueCode,
                                method.getBegin().get().column,
                                method.getNameAsString()
                            )
                        )
                    );
                }

                method.findAll(VariableDeclarator.class).forEach(
                    var -> {
                        if (var.getNameAsString().contains("_")){
                            reports.add(
                                new ImmutablePair<Integer,String>(
                                    var.getBegin().get().line,
                                    MessageFormat.format(
                                        messageFormat,
                                        issueCode,
                                        var.getBegin().get().column,
                                        var.getNameAsString()
                                    )
                                )
                            );
                        }
                    }
                );
            }
        );

        cu.findAll(FieldDeclaration.class).forEach(
            field -> {
                Set<String> constMods = new HashSet<>(Arrays.asList("final", "static"));
                Set<String> modifiers = new HashSet<>();
                field.getModifiers().forEach(
                    mod -> {
                        modifiers.add(mod.toString().trim());
                    }
                );
                if (!modifiers.containsAll(constMods)){
                    field.getVariables().forEach(
                        var -> {
                            if (var.getNameAsString().contains("_")){
                                reports.add(
                                    new ImmutablePair<Integer,String>(
                                        var.getBegin().get().line,
                                        MessageFormat.format(
                                            messageFormat,
                                            issueCode,
                                            var.getBegin().get().column,
                                            var.getNameAsString()
                                        )
                                    )
                                );
                            }
                        }
                    );
                }
            }
        );

        cu.findAll(ClassOrInterfaceDeclaration.class).forEach(
            className -> {
                if (className.getNameAsString().contains("_")){
                    reports.add(
                        new ImmutablePair<Integer,String>(
                            className.getBegin().get().line,
                            MessageFormat.format(
                                messageFormat,
                                issueCode,
                                className.getBegin().get().column,
                                className.getNameAsString()
                            )
                        )
                    );
                }
            }
        );

        cu.findAll(EnumDeclaration.class).forEach(
            enumName -> {
                if (enumName.getNameAsString().contains("_")){
                    reports.add(
                        new ImmutablePair<Integer,String>(
                            enumName.getBegin().get().line,
                            MessageFormat.format(
                                messageFormat,
                                issueCode,
                                enumName.getBegin().get().column,
                                enumName.getNameAsString()
                            )
                        )
                    );
                }
            }
        );
    }    
}
