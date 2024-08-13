package prevproject;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.Serializable;

public class Food implements Serializable {
    private int res_id;

    private int id;
    private String food_category;
    private String food_name;
    private double food_price;

    //private Button buttonAddCart;

    public Food(int res_id, String food_category, String food_name, double food_price) {
        this.res_id = res_id;
        this.food_category = food_category;
        this.food_name = food_name;
        this.food_price = food_price;
        this.id =  res_id;
        //this.buttonAddCart = new Button("Add");
    }

//    public Button getButtonAddCart() {
//        return buttonAddCart;
//    }

    public int getId() {
        return id;
    }
//    public void setRes_id( int id ){
//        this.res_id = id;
//    }
//    /////// getter functions ////////////
//
//    public int getRes_id() {
//        return res_id;
//    }
//
//    public String getFood_category() {
//        return food_category;
//    }
//
//    public String getFood_name() {
//        return food_name;
//    }
//
//    public double getFood_price() {
//        return food_price;
//    }


    public int getRes_id() {
        return res_id;
    }
    public void setRes_id(int res_id) {
        this.res_id = res_id;
    }

    public String getFood_category() {
        return food_category;
    }

    public void setFood_category(String food_category) {
        this.food_category = food_category;
    }

    public String getFood_name() {
        return food_name;
    }

    public void setFood_name(String food_name) {
        this.food_name = food_name;
    }

    public double getFood_price() {
        return food_price;
    }

    public void setFood_price(double food_price) {
        this.food_price = food_price;
    }
//

    //////////// show functions  //////////
    public void showFoodDetails() {
        //System.out.println(res_id + "," + food_category + "," + food_name + "," + food_price);
        System.out.println();
        System.out.println( "Food Name : "+food_name);
        System.out.println( "Restaurant ID : "+ res_id);
        System.out.println( "Category : "+ food_category);
        System.out.println( "Price : "+ food_price);

    }


}