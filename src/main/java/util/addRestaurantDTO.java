package util;

import prevproject.RestaurantManager;

import java.io.Serializable;

public class addRestaurantDTO implements Serializable {

    public RestaurantManager restaurantManager;
    static int i = 0;
    public addRestaurantDTO( RestaurantManager restaurantManager) {
        this.restaurantManager = restaurantManager;
        i++;
    }
}
