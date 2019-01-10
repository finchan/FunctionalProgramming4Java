package com.xavier.learning.defaults;

public interface Carriage {
    default String rock() {
        return "... from side to side.";
    }
}
