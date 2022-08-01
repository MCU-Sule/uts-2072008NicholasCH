package com.example.uts_pt_2072008.controller;

import com.example.uts_pt_2072008.HelloApplication;
import com.example.uts_pt_2072008.dao.movieDao;
import com.example.uts_pt_2072008.dao.userDao;
import com.example.uts_pt_2072008.dao.watchlistDao;
import com.example.uts_pt_2072008.model.Movie;
import com.example.uts_pt_2072008.model.User;
import com.example.uts_pt_2072008.util.utilConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

import java.io.IOException;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

public class mainController {
    @FXML
    public TableColumn colTitle;
    @FXML
    public TableColumn colGenre;
    @FXML
    public TableColumn colDur;
    @FXML
    public TableColumn colTitleML;
    @FXML
    public TableColumn colLastML;
    @FXML
    public TableColumn colFavML;
    ObservableList<Movie> movieList;
    ObservableList<User> userList;
    @FXML
    public ComboBox cmbGenre;
    @FXML
    public ListView lvUser;
    @FXML
    public TableView table1;
    @FXML
    public TableView table2;
    movieDao movDao = new movieDao();
    userDao userDao = new userDao();
    watchlistDao wlDao = new watchlistDao();
    ObservableList<String> filterData;
    User selectedItem;
    Stage stage;
    Scene scene;
    FXMLLoader fxmlLoader;

    public void  initialize() throws IOException {
        fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("UTSSecondPage.fxml"));
        scene = new Scene(fxmlLoader.load(), 350, 170);
        stage = new Stage();
        stage.setTitle("Add New User");
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);

        filterData = FXCollections.observableArrayList(
                "All",
                "Action",
                "Musical",
                "Comedy",
                "Animated",
                "Fantasy",
                "Drama",
                "Mystery",
                "Thriller",
                "Horror"
        );
        cmbGenre.setItems(filterData);
        cmbGenre.getSelectionModel().select(0);
        fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("UTSSecondPage.fxml"));
        tampilan();
    }

    public void tampilan(){
        movieList = movDao.getData();
        userList = userDao.getData();
        table1.setItems(movieList);
        colTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        colGenre.setCellValueFactory(new PropertyValueFactory<>("genre"));
        colDur.setCellValueFactory(new PropertyValueFactory<>("durasi"));
        lvUser.setItems(userList);
    }

    public void changeCombo(ActionEvent actionEvent) {
        movieList.clear();
        if (cmbGenre.getValue().equals("All")) {
            table1.setItems(movDao.getData());
        }else {
            table1.setItems(movDao.filterData((String) cmbGenre.getValue()));
        }
    }

    public void AddUserAction(ActionEvent actionEvent){
        stage.showAndWait();
        tampilan();
    }

    public void DelUserAction(ActionEvent actionEvent) {
        selectedItem = (User) lvUser.getSelectionModel().getSelectedItem();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure want to delete this selected data?", ButtonType.OK, ButtonType.CANCEL);
        alert.showAndWait();
        if (alert.getResult() == ButtonType.OK) {
            userDao.delData(selectedItem);
        }
        tampilan();
    }

    public void printReport(ActionEvent actionEvent) {
        JasperPrint jp;
        Map param = new HashMap();
        Connection conn = utilConnection.getConnection();
        try {
            jp = JasperFillManager.fillReport("report/Report_UTS_2072008.jasper", param, conn);
            JasperViewer jv = new JasperViewer(jp, false);
            jv.setTitle("Laporan Movies");
            jv.setVisible(true);
        } catch (JRException e) {
            throw new RuntimeException(e);
        }
    }

    public void filteredData(){
        selectedItem = (User) lvUser.getSelectionModel().getSelectedItem();
        table2.setItems(wlDao.filterData(selectedItem));
        colTitleML.setCellValueFactory(new PropertyValueFactory<>("movieId"));
        colLastML.setCellValueFactory(new PropertyValueFactory<>("DurasiLW"));
        colFavML.setCellValueFactory(new PropertyValueFactory<>("Favorit"));
    }
}
