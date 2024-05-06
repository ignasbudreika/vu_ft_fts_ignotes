package com.github.ignasbudreika.ignotes.model;

import androidx.annotation.NonNull;

import java.util.UUID;

public class Note {

    private String id;

    private String name;

    private String content;

    public Note(String name, String content) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.content = content;
    }

    public Note(String id, String name, String content) {
        this.id = id;
        this.name = name;
        this.content = content;
    }

    public String getId() {
        return this.id;
    }

    @NonNull
    @Override
    public String toString() {
        return this.name;
    }

    public String toDisplay() {
        return this.name + ": " + this.content;
    }
}
