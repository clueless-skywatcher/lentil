package com.github.cluelessskywatcher.lentil.utilities;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class SourceReader {
    public static List<String> readSource(String filePath) throws FileNotFoundException{
        List<String> lines = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(filePath));

        try {
            String line = reader.readLine();

            while (line != null){
                lines.add(line);
                line = reader.readLine();
            }

            reader.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return lines;
    }
}
