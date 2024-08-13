package util;

import prevproject.Food;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class OrderedFoodDTO implements Serializable{

    private List<Food> food ;
    private String resName;

    public List<Food> getFoodList() {
        return food;
    }

    public void setFood(List<Food> food) {
        this.food = food;
    }

    public String getResName() {
        return resName;
    }

    public void setResName(String resName) {
        this.resName = resName;
    }

    static int i = 0;
    public OrderedFoodDTO(  List<Food> food,String resName ) {
        this.food = food;
        this.resName = resName;
        i++;
    }
}