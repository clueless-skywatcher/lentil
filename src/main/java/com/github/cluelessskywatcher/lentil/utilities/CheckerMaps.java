package com.github.cluelessskywatcher.lentil.utilities;

import java.util.Map;
import java.util.HashMap;

public class CheckerMaps {
    public static final Map<String, String> CHECKERS_FROM_CODE = new HashMap<>();
    public static final Map<String, String> CHECKERS_FROM_NAME = new HashMap<>();
    static {
        // Code checks
        CHECKERS_FROM_CODE.put("C0001", "com.github.cluelessskywatcher.lentil.checks.code.LineLengthChecker");

        // Import checks
        CHECKERS_FROM_CODE.put("I0001", "com.github.cluelessskywatcher.lentil.checks.imports.AsteriskImportChecker");

        // Naming convention checks
        CHECKERS_FROM_CODE.put("N0001", "com.github.cluelessskywatcher.lentil.checks.conventions.ConstantNameChecker");
    }

    static {
        // Code checks
        CHECKERS_FROM_NAME.put("LineLength", "com.github.cluelessskywatcher.lentil.checks.code.LineLengthChecker");

        // Import checks
        CHECKERS_FROM_NAME.put("AsteriskImport", "com.github.cluelessskywatcher.lentil.checks.imports.AsteriskImportChecker");

        // Naming convention checks
        CHECKERS_FROM_NAME.put("ConstantName", "com.github.cluelessskywatcher.lentil.checks.conventions.ConstantNameChecker");
    }
}
