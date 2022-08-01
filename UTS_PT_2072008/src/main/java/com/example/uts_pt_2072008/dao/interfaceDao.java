package com.example.uts_pt_2072008.dao;

import javafx.collections.ObservableList;

public interface interfaceDao<T> {
    ObservableList<T> getData();
    int addData(T data);
    int upData(T data);
    int delData(T data);

}
