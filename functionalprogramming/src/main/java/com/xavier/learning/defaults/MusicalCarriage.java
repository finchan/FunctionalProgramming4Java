package com.xavier.learning.defaults;

public class MusicalCarriage implements Carriage, Jukebox {
    @Override
    public String rock() {
        return Carriage.super.rock();
    }
}
