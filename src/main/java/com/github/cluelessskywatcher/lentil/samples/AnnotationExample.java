package com.github.cluelessskywatcher.lentil.samples;

import javax.annotation.Nullable;

@SuppressWarnings(value = "abcd") // OK
public class AnnotationExample {
    @Deprecated public void abcd(){ // Violation
        @Nullable // OK
        String anno;
        
        @Nullable int abc; // Violation
    }

    @Deprecated // OK
    public void abcd2(@Nullable String xyz){ // Still OK since it is a param annotation
    }
}

@SuppressWarnings(value = "abcd") class ABCD {

}
