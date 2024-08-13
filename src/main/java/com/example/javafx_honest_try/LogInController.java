package com.example.javafx_honest_try;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;

public class LogInController {

    public Main main;
    @FXML
    private TextField userText;
    @FXML
    private TextField passwordtextfield;
    @FXML
    private Button loginButton;
    @FXML
    private Button resetButton;

    public void setMain(Main main) {
        this.main = main;
    }

    public void loginAction(ActionEvent actionEvent) throws IOException, InterruptedException {
        String resName = userText.getText();
        //ain.setTemp(resName);
        main.setResName(resName);

        String pass = passwordtextfield.getText();

        if(!pass.equalsIgnoreCase("111")){
            main.showAlert();
            userText.setText(null);
            passwordtextfield.setText(null);
            return;
        }


        //if((resName.equalsIgnoreCase("kfc")||resName.equalsIgnoreCase("ihop")||resName.equalsIgnoreCase("mcdonalds")||resName.equalsIgnoreCase("starbucks"))) {

            main.connectToServer(resName);                  ////////// UPDATE ////////
            main.setTemp(resName);

            Thread.sleep(100);
            if(main.clientRestaurant.restaurantManager == null){
                System.out.println("porlo dhora");
            }
            else {
                System.out.println("all data loaded after login action");
                //main.clientRestaurant.restaurantManager.showAllRestaurantDetails();
            }
            System.out.println(resName + " connected To Server" + main.getResName());
            synchronized (this) {
                main.showHomePageRestaurant();
            }
            //main.showHomePageRestaurant();
        //}
//        else{
//            main.showAlert();
//            userText.setText(null);
//        }
    }
    public void resetLogin(ActionEvent actionEvent) {
        userText.setText(null);
    }


}
