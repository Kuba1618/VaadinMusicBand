package com.example.application.views.song.dedication;

public class Dedication {

    public String title;
    public String category;
    public String description;
    public String author;

    public Dedication() {
    }

    public Dedication(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public Dedication(String title, String category, String description, String author) {
        this.title = title;
        this.category = category;
        this.description = description;
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
