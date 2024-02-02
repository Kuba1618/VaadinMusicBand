package com.example.application.data;

public class Song {

    public String title;
    public String description;
    public String category;
    public String url;

    public Song(String title, String description, String category, String url) {
        this.title = title;
        this.description = description;
        this.category = category;
        this.url = url;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

}
