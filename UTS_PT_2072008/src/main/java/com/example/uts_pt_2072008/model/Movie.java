package com.example.uts_pt_2072008.model;

public class Movie {
    int idMovie;
    String title;
    String genre;
    int durasi;

    public Movie(int idMovie, String title, String genre, int durasi) {
        this.idMovie = idMovie;
        this.title = title;
        this.genre = genre;
        this.durasi = durasi;
    }

    public int getIdMovie() {
        return idMovie;
    }

    public void setIdMovie(int idMovie) {
        this.idMovie = idMovie;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getDurasi() {
        return durasi;
    }

    public void setDurasi(int durasi) {
        this.durasi = durasi;
    }

    @Override
    public String toString() {
        return title;
    }
}
