package com.xavier.learning.defaults;

public interface Parent {
    void message(String body);
    default void welcome() {
        message("Parent: Hi!");
    }
    String getLastMessage();
}
