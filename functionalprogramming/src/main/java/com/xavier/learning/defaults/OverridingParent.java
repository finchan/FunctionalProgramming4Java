package com.xavier.learning.defaults;

public class OverridingParent extends ParentImpl {
    @Override
    public void welcome() {
        message("Overriding Parent: Hi!");
    }
}
