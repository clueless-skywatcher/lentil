package com.github.cluelessskywatcher.lentil.checks.imports;

import java.text.MessageFormat;
import java.util.List;

import org.apache.commons.lang3.tuple.ImmutablePair;

import com.github.cluelessskywatcher.lentil.checks.AbstractChecker;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.ImportDeclaration;

public class AsteriskImportChecker extends AbstractChecker {

    public AsteriskImportChecker(){
        issueCode = "I0001";
        messageFormat = "{0} - Asterisk import ({1})";
        description = "A particular import is an asterisk import, e.g. import java.util.*";
    }

    @Override
    public void check(CompilationUnit cu) {
        List<ImportDeclaration> imports = cu.getImports();
        for (ImportDeclaration imp : imports){
            if (imp.isAsterisk()){
                ImmutablePair<Integer, String> report = new ImmutablePair<Integer,String>(
                    imp.getBegin().get().line,
                    MessageFormat.format(messageFormat, 
                        issueCode,
                        "\"" + imp.toString().trim() + "\""
                    )
                );
                reports.add(report);
            }
        }
    }
}
