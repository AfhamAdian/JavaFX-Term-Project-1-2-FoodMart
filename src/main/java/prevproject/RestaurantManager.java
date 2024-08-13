package prevproject;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class RestaurantManager implements Serializable {

    private List<Restaurant> restaurantlist;
    private List<Food> foodlist;
    int restaurantAdded;
    int foodAdded;



    public RestaurantManager(){
        restaurantlist = new ArrayList<>();
        foodlist = new ArrayList<>();
        restaurantAdded = 0;
        foodAdded = 0;
    }
    /************************** FILE I/O Functions ****************************
     *********************************************************************/

    public void input_menu ( String menutext ) throws IOException {

        String INPUT_FILE_NAME = menutext;
        BufferedReader br = new BufferedReader(new FileReader(INPUT_FILE_NAME));

        while (true) {
            String line = br.readLine();                        // takes a full line as input
            if (line == null) break;                            // exits the while loop when end of file reached
            //System.out.println(line);
            String[] array = line.split(",", -1);

            int foodId = Integer.parseInt(array[0]);
            String foodCategory = array[1];
            String foodName = array[2];
            double foodPrice = Double.parseDouble(array[3]);

            // Create a Food object using the constructor
            Food temp_food = new Food(foodId, foodCategory, foodName, foodPrice);

            foodAdderFromMenu(temp_food);           /********** Adds food from menu to resturant ********/

            foodlist.add(temp_food);                /********** Adds food in foodlist in manager *********/
            foodAdded++;
        }
        br.close();
    }

    public void input_restaurant( String restauranttext ) throws IOException {

        String INPUT_FILE_NAME = restauranttext;
        //String OUTPUT_FILE_NAME = out;

        //////////////////// reading the file //////////////////////
        BufferedReader br = new BufferedReader(new FileReader(INPUT_FILE_NAME));

        while (true) {
            String line = br.readLine();                              // takes a full line as input
            if (line == null) break;                                  // exits the while loop when end of file reached
            String[] array = line.split(",", -1);          // splits the comma separated word

            String[] temp_cat = {array[5], array[6], array[7]};        // operations to make a Restaurant Object
            int id = Integer.parseInt(array[0]);
            String name = array[1];
            double score = Double.parseDouble(array[2]);
            String price = array[3];
            int zip = Integer.parseInt(array[4]);

            // Create the Restaurant object using the constructor
            Restaurant restaurant = new Restaurant(id, name, score, price, zip, temp_cat);  // creation of  a Restaurant Object
            restaurantlist.add(restaurant);                                                  //added to the list
            restaurantAdded++;
        }
        br.close();
    }

    public void output_restaurant ( String restauranttext ) throws IOException {

        String outputLine = null;
        String OUTPUT_FILE_NAME = restauranttext;
        BufferedWriter bw = new BufferedWriter(new FileWriter(OUTPUT_FILE_NAME));
        for (Restaurant restaurant : restaurantlist ) {

            String [] array = restaurant.getCategories();
            if( restaurant.getCategories().length == 1 ) outputLine = restaurant.getId()+","+restaurant.getName()+","+restaurant.getScore()+","+restaurant.getPrice()+","+restaurant.getZip()+"," + array[0]+","+",";
            else if( restaurant.getCategories().length == 2 ) outputLine = restaurant.getId()+","+restaurant.getName()+","+restaurant.getScore()+","+restaurant.getPrice()+","+restaurant.getZip()+"," + array[0]+","+array[1]+",";
            else if( restaurant.getCategories().length == 3 ) outputLine = restaurant.getId()+","+restaurant.getName()+","+restaurant.getScore()+","+restaurant.getPrice()+","+restaurant.getZip()+"," + array[0]+","+array[1]+","+array[2];

            //System.out.println(outputLine);
            assert outputLine != null;
            bw.write( outputLine );
            bw.write(System.lineSeparator());
        }
        bw.close();
    }
    public void output_menu ( String menu ) throws IOException {

        String outputLine;
        String OUTPUT_FILE_NAME = menu;
        BufferedWriter bw = new BufferedWriter(new FileWriter(OUTPUT_FILE_NAME));
        for (Food food : foodlist ) {
            outputLine = food.getRes_id()+","+food.getFood_category()+","+food.getFood_name()+","+food.getFood_price();

            //System.out.println(outputLine);
            bw.write( outputLine);
            bw.write(System.lineSeparator());
        }
        bw.close();
    }
    /************************** Print Functions ****************************
     *********************************************************************/

    public List<Restaurant>  getRestaurantlist(){
        return restaurantlist;
    }
    public void showAllRestaurantDetails()                   // Gives ALL restaurant details without the food Info
    {
        for (Restaurant temp : restaurantlist) {
            temp.showRestaurantDetails();
        }
    }
    public void showAllRestaurantDetailsWithFood()           // Gives ALL restaurant details WITH ALL the food Info
    {
        for (Restaurant temp : restaurantlist) {
            temp.showRestaurantDetailsWithFood();
        }
    }
    public void showFullMenu()
    {
        for ( Food temp : foodlist ) {
            temp.showFoodDetails();
        }
    }
    public void printRestaurantList(List<Restaurant> temp_resList )     // Prints Details of the Given LIST without
    {                                                                   // food INFO
        for (Restaurant temp_res : temp_resList) {
            temp_res.showRestaurantDetails();
        }
    }

    public void PrintCatWiseRestaurant( List< List<Restaurant> > catwise_res_list, List<String> allCategories)
    {
        List<Restaurant> temp = new ArrayList<>();
        for (int i = 0; i<catwise_res_list.size(); i++)
        {
            System.out.printf(allCategories.get(i)+ " :");
            temp = catwise_res_list.get(i);
            int j = 0;

            for (Restaurant restaurant : temp )
            {
                j++;
                if( j == temp.size()) {
                    System.out.printf(restaurant.getName());
                    continue;
                }
                System.out.printf(restaurant.getName()+",");
            }
            System.out.println("");
        }
    }

    public String getCatWiseRestaurantet( List< List<Restaurant> > catwise_res_list, List<String> allCategories)
    {
        String ans = new String();
        List<Restaurant> temp = new ArrayList<>();
        for (int i = 0; i<catwise_res_list.size(); i++)
        {
            //System.out.printf(allCategories.get(i)+ " :");
            ans += allCategories.get(i)+ " :";
            temp = catwise_res_list.get(i);
            int j = 0;

            for (Restaurant restaurant : temp )
            {
                j++;
                if( j == temp.size()) {
                    //System.out.printf(restaurant.getName());
                    ans += restaurant.getName();
                    continue;
                }
                ans += restaurant.getName()+",";
                //System.out.printf(restaurant.getName()+",");
            }
            ans += "\n";
           // System.out.println("");
        }
        return ans;
    }
    /************************** Searching Functions *********************
     ********************************************************************/
    public void foodAdderFromMenu(Food temp_food)
    {
        int res_id = temp_food.getRes_id();
//        for (Restaurant restaurant : restaurantlist) {
//            if( restaurant.getId() == res_id)
//        }

        if (temp_food == null) {
            // Handle the case where temp_food is null
            return;
        }
        for (Restaurant temp_res: restaurantlist )
        {
            if(temp_res.getId() == res_id) {
                temp_res.addFoodToRestaurant( temp_food );
                return;
            }
        }
    }
    public int addRestaurant( Restaurant restaurant )
    {
        String[] tempo = restaurant.getCategories();       /***returns 0 if added,
     -                                                     -11 if not suitable catagories,1 if Name or Id exists**/
        int check = 0;
        for( int i = 0; i< tempo.length; i++) {
            if( !tempo[i].isEmpty()) check++;
        }
//        if( !tempo[0].isEmpty()) check++;
//        if( !tempo[1].isEmpty()) check++;
//        if( !tempo[2].isEmpty()) check++;

        if( check < 1 || tempo.length > 3){
//            System.out.println(tempo[0]+"  "+tempo[1]+"  "+tempo[2]);
//            System.out.println("check" + check + " size " + tempo.length );
            //System.out.println("The Restaurant doesn't have enough Categories");
            return -1;
        }
        for (Restaurant temp_res : restaurantlist)
        {
            if( temp_res.getName().equalsIgnoreCase(restaurant.getName()) ) return 1;
            if( temp_res.getId() == restaurant.getId() ) return 1;
        }
        restaurantlist.add(restaurant);
        restaurantAdded++;
        return 0;
    }

    public List<Restaurant> searchByName(String name) {
        List<Restaurant> temp= new ArrayList<>();
        int flag = 0;
        for (Restaurant restaurant : restaurantlist) {
            if (restaurant.getName().toLowerCase().contains(name)) {
                //return restaurant;  // Found a matching restaurant, returning it
                temp.add( restaurant );
                flag = 1;
            }
        }
        if( flag == 1 ) return temp;
        return null;  // No matching restaurant found, returning null
    }

    public List<Restaurant> searchByPrice( String price )
    {
        int flag = 0;
        List<Restaurant> temp_restaurantlist = new ArrayList<>();

        for (Restaurant restaurant : restaurantlist) {
            if (restaurant.getPrice().equalsIgnoreCase(price)) {
                temp_restaurantlist.add( restaurant );
                flag = 1;
            }
        }
        return temp_restaurantlist;
//        if( flag == 1) return  temp_restaurantlist;         // returns a list of all the restaurant with this price
//        return null;                                        // returns NULL is no restaurant is found
    }

    public List<Restaurant> searchByZip( int Zip )
    {
        int flag = 0;
        List<Restaurant> temp_restaurantlist = new ArrayList<>();

        for (Restaurant restaurant : restaurantlist) {
            if (restaurant.getZip() == Zip) {
                temp_restaurantlist.add( restaurant );
                flag = 1;
            }
        }
        return temp_restaurantlist;
//        if( flag == 1) return  temp_restaurantlist;         // returns a list of all the restaurant with this price
//        return null;                                        // returns NULL is no restaurant is found
    }

    public List<Restaurant> searchByScore( double score )
    {
        int flag = 0;
        List<Restaurant> temp_restaurantlist = new ArrayList<>();

        for (Restaurant restaurant : restaurantlist) {
            if (Double.compare(restaurant.getScore(),score) == 0) {
                temp_restaurantlist.add( restaurant );
                flag = 1;
            }
        }
//        return  temp_restaurantlist;
        if( flag == 1) return  temp_restaurantlist;         // returns a list of all the restaurant with this price
        return null;                                        // returns NULL is no restaurant is found
    }

    public List<Restaurant> searchByCategory( String catagory )
    {
        int flag = 0;
        List<Restaurant> temp_restaurantlist = new ArrayList<>();

        for (Restaurant restaurant : restaurantlist) {
            String[] temp_categories = restaurant.getCategories();
            if(temp_categories[0].toLowerCase().contains(catagory)||temp_categories[1].toLowerCase().contains(catagory)||temp_categories[2].toLowerCase().contains(catagory))
            {
                temp_restaurantlist.add( restaurant );
                flag = 1;
            }
        }
        return  temp_restaurantlist;         // returns a list of all the restaurant with this price
//        if( flag == 1) return  temp_restaurantlist;
//        return null;                                        // returns NULL is no restaurant is found
    }

    public List<String> getAllCategoryNames()
    {
        List<String> allCategories = new ArrayList<>();
        int flag = 0;
        for ( Restaurant temp_res : restaurantlist) {
            String[] temp_categories = temp_res.getCategories();
            for( int i = 0; i<3; i++)
            {
                flag = 0;
                if(temp_categories[i].equals("")) continue;
                if( allCategories.size() == 0 ){
                    allCategories.add( temp_categories[i] );
                    continue;
                }
                for (String cat: allCategories) {
                    if( cat.equalsIgnoreCase(temp_categories[i])){
                        flag = 1;
                        break;
                    }
                }
                if( flag == 0 ) allCategories.add(temp_categories[i]);
            }
        }
        return allCategories;
    }

    public List< List<Restaurant> > getCategoryWiseRestaurant( List<String> allCategories )
    {
        List< List<Restaurant> > temp_catWiseRestaurantList = new ArrayList<>();

        for ( String category : allCategories )
        {
            List<Restaurant> temp_restaurantlist = new ArrayList<>();

                for( Restaurant restaurant : restaurantlist)
                {
                    String[] temp_cat= (restaurant.getCategories());
                    if( category.equalsIgnoreCase(temp_cat[0])){
                        temp_restaurantlist.add( restaurant );
                        //System.out.println("0");
                    }
                    else if( category.equalsIgnoreCase(temp_cat[1])){
                        temp_restaurantlist.add( restaurant );
                    }
                    else if( category.equalsIgnoreCase(temp_cat[2])){
                        temp_restaurantlist.add( restaurant );
                    }
                }
            temp_catWiseRestaurantList.add( temp_restaurantlist );
            //temp_restaurantlist.clear();
        }
        return temp_catWiseRestaurantList;
    }

     /************************* Food Managing Functions ******************
     ********************************************************************/

     public int addFoodInRes ( String resName, Food temp_food)      // for adding food in main menu
     {
         for (Restaurant restaurant : restaurantlist) {
             if( restaurant.getName().equalsIgnoreCase(resName) )
             {
                 temp_food.setRes_id( restaurant.getId() );
                 int check = restaurant.addFoodHelper( temp_food );
                 if( check == 1 ) return 1;                     /** Food already Exists in that Restaurant **/
                 if( check == 0 ) {                             /** Food Successfully Added **/
                     foodlist.add( temp_food );
                     return 0;
                 }
             }
         }
         return -1;                                             /** Restaurant Not Found **/
     }

//    public int addFood ( Food temp_food)
//    {
//        for (Food tempo_food : foodlist ) {
//            if( tempo_food.getFood_name().equalsIgnoreCase(tempo_food.getFood_name()) ) {
//                if( tempo_food.getFood_category().equalsIgnoreCase(tempo_food.getFood_category()) ) {
//                    return 1;                              /** Food already exists, cannot be added **/
//                }
//            }
//        }
//        foodlist.add( temp_food );
//        return 0;                                          /** Food added in foodsInRestaurant LISt, return 0 **/
//    }

     /************************** Food Printing Functions *****************
     ********************************************************************/

    public void printFoodList ( List<Food> temp )
    {
        for ( Food food : temp) {
            food.showFoodDetails();
        }
    }

    public void totalItemCount()
    {
        for ( Restaurant restaurant : restaurantlist ) {
            System.out.printf(restaurant.getName() + " : ");
            System.out.println(restaurant.itemInRestaurant() );
        }
    }
    public String getTotalItemCount()
    {
        String ans = new String();
        for ( Restaurant restaurant : restaurantlist ) {
            //System.out.printf( restaurant.getName() + " : ");
            ans += restaurant.getName() + " : ";
            //System.out.println( restaurant.itemInRestaurant() );
            ans += restaurant.itemInRestaurant() + "\n";
        }
        return ans;
    }

    /************************** Food getter Functions *********************
     ********************************************************************/
    public List<Food> searchFoodByName ( String foodName )
    {
        List<Food> temp_food = new ArrayList<>();
        for (Food food : foodlist) {
            if (food.getFood_name().toLowerCase().contains(foodName)) {
                temp_food.add(food);
            }
        }
        return temp_food;             // No matching restaurant found, returning null
    }

    public List<Food> searchFoodByNameInRes ( String foodName,String restaurantName )
    {
        List<Food> temp_food = new ArrayList<>();
        for ( Restaurant restaurant : restaurantlist ) {
            if(restaurant.getName().equalsIgnoreCase(restaurantName))
            {
                temp_food = restaurant.searchFoodByName( foodName );
                // temp_food = searchFoodByName( foodName );
                return temp_food;
            }
        }
        return null;
    }
    public List<Food> getCostliest ( String resName)
    {
        List<Food> temp_food = new ArrayList<>();

        for ( Restaurant restaurant : restaurantlist) {
            if( restaurant.getName().equalsIgnoreCase(resName) )
            {
                temp_food = restaurant.getCostliestFood();
                return  temp_food;
            }
        }
        //if(temp_food.isEmpty()) return null;
        return null;
    }

    public List<Food> searchFoodByCategory (String category)
    {
        List<Food> temp_food = new ArrayList<>();
        int flag = 0;
        for( Food food : foodlist ) {
            if( food.getFood_category().toLowerCase().contains(category) ) {
                temp_food.add( food );
                //break;
                flag = 1;
            }
        }
        //if (temp_food.isEmpty()) return null;
        if( flag == 0 ) return null;
        return temp_food;
    }

    public List<Food> searchFoodByCategoryInRes ( String category,String resName )
    {
        List<Food> temp_food = new ArrayList<>();

        for( Restaurant restaurant : restaurantlist ) {
            if( restaurant.getName().equalsIgnoreCase(resName) ) {
                temp_food = restaurant.getCategoryFood( category );
                return temp_food;
            }
        }
        //if (temp_food.isEmpty()) return null;
        return null;
    }

    public List<Food> searchInPriceRange ( double lower, double upper )
    {
        List<Food> temp_food = new ArrayList<>();

        for( Food food : foodlist )
        {
            if( food.getFood_price() >= lower && food.getFood_price() <= upper)
            {
                temp_food.add(food);
            }
        }
        //if (temp_food.isEmpty()) return null;
        return temp_food;
    }

    public List<Food> searchInPriceRangeInRes (  double lower, double upper, String resName)
    {
        List<Food> temp_food = new ArrayList<>();

        for( Restaurant restaurant : restaurantlist ) {
            if( restaurant.getName().equalsIgnoreCase(resName) )
            {
                temp_food = restaurant.getFoodInPrice( lower, upper ) ;
                //printFoodList( temp_food );
                return temp_food;
            }
        }
       // if (temp_food.isEmpty()) return null;
        return null;
    }

    public String idToResNameFinder( Food food) {
        for ( Restaurant resTemp : restaurantlist){
            if( resTemp.getId() == food.getRes_id() ){
                return resTemp.getName();
            }
        }
        return null;
    }


}