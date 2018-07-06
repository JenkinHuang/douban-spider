package com.hikari.doubanspider.bean;

import java.util.Arrays;

public class Movie {
    private String[] genres;
    private String title;
    private String[] directors;
    private String year;
    private String alt;
    private String id;

    public String[] getGenres() {
        return genres;
    }

    public void setGenres(String[] genres) {
        this.genres = genres;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String[] getDirectors() {
        return directors;
    }

    public void setDirectors(String[] directors) {
        this.directors = directors;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getAlt() {
        return alt;
    }

    public void setAlt(String alt) {
        this.alt = alt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "genres=" + Arrays.toString(genres) +
                ", title='" + title + '\'' +
                ", directors=" + Arrays.toString(directors) +
                ", year='" + year + '\'' +
                ", alt='" + alt + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}
