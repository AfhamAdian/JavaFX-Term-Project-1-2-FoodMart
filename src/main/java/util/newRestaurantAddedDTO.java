package util;

import prevproject.Restaurant;

public class newRestaurantAddedDTO {

    private Restaurant restaurant;

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public newRestaurantAddedDTO(Restaurant res)
    {
        this.restaurant = res;
    }
}
