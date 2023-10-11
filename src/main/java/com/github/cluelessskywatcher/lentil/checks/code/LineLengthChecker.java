package com.github.cluelessskywatcher.lentil.checks.code;

import java.io.FileNotFoundException;
import java.text.MessageFormat;
import java.util.List;

import org.apache.commons.lang3.tuple.ImmutablePair;

import com.github.cluelessskywatcher.lentil.checks.AbstractChecker;
import com.github.cluelessskywatcher.lentil.utilities.SourceReader;
import com.github.javaparser.ast.CompilationUnit;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LineLengthChecker extends AbstractChecker {
    private int maxLineLength = 80;

    public LineLengthChecker(){
        issueCode = "C0001";
        messageFormat = "{2} - Line length has more than {0} characters (Line length {1})";
        description = "Line has more characters than recommended by convention";
    }

    @Override
    public void check(CompilationUnit cu){
        try {
            List<String> sourceLines = SourceReader.readSource(
                cu.getStorage().get().getPath().toString());
            for (int i = 0; i < sourceLines.size(); i++){
                if (sourceLines.get(i).length() > maxLineLength){
                    ImmutablePair<Integer, String> report = new ImmutablePair<Integer,String>(
                        i + 1, 
                        MessageFormat.format(
                            messageFormat,
                            maxLineLength, sourceLines.get(i).length(), issueCode
                        )
                    );
                    reports.add(report);
                }
            }
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
