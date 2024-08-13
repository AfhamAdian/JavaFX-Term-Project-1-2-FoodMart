package com.example.javafx_honest_try;

import ClientCustomerUI.ClientCustomer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;

public class MainCustomer extends Application {

    private Stage stage;
    private String customerName;
    public ClientCustomer clientCustomer;


    ///////////////////// Getter Setter Methods ///////////////////////
    public ClientCustomer getClientCustomer(){
        return this.clientCustomer;
    }
    public String getCustomerName(){
        return customerName;
    }
    public void setCustomerName(String temp){
        this.customerName = temp;
    }

    public Stage getStage() {
        return stage;
    };

    public void setTemp( String temp){
        this.customerName = temp;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
        connectToServer();
        clientCustomer.restaurantManager.showAllRestaurantDetails();
        showHomeCustomer();
    }


    public void showHomeCustomer() throws Exception {
        // XML Loading using FXMLLoader
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("HomeCustomer.fxml"));
        //Parent root = loader.load();

        //Loading the controller
        Parent root = loader.load();
        Cus_HomePageController controller = loader.getController();
        controller.setMain(this);

        //Set the primary stage
        stage.setTitle("Home");
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void showSearchResPage() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("SearchRestaurant.fxml"));
        //Parent root = loader.load();

        //Loading the controller
        Parent root = loader.load();
        SearchResController controller = loader.getController();
        controller.setMain(this);

        //Set the primary stage
        stage.setTitle("Search Restaurant");
        stage.setScene(new Scene(root));
        stage.show();

    }


    ////////////////// Other methods ///////////////////

    // Connects Restaurant to Server , and transfer data from server to that restaurant
    public void connectToServer() throws IOException, InterruptedException, ClassNotFoundException {
        String serverAddress = "127.0.0.1";
        int serverPort = 12345;
        clientCustomer = new ClientCustomer(serverAddress,serverPort);
    }

    // Shows alert is not correct
    public void showAlert() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Incorrect Credentials");
        alert.setHeaderText("Incorrect Credentials");
        alert.setContentText("The Restaurant and password you provided is not correct.");
        alert.showAndWait();
    }

    public void showAlert(String text,int unused) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Error");
        alert.setContentText(text);
        alert.showAndWait();
    }

    public void showAlert(String contenttext ) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("CONFIRMATION");
        alert.setHeaderText("Are you sure to logout?");
        alert.setContentText(contenttext);
        alert.showAndWait();
    }



    ///////////////// Main Function ///////////////////////
    public static void main(String[] args) {
        // This will launch the JavaFX application
        launch(args);
    }
}