package com.github.cluelessskywatcher.lentil.utilities;

import java.io.FileReader;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.github.cluelessskywatcher.lentil.AnalyzerConfig;

public class ConfigJSONReader {
    public static AnalyzerConfig readConfig(String filePath) throws Exception{
        Object obj = new JSONParser().parse(new FileReader(filePath));
        JSONObject json = (JSONObject) obj;

        Map<?, ?> analyzer = (Map<?, ?>) json.get("analyzer");
        JSONArray array = (JSONArray) analyzer.get("checks");

        AnalyzerConfig config = new AnalyzerConfig();

        for (int i = 0; i < array.size(); i++){
            JSONObject arrayAtI = (JSONObject) array.get(i);
            if (arrayAtI.get("name") != null){
                String name = arrayAtI.get("name").toString();
                config.addClass(CheckerMaps.CHECKERS_FROM_NAME.get(name));
            }
            else if (arrayAtI.get("code") != null){
                String code = arrayAtI.get("code").toString();
                config.addClass(CheckerMaps.CHECKERS_FROM_CODE.get(code));
            }
            else {
                throw new Exception("Check configuration must contain either name of check or it's code");
            }
        }
        
        return config;
    }

    public static AnalyzerConfig readConfig() throws Exception{
        return readConfig("analyzer-config.json");
    }
}
