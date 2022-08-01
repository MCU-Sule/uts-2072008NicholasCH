package com.example.uts_pt_2072008.dao;

import com.example.uts_pt_2072008.model.User;
import com.example.uts_pt_2072008.util.utilConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class userDao implements interfaceDao<User>{
    Connection conn = utilConnection.getConnection();

    @Override
    public ObservableList<User> getData() {
        ObservableList<User> userList;
        userList = FXCollections.observableArrayList();
        String querry = "SELECT * FROM user;";
        PreparedStatement ps;
        try {
            ps = conn.prepareStatement(querry);
            ResultSet hasilUser = ps.executeQuery();
            while (hasilUser.next()){
                int id = hasilUser.getInt("idUser");
                String nama = hasilUser.getString("UserName");
                String pass = hasilUser.getString("UserPassword");
                User user = new User(id, nama, pass);
                userList.add(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return userList;
    }

    @Override
    public int addData(User data) {
        String querry = "INSERT INTO user(idUser, UserName, UserPassword) VALUES(?, ?, ?);";
        PreparedStatement ps;
        int hasil;
        try {
            ps = conn.prepareStatement(querry);
            ps.setInt(1, data.getIdUser());
            ps.setString(2, data.getUserName());
            ps.setString(3, data.getUserPass());
            hasil = ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return hasil;
    }

    @Override
    public int upData(User data) {
        String querry = "UPDATE user SET UserName = ?, UserPassword = ? WHERE idUser = ?;";
        PreparedStatement ps;
        int hasil;
        try {
            ps = conn.prepareStatement(querry);
            ps.setString(1, data.getUserName());
            ps.setString(2, data.getUserPass());
            ps.setInt(3, data.getIdUser());
            hasil = ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return hasil;
    }

    @Override
    public int delData(User data) {
        String querry = "DELETE FROM user WHERE idUser = ?;";
        PreparedStatement ps;
        int hasil;
        try {
            ps = conn.prepareStatement(querry);
            ps.setInt(1, data.getIdUser());
            hasil = ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return hasil;
    }
}
