package com.example.javafx_honest_try;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import prevproject.Food;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;


public class Cus_HomePageController {


    @FXML
    private Button buttonSearchRes;
    @FXML
    private Button buttonSearchFood;
    @FXML
    private Button buttonOrder;

    public MainCustomer main;

    public void setMain(MainCustomer main) {
        this.main = main;
    }

    public void searchResAction(ActionEvent actionEvent) throws IOException {
        main.showSearchResPage();
    }


//    public void logOutAction(ActionEvent actionEvent) throws IOException {
//        main.clientRestaurant.restaurantManager.output_restaurant("NewRestaurant.txt");
//        main.clientRestaurant.restaurantManager.output_menu("NewMenu.txt");
//        System.out.println("Logged out and data saved");
//        main.showAlert("Press OK to logout");
//        Platform.exit();
//    }
}
