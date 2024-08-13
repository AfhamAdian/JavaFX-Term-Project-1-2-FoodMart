package com.example.javafx_honest_try;

import ClientRestaurantUI.ClientRestaurant;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    private Stage stage;
    private String resName;
    public ClientRestaurant clientRestaurant;
    
    
    ///////////////////// Getter Setter Methods ///////////////////////
    public ClientRestaurant getClientRestaurant(){
        return this.clientRestaurant;
    }
    public String getResName(){
        return resName;
    }
    public Stage getStage() {
        return stage;
    };

    public void setTemp( String temp){
        this.resName = temp;
    }

    public void setResName(String name){
        resName = name;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
        showLoginPage();
    }

    public void showLoginPage() throws Exception {
        // XML Loading using FXMLLoader
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("test.fxml"));
        //Parent root = loader.load();

        //Loading the controller
        Parent root = loader.load();
        LogInController controller = loader.getController();
        controller.setMain(this);

        //Set the primary stage
        stage.setTitle("Login");
        stage.setScene(new Scene(root));
        stage.show();
    }
    
    public void showHomePageRestaurant() throws IOException {

        if(clientRestaurant.restaurantManager == null ) {
            //System.out.println("ookkk");
            //Platform.exit();
            showAlert();
            return;
        }

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("HomePageRestaurant.fxml"));

        Parent root = loader.load();
        synchronized (this) {
            Res_HomePageController controller = loader.getController();
            //clientRestaurant.restaurantManager.showAllRestaurantDetails();
            controller.setMain(this);
            controller.changelabel();
        }

        stage.setScene(new Scene(root));
        stage.setTitle("Home");
        stage.show();
    }

    public void showAddFoodPage() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("addFoodRestaurant.fxml"));
        //Parent root = loader.load();

        //Loading the controller
        Parent root = loader.load();
        addFoodController controller = loader.getController();
        controller.setMain(this);

        stage.setTitle("Add Food");
        stage.setScene(new Scene(root));
        stage.show();
    }

    ////////////////// Other methods ///////////////////

    // Connects Restaurant to Server , and transfer data from server to that restaurant
    public void connectToServer(String resName) throws IOException, InterruptedException {
        String serverAddress = "127.0.0.1";
        int serverPort = 12345;
        clientRestaurant = new ClientRestaurant(serverAddress,serverPort,resName);
    }
    
    // Shows alert is not correct
    public void showAlert() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Incorrect Credentials");
        alert.setHeaderText("Incorrect Credentials");
        alert.setContentText("The Restaurant and password you provided is not correct.");
        alert.showAndWait();
    }
    public void showAlert(String text,int usused) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Something is wrong!");
        alert.setContentText(text);
        alert.showAndWait();
    }

    public void showAlert(String contenttext) {
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