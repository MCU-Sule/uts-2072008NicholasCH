module com.example.uts_pt_2072008 {
    requires javafx.controls;
    requires javafx.fxml;
    requires jasperreports;
    requires java.sql;
    requires mysql.connector.java;


    exports com.example.uts_pt_2072008;
    exports com.example.uts_pt_2072008.dao;
    exports com.example.uts_pt_2072008.util;
    exports com.example.uts_pt_2072008.model;
    exports com.example.uts_pt_2072008.controller;
}