package com.xavier.learning.defaults;

public interface Child extends Parent {
    @Override
    default void welcome() {
        message("Child: Hi!");
    }
}
