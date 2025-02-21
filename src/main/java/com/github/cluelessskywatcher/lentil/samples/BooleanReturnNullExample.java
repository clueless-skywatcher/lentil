package com.github.cluelessskywatcher.lentil.samples;

public class BooleanReturnNullExample {
    public boolean abcd(){
        System.out.println("Not okay");
        if (1 == 1){
            return true;
        } else {
            return null;
        }
    }

    public Boolean abcd2(){
        System.out.println("Not okay");
        if (1 == 2){
            return true;
        } else {
            return null;
        }
    }

    public boolean defg(){
        int a;
        int b;
        if (a + b == a + b) {
            return false;
        }
        System.out.println("Okay");
        return true;
    }
}
