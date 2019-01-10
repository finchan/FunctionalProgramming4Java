package com.xavier.learning.defaults;

public class ChildImpl implements Child {
    private String message;
    @Override
    public void message(String body) {
        System.out.println(body);
        this.message = body;
    }

    @Override
    public String getLastMessage() {
        return this.message;
    }
}
