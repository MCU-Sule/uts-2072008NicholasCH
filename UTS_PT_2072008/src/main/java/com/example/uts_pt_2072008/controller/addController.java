package com.example.uts_pt_2072008.controller;

import com.example.uts_pt_2072008.dao.userDao;
import com.example.uts_pt_2072008.model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class addController {
    @FXML
    public TextField txtUserName;
    @FXML
    public PasswordField txtPassword;
    userDao userDao = new userDao();
    Alert alert;

    public void submit(ActionEvent actionEvent) {
        int hasil;
        if((txtUserName.getText() == null) || (txtPassword.getText() == null) ){
            hasil = 0;
        } else {
            int id = 0;
            String nama = txtUserName.getText();
            String pass = txtPassword.getText();
            hasil = userDao.addData(new User(id, nama, pass));
        }
        if (hasil > 0){
            alert = new Alert(Alert.AlertType.INFORMATION, "Data successfully added!", ButtonType.OK);
            alert.showAndWait();
            reset();
            txtPassword.getScene().getWindow().hide();
        } else {
            alert = new Alert(Alert.AlertType.ERROR, "Error while adding new data!", ButtonType.OK);
            alert.showAndWait();
        }
    }
    public void reset(){
        txtUserName.clear();
        txtPassword.clear();
    }
}
