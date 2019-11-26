package com.github.callmewaggs.youngwildfreelive.model;

public enum Category {

    GAME("GAME"),
    SPORT("SPORT"),
    TALK("TALK"),
    EAT("EAT"),
    PET("PET"),
    MUSIC("MUSIC");

    private String title;

    Category(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
