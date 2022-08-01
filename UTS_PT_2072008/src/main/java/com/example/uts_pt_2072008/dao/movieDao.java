package com.example.uts_pt_2072008.dao;

import com.example.uts_pt_2072008.model.Movie;
import com.example.uts_pt_2072008.util.utilConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class movieDao implements interfaceDao<Movie>{
    Connection conn = utilConnection.getConnection();
    @Override
    public ObservableList<Movie> getData() {
        ObservableList<Movie> movList;
        movList = FXCollections.observableArrayList();
        String querry = "SELECT  * FROM movie;";
        PreparedStatement ps;
        try {
            ps = conn.prepareStatement(querry);
            ResultSet hasilMov = ps.executeQuery();
            while (hasilMov.next()){
                int idMov = hasilMov.getInt("idMovie");
                String title = hasilMov.getString("Title");
                String genre = hasilMov.getString("Genre");
                int  durasi = hasilMov.getInt("Durasi");
                Movie mov = new Movie(idMov, title, genre, durasi);
                movList.add(mov);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return movList;
    }

    @Override
    public int addData(Movie data) {
        String querry = "INSERT INTO movie (idMovie, Title, Genre, Durasi) VALUES (?, ?, ?, ?);";
        PreparedStatement ps;
        int hasil;
        try {
            ps = conn.prepareStatement(querry);
            ps.setInt(1, data.getIdMovie());
            ps.setString(2, data.getTitle());
            ps.setString(3, data.getGenre());
            ps.setInt(4, data.getDurasi());
            hasil = ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return hasil;
    }

    @Override
    public int upData(Movie data) {
        String querry = "UPDATE movie SET Title = ?, Genre = ?, Durasi = ? WHERE idMovie = ?;";
        PreparedStatement ps;
        int hasil;
        try {
            ps = conn.prepareStatement(querry);
            ps.setString(1, data.getTitle());
            ps.setString(2, data.getGenre());
            ps.setInt(3, data.getDurasi());
            ps.setInt(4, data.getIdMovie());
            hasil = ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return hasil;
    }

    @Override
    public int delData(Movie data) {
        String querry = "DELETE FROM movie WHERE idMovie = ?;";
        PreparedStatement ps;
        int hasil;
        try {
            ps = conn.prepareStatement(querry);
            ps.setInt(1, data.getIdMovie());
            hasil = ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return hasil;
    }

    public ObservableList<Movie> filterData(String data){
        ObservableList<Movie> movList;
        movList = FXCollections.observableArrayList();
        String querry = "SELECT * FROM Movie WHERE Genre LIKE '%' ? '%';";
        PreparedStatement ps;
        try {
            ps = conn.prepareStatement(querry);
            ps.setString(1, data);
            ResultSet hasilFil = ps.executeQuery();
            while (hasilFil.next()){
                int id = hasilFil.getInt("idMovie");
                String title = hasilFil.getString("Title");
                String genre = hasilFil.getString("Genre");
                int durasi = hasilFil.getInt("Durasi");
                Movie mov = new Movie(id, title, genre, durasi);
                movList.add(mov);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return movList;
    }
}
