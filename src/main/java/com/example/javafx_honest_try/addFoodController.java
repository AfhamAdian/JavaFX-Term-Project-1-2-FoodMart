package com.example.javafx_honest_try;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import prevproject.Food;
import util.addFoodInResDTO;

import java.io.IOException;

public class addFoodController {

    public Main main;
    @FXML
    private Label confirmation;
    @FXML
    private Button addButton;
    @FXML
    private TextField categoryName;
    @FXML
    private TextField price;
    @FXML
    private TextField foodname;


    public void setMain(Main main) {
        this.main = main;
    }
    public void foodAdder(ActionEvent actionEvent) throws IOException {

        String foodPrice = price.getText();
        String foodCategoryName = categoryName.getText();
        String foodName = foodname.getText();
        String resName = main.getResName();
        int id = 999;

        for( var temp : main.clientRestaurant.restaurantManager.getRestaurantlist()){
            if( temp.getName().equalsIgnoreCase(resName)){
                id = temp.getId();
            }
        }

        Food foodToAdd = new Food( id,foodCategoryName,foodName,Double.parseDouble(foodPrice));
        int checker = main.clientRestaurant.restaurantManager.addFoodInRes(resName,foodToAdd);
        if( checker == 0 ) {
            writeAddFoodDTO(foodToAdd,resName);
            System.out.println("Food successfully added to menu");
//            ///////////// Food Added to database ////////////////
            main.clientRestaurant.restaurantManager.output_restaurant("NewRestaurant.txt");
            main.clientRestaurant.restaurantManager.output_menu("NewMenu.txt");

            confirmation.setText("Food Successfully Added to Menu!");
            System.out.println("Food data written to menu");
        }
        else if ( checker == -1 ) {
            System.out.println("The Restaurant does not exist");
            confirmation.setText("WHAT!!");
        }
        else if ( checker == 1) {
            System.out.println("Food by that name and category already exists in that restaurant");
            confirmation.setText("Food by that name and category already exists");
            main.showAlert(confirmation.getText(),99);
        }
        System.out.println("Food Added To Restaurant");

    }

    public void writeAddFoodDTO(Food food,String resName) throws IOException
    {
        addFoodInResDTO foodDTO = new addFoodInResDTO(food,resName);
        main.clientRestaurant.socketWrapper.write(foodDTO);
        System.out.println(" addFoodDTO written in server ");
    }

    public void backToHome(ActionEvent actionEvent) throws IOException {
        try {
            main.showHomePageRestaurant();
            System.out.println("Back to homepage");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
