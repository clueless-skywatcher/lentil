package com.github.cluelessskywatcher.lentil.checks.annotations;

import java.text.MessageFormat;

import org.apache.commons.lang3.tuple.ImmutablePair;

import com.github.cluelessskywatcher.lentil.checks.AbstractChecker;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.expr.VariableDeclarationExpr;

public class AnnotationsOnSameLineChecker extends AbstractChecker {

    public AnnotationsOnSameLineChecker(){
        issueCode = "A0001";
        messageFormat = "Annotation @{0} present on the same line as declaration {1}";
        description = "Annotations should preferably be used on a different line as their targets";
    }

    @Override
    public void check(CompilationUnit cu) {
        cu.findAll(ClassOrInterfaceDeclaration.class).forEach(
            classDecl -> {
                classDecl.getAnnotations().forEach(
                    anno -> {
                        if (classDecl.getName().getBegin().get().line 
                            == anno.getName().getBegin().get().line){
                            reports.add(new ImmutablePair<Integer,String>(
                                classDecl.getBegin().get().line,
                                MessageFormat.format(
                                    messageFormat,
                                    anno.getNameAsString(),
                                    classDecl.getNameAsString()
                                )
                            ));
                        }
                    }
                );
            }
        );

        cu.findAll(MethodDeclaration.class).forEach(
            methodDecl -> {
                methodDecl.getAnnotations().forEach(
                    anno -> {
                        if (methodDecl.getName().getBegin().get().line 
                            == anno.getName().getBegin().get().line){
                            reports.add(new ImmutablePair<Integer,String>(
                                methodDecl.getBegin().get().line,
                                MessageFormat.format(
                                    messageFormat,
                                    anno.getNameAsString(),
                                    methodDecl.getNameAsString()
                                )
                            ));
                        }
                    }
                );
            }
        );

        cu.findAll(VariableDeclarationExpr.class).forEach(
            varDecl -> {
                varDecl.getAnnotations().forEach(
                    anno -> {
                        varDecl.getVariables().forEach(
                            var -> {
                                if (var.getName().getBegin().get().line 
                                    == anno.getName().getBegin().get().line){
                                    reports.add(new ImmutablePair<Integer,String>(
                                        var.getBegin().get().line,
                                        MessageFormat.format(
                                            messageFormat,
                                            anno.getNameAsString(),
                                            var.getNameAsString()
                                        )
                                    ));
                                }
                            }
                        );
                    }
                );
            }
        );
    }
    
}
