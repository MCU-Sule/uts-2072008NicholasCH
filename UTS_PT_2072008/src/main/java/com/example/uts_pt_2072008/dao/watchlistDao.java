package com.example.uts_pt_2072008.dao;

import com.example.uts_pt_2072008.model.Movie;
import com.example.uts_pt_2072008.model.User;
import com.example.uts_pt_2072008.model.Watchlist;
import com.example.uts_pt_2072008.util.utilConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class watchlistDao implements interfaceDao<Watchlist>{
    Connection conn = utilConnection.getConnection();
    @Override
    public ObservableList<Watchlist> getData() {
        ObservableList<Watchlist> wList;
        wList = FXCollections.observableArrayList();
        String querry = "SELECT * FROM watchlist w LEFT JOIN movie m ON m.idMovie = w.Movie_idMovie LEFT JOIN user u ON u.idUser = w.User_idUser;";
        PreparedStatement ps;
        try {
            ps = conn.prepareStatement(querry);
            ResultSet hasilWL = ps.executeQuery();
            while(hasilWL.next()){
                int idWatch = hasilWL.getInt("idWatchList");
                int last = hasilWL.getInt("LastWatch");
                int fav = hasilWL.getInt("Favorite");
                int idMovie = hasilWL.getInt("idMovie");
                String title = hasilWL.getString("Title");
                String genre = hasilWL.getString("Genre");
                int durasi = hasilWL.getInt("Durasi");
                int idUser = hasilWL.getInt("idUser");
                String userName = hasilWL.getString("UserName");
                String userPass = hasilWL.getString("UserPassword");
                Movie mov = new Movie(idMovie, title, genre, durasi);
                User user = new User(idUser, userName, userPass);
                Watchlist wl = new Watchlist(idWatch, last, fav, mov, user);
                wList.add(wl);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return wList;
    }

    @Override
    public int addData(Watchlist data) {
        String querry = "INSERT INTO watchlist(idWatchList, LastWatch, Favorite, Movie_idMovie, User_idUser) VALUES(?, ?, ?, ?, ?)";
        PreparedStatement ps;
        int hasil;
        try {
            ps = conn.prepareStatement(querry);
            ps.setInt(1, data.getLastWatch());
            ps.setInt(2, data.getFavorite());
            ps.setInt(3, data.getMovieId().getIdMovie());
            ps.setInt(4, data.getUserId().getIdUser());
            ps.setInt(5, data.getIdWatchList());
            hasil = ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }

    @Override
    public int upData(Watchlist data) {
        String query = "UPDATE watchlist SET LastWatch = ?, Favorite = ?, Movie_idMovie = ?, User_idUser = ? WHERE idWatchList = ?;";
        PreparedStatement ps;
        int hasil;
        try {
            ps = conn.prepareStatement(query);
            ps.setInt(1, data.getLastWatch());
            ps.setInt(2, data.getFavorite());
            ps.setInt(3, data.getMovieId().getIdMovie());
            ps.setInt(4, data.getUserId().getIdUser());
            ps.setInt(5, data.getIdWatchList());
            hasil = ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return hasil;
    }

    @Override
    public int delData(Watchlist data) {
        String querry = "DELETE FROM watchlist WHERE id = ?;";
        PreparedStatement ps;
        int hasil;
        try {
            ps = conn.prepareStatement(querry);
            ps.setInt(1, data.getIdWatchList());
            hasil = ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return hasil;
    }

    public  ObservableList<Watchlist> filterData(User data){
        ObservableList<Watchlist> wList = FXCollections.observableArrayList();
        String querry = "SELECT * FROM watchlist w LEFT JOIN movie m ON m.idMovie = w.Movie_idMovie LEFT JOIN user u ON u.idUser = w.User_idUser WHERE w.User_idUser = ?;";
        PreparedStatement ps;
        try {
            ps = conn.prepareStatement(querry);
            ps.setInt(1, data.getIdUser());
            ResultSet hasilFilter = ps.executeQuery();
            while(hasilFilter.next()){
                int idWatch = hasilFilter.getInt("idWatchList");
                int last = hasilFilter.getInt("LastWatch");
                int fav = hasilFilter.getInt("Favorite");
                int idMovie = hasilFilter.getInt("idMovie");
                String title = hasilFilter.getString("Title");
                String genre = hasilFilter.getString("Genre");
                int durasi = hasilFilter.getInt("Durasi");
                int idUser = hasilFilter.getInt("idUser");
                String userName = hasilFilter.getString("UserName");
                String userPass = hasilFilter.getString("UserPassword");
                Movie mov = new Movie(idMovie, title, genre, durasi);
                User user = new User(idUser, userName, userPass);
                Watchlist wl = new Watchlist(idWatch, last, fav, mov, user);
                wList.add(wl);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return wList;
    }
}
