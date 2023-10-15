package com.github.cluelessskywatcher.lentil.samples;

import javax.annotation.Nullable;

import com.google.inject.name.Named;

import lombok.Builder.Default;

@SuppressWarnings(value = "abcd") // OK
public class AnnotationOnSameLineExample {
    @Deprecated public void abcd(){ // Violation
        @Nullable // OK
        String anno;
        
        @Nullable int abc; // Violation

        @Nullable
        @Deprecated String xyz1; // Violation
    }

    @Deprecated // OK
    public void abcd2(@Nullable String xyz){ // Still OK since it is a param annotation
    }

    @Nullable
    @Named(value = "xyz") // OK
    String xyz;

    @Nullable
    @Named(value = "xyz") String xyz1; // Violation

    @Nullable @Named(value = "xyz2") String xyz2, xyz3; // Violation
}

@SuppressWarnings(value = "abcd") class ABCD {

}
