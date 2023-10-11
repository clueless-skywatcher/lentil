package com.github.cluelessskywatcher.lentil.samples;

public class UnderscoreNameExample {
    int sampleVar1 = 5;
    boolean sampleVar2;

    String sample_var_3; // Violation

    public void abc(){
        int abc_def = 1; // Violation
    }

    public void abc_def_func(){ // Violation
        int aaaDef = 1;
    }
}

class Under_score_class { // Violation
    private static final String STRING_CONSTANT = "ABC";
    static String STATIC_CONSTANT; // Violation
    final String FINAL_CONSTANT = "2"; // Violation
}

interface Under_score_interface { // Violation

}

enum GoodEnum {
    ABC_DEF,
    ABC_GHI
}

enum Bad_enum { // Violation
    ABC_DEF,
    ABC_GHI
}
