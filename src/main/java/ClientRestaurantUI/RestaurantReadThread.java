package ClientRestaurantUI;

import Server.ClientRestaurant;
import javafx.beans.value.ObservableNumberValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import prevproject.Food;
import prevproject.Restaurant;
import prevproject.RestaurantManager;
import util.OrderedFoodDTO;
import util.SocketWrapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RestaurantReadThread implements Runnable {
    private Thread thr;
    private SocketWrapper socketWrapper;

    public Restaurant restaurant;

    public RestaurantManager restaurantManager;
    public ClientRestaurantUI.ClientRestaurant clientRestaurant;
    public ObservableList<Food> orderedFoodList = FXCollections.observableArrayList();

    public RestaurantReadThread( SocketWrapper socketWrapper,ClientRestaurantUI.ClientRestaurant clientRestaurant)
    {
        this.clientRestaurant = clientRestaurant;
        this.thr = new Thread(this,"Restaurant Reading Thread");
        this.socketWrapper = socketWrapper;
        //orderedFoodList ;
        thr.start();
    }

    @Override
    public void run()
    {
        try {
        while( true ) {
            ///////////////////////////Thread reading which object is sent////////////////////////
            //////////////////// gets all info of restaurant from server to client////////////////
            try {
                Object o = socketWrapper.read();
                synchronized (this){
                    if (o instanceof Restaurant)
                    {
                        this.restaurant = (Restaurant) o;
                        System.out.println("Restaurant "+ restaurant.getName()+" Data received");
                        restaurant.showRestaurantDetails();
                    }
                    else if ( o instanceof RestaurantManager)
                    {
                        this.restaurantManager = (RestaurantManager) o;
                        clientRestaurant.restaurantManager = this.restaurantManager;
                        System.out.println("Restaurant manager Data received");
                    }
                    else if ( o instanceof OrderedFoodDTO orderedFoodDTO)
                    {
                        //this.orderedFoodList = orderedFoodDTO.getFoodList();
                        orderedFoodList.addAll(FXCollections.observableArrayList(orderedFoodDTO.getFoodList()));

                        System.out.println( "Order received in "+ orderedFoodDTO.getResName());

                        for ( var temp : orderedFoodList){
                            System.out.println(temp.getFood_name()+ "  " + temp.getFood_price());
                        }
                    }
                }
            }catch( Exception e) {
                System.out.println(e);
            }
        }
    } finally {
            try {
                socketWrapper.closeConnection();
                System.out.println("Restaurant client closed");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
