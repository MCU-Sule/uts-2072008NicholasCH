package com.example.uts_pt_2072008.model;

public class Watchlist {
    int idWatchList;
    int lastWatch;
    int favorite;
    Movie movieId;
    User userId;

    public Watchlist(int idWatchList, int lastWatch, int favorite, Movie movieId, User userId) {
        this.idWatchList = idWatchList;
        this.lastWatch = lastWatch;
        this.favorite = favorite;
        this.movieId = movieId;
        this.userId = userId;
    }

    public int getIdWatchList() {
        return idWatchList;
    }

    public void setIdWatchList(int idWatchList) {
        this.idWatchList = idWatchList;
    }

    public int getLastWatch() {
        return lastWatch;
    }

    public void setLastWatch(int lastWatch) {
        this.lastWatch = lastWatch;
    }

    public int getFavorite() {
        return favorite;
    }

    public void setFavorite(int favorite) {
        this.favorite = favorite;
    }

    public Movie getMovieId() {
        return movieId;
    }

    public void setMovieId(Movie movieId) {
        this.movieId = movieId;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    public String getFavorit(){
        String fav;
        if(favorite == 0){
            fav = "False";
        } else {
            fav = "True";
        }
        return fav;
    }

    public String getDurasiLW(){
        String dur = String.valueOf(lastWatch) + '/' + String.valueOf(movieId.getDurasi());
        return dur;
    }

    @Override
    public String toString() {
        return "Watchlist{" +
                "idWatchList=" + idWatchList +
                ", lastWatch=" + lastWatch +
                ", favorite=" + favorite +
                ", movieId=" + movieId +
                ", userId=" + userId +
                '}';
    }
}
