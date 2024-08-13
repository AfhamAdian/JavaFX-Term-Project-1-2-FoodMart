package util;

import prevproject.RestaurantManager;

import java.io.Serializable;

public class DataToCustomerDTO implements Serializable {
    public RestaurantManager restaurantManager;
    static int i = 0;
    public DataToCustomerDTO( RestaurantManager restaurantManager ) {
        this.restaurantManager = restaurantManager;
        i++;
    }
}
