package prevproject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Restaurant implements Serializable {

    private int id;
    private String name;
    private double score;
    private String price;
    private int zip;
    private String[] categories;
    private List<Food> foodsInRestaurant = new ArrayList<>();
    public String categoriesString;

    public String getCategoriesString() {
        return categoriesString;
    }

    public void setCategoriesString(String categoriesString) {
        this.categoriesString = categoriesString;
    }

    public Restaurant(int id, String name, double score, String price, int zip, String[] categories) {
        this.id = id;
        this.name = name;
        this.zip = zip;
        this.score = score;
        this.price = price;
        this.categories = categories;
        this.categoriesString = categories[0]+","+categories[1]+","+categories[2];
    }
    public Restaurant(int id, String name, double score, String price, int zip) {
        this.id = id;
        this.name = name;
        this.zip = zip;
        this.score = score;
        this.price = price;
        this.categories = new String[]{"1", "2","3"};
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getZip() {
        return zip;
    }

    public void setZip(int zip) {
        this.zip = zip;
    }

    public String[] getCategories() {
        return categories;
    }

    public void setCategories(String[] categories) {
        this.categories = categories;
    }

    public List<Food> getFoodsInRestaurant() {
        return foodsInRestaurant;
    }

    public void setFoodsInRestaurant(List<Food> foodsInRestaurant) {
        this.foodsInRestaurant = foodsInRestaurant;
    }

    /*************************** Getter Functions **************************/
//    public int getId() {
//        return id;
//    }
//    public String getName() {
//        return name;
//    }
//    public int getZip() {
//        return zip;
//    }
//    public double getScore() {
//        return score;
//    }
//    public String getPrice() {
//        return price;
//    }
//    public String[] getCategories() {
//        return categories;
//    }

    public List<Food> getCostliestFood()
    {
        List<Food> temp = new ArrayList<>();
        double max = 0;
        for (Food food : foodsInRestaurant) {
            if(food.getFood_price() > max ) max = food.getFood_price();
        }
        for (Food food : foodsInRestaurant) {
            if(food.getFood_price() == max ) temp.add( food);
        }
        return temp;
    }

    public List<Food> getCategoryFood( String category )
    {
        List<Food> temp = new ArrayList<>();
        for (Food food : foodsInRestaurant ) {
            if(food.getFood_category().toLowerCase().contains( category )) {
                temp.add( food );
            }
        }
        return temp;
    }

    public  List<Food> getFoodInPrice ( double lower, double upper )
    {
        List<Food> temp_food = new ArrayList<>();

        for( Food food : foodsInRestaurant ) {
            if( food.getFood_price() >= lower && food.getFood_price() <= upper) {
                temp_food.add(food);
            }
        }
        //if (temp_food.isEmpty()) return null;
        return temp_food;
    }

    public int addFoodHelper ( Food food )
    {
        for (Food temp_food : foodsInRestaurant ) {
            if( temp_food.getFood_name().equalsIgnoreCase(food.getFood_name()) ) {
                if( temp_food.getFood_category().equalsIgnoreCase(food.getFood_category()) ) {
                    return 1;                              /** Food already exists, cannot be added **/
                }
            }
        }
        food.setRes_id( id );
        foodsInRestaurant.add( food );
        return 0;                                          /** Food added in foodsInRestaurant LISt, return 0 **/
    }
    public int itemInRestaurant(){
        return foodsInRestaurant.size();
    }

    /*************************** setter Functions **************************/
    public void addFoodToRestaurant ( Food temp_food ) {
        foodsInRestaurant.add(temp_food);
    }


    /*************************** Print Functions **************************/
    public void showRestaurantDetails()
    {
        //System.out.println(id+","+name+","+zip+","+score+","+price+","+categories[0]+","+categories[1]+","+categories[2]);
        System.out.println();
        System.out.println( "Restaurant Name : "+name);
        System.out.println( "ID : "+ id);
        System.out.println( "Zip Code : "+ zip);
        System.out.println( "Score : "+ score);
        System.out.println( "Price : "+ price);
        if(categories[2].isEmpty())System.out.println( "Categories : "+ categories[0]+","+categories[1]);
        else if(categories[1].isEmpty())System.out.println( "Categories : "+ categories[0]);
        else System.out.println( "Categories : "+ categories[0]+","+categories[1]+","+categories[2]) ;

    }

    public List<Food> searchFoodByName( String name) {
        List<Food> tempo_list = new ArrayList<>();
        for( var temp : foodsInRestaurant ){
            if(temp.getFood_name().toLowerCase().contains(name)){
                tempo_list.add( temp );
            }
        }
        return tempo_list;
    }

    public void showRestaurantDetailsWithFood() {
        System.out.println(id+","+name+","+zip+","+score+","+price+","+categories[0]+","+categories[1]+","+categories[2]);
        for ( Food temp : foodsInRestaurant ) {
            temp.showFoodDetails();
        }
        System.out.println("\n");
    }


}
