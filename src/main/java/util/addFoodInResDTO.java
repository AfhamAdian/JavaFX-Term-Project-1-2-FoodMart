package util;

import prevproject.Food;

import java.io.Serializable;

public class addFoodInResDTO implements Serializable {

    public Food food;
    public String resName;
    static int i = 0;
     public addFoodInResDTO(Food food, String resName ) {
        this.food = food;
        this.resName = resName;
        i++;
    }
}
