package prevproject;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;



public class RestaurantApp {

    private static String INPUT_FILE_NAME = "restaurant.txt";
    private static final String OUTPUT_RESTAURANT_FILE_NAME = "restaurant.txt";
    private static final String OUTPUT_FOOD_FILE_NAME = "menu.txt";


    public static void main(String[] args) throws Exception {
        RestaurantManager test = new RestaurantManager();
        test.input_restaurant(INPUT_FILE_NAME);
        INPUT_FILE_NAME = "menu.txt";
        test.input_menu(INPUT_FILE_NAME);

//        test.showAllRestaurantDetails();
//        System.out.println("\n");
//        test.showFullMenu();
//        System.out.println("\n");
//        test.showAllRestaurantDetailsWithFood();
//        System.out.println("\n");

        List<Restaurant> temp_res_list_main;
        Restaurant temp_res;
        int choice;
        int temp_int1,temp_int2,temp_int3;
        double temp_double1,temp_double2;
        Scanner scanner = new Scanner(System.in);
        String tempString1, tempString2, tempString3;

        do {
            // the following lines of code show a menu to take user's choice
            System.out.println("Main Menu:");
            System.out.println("1) Search Restaurants");
            System.out.println("2) Search Food Items");
            System.out.println("3) Add Restaurant");
            System.out.println("4) Add Food Item to the Menu");
            System.out.println("5) Exit System");

            System.out.print("Enter option: ");

            choice = scanner.nextInt();
            scanner.nextLine();                 // Consume the newline character
            System.out.println("");
            //System.out.println(choice);

            switch (choice) {
                case 1: // Search Restaurant

                    int choiceRes;
                    do {
                        int flag = 0;
                        // the following lines of code show a menu to take user's choice
                        System.out.println("Restaurant Searching Options:");
                        System.out.println("1) By Name");
                        System.out.println("2) By Score");
                        System.out.println("3) By Category");
                        System.out.println("4) By Price");
                        System.out.println("5) By Zip Code");
                        System.out.println("6) Different Category Wise List of Restaurants");
                        System.out.println("7) Back to Main Menu");

                        System.out.print("Enter option: ");
                        choiceRes = scanner.nextInt();
                        scanner.nextLine();                 // Consume the newline character
                        System.out.println("");

                        switch (choiceRes) {

                            case 1: //1) By Name

                                System.out.printf("Enter Restaurant name: ");
                                tempString1 = scanner.nextLine();
                                temp_res_list_main = test.searchByName(tempString1);
                                if( temp_res_list_main == null ){
                                    System.out.println("No such restaurant exists");
                                    break;
                                }
                                test.printRestaurantList( temp_res_list_main );
                                break;

                            case 2: //2) By Score

                                System.out.printf("Enter Restaurant Score: ");
                                temp_double1 = scanner.nextDouble();
                                temp_res_list_main = test.searchByScore( temp_double1);
                                if( temp_res_list_main == null || temp_res_list_main.size() == 0){
                                    System.out.println("No restaurant with this score exists");
                                    break;
                                }
                                test.printRestaurantList(temp_res_list_main );
                                temp_res_list_main.clear();
                                System.out.println("\n");
                                break;


                            case 3: // Category Restaurants

                                System.out.printf("Enter Restaurant Category: ");
                                tempString1 = scanner.nextLine();
                                temp_res_list_main = test.searchByCategory( tempString1);
                                if( temp_res_list_main.size() == 0 ){
                                    System.out.println("No restaurant with this category exists");
                                    break;
                                }
                                test.printRestaurantList(temp_res_list_main );
                                temp_res_list_main.clear();
                                System.out.println("\n");
                                break;

                            case 4: //  Price

                                System.out.printf("Enter Restaurant Price: ");
                                tempString1 = scanner.nextLine();
                                temp_res_list_main = test.searchByPrice( tempString1 );
                                if( temp_res_list_main.size() == 0 ){
                                    System.out.println("No restaurant with this price exists");
                                    break;
                                }
                                test.printRestaurantList(temp_res_list_main );
                                temp_res_list_main.clear();
                                System.out.println("\n");
                                break;

                            case 5:  // By Zip Code

                                System.out.printf("Enter Restaurant Zipcode: ");
                                temp_int1= scanner.nextInt();
                                temp_res_list_main = test.searchByZip( temp_int1 );
                                if( temp_res_list_main.size() == 0){
                                    System.out.println("No restaurant with this Zipcode exists");
                                    break;
                                }
                                test.printRestaurantList(temp_res_list_main );
                                temp_res_list_main.clear();
                                System.out.println("\n");
                                break;

                            case 6:  // 6) Different Category Wise List of Restaurants

                                System.out.println("Category wise Restaurant List :");
                                List <String> allCategoryNames = new ArrayList<>();
                                allCategoryNames = test.getAllCategoryNames();
                                if( allCategoryNames.size() == 0 ){
                                    System.out.println("No restaurant in the database");
                                    break;
                                }
                                List< List<Restaurant> > catwise_res_list = new ArrayList<>();
                                catwise_res_list = test.getCategoryWiseRestaurant( allCategoryNames );
                               // test.PrintCatWiseRestaurant( catwise_res_list,allCategoryNames);
                                String ans = test.getCatWiseRestaurantet(catwise_res_list,allCategoryNames);
                                System.out.println(ans+"\n");
                                break;

                            case 7: // 7) Back to Main Menu/
                                flag = 1;
                                break;

                            default:
                                System.out.println("Invalid Choice.Please select within 1-7");
                                break;
                        }
                    }while(choiceRes != 7);
                    break;

                case 2:// Search Food Items

                    int choiceFood;
                    do {
                        int flag = 0;
                        // the following lines of code show a menu to take user's choice
                        System.out.println("Food Item Searching Options:");
                        System.out.println("1) By Name");
                        System.out.println("2) By Name in a Given Restaurant");
                        System.out.println("3) By Category");
                        System.out.println("4) By Category in a Given Restaurant ");
                        System.out.println("5) By Price Range");
                        System.out.println("6) By Price Range in a Given Restaurant");
                        System.out.println("7) Costliest Food Item(s) on the Menu in a Given Restaurant");
                        System.out.println("8) List of Restaurants and Total Food Item on the Menu");
                        System.out.println("9) Back to Main Menu");

                        System.out.print("Enter option: ");
                        choiceFood = scanner.nextInt();
                        scanner.nextLine();                 // Consume the newline character
                        System.out.println("");

                        switch (choiceFood) {

                            case 1: //1) By food Name

                                System.out.printf("Enter Food name: ");
                                tempString1 = scanner.nextLine();
                                List<Food> food = test.searchFoodByName(tempString1);
                                if(food.size() == 0){
                                    System.out.println("No food by this name is in the menu");
                                    break;
                                }
                                System.out.println("Available foods : ");
                                test.printFoodList( food );
                                food.clear();
                                System.out.println("\n");
                                break;

                            case 2: //2) By food name in res

                                System.out.printf("Enter Food name: ");
                                tempString1 = scanner.nextLine();
                                System.out.printf("Enter Restaurant Name: ");
                                tempString2 = scanner.nextLine();
                                food = test.searchFoodByNameInRes(tempString1,tempString2);
                                if(food == null){
                                    System.out.println("No Restaurant with this name exists");
                                    break;
                                }
                                if(food.size() == 0){
                                    System.out.println("No food by this name is in the Restaurant");
                                    break;
                                }
                                System.out.println("Available foods : ");
                                test.printFoodList( food );
                                food.clear();
                                System.out.println("\n");
                                break;

                            case 3: // by food Category

                                System.out.printf("Enter Food Category: ");
                                tempString1 = scanner.nextLine();
                                food  = test.searchFoodByCategory( tempString1 );
                                if( food == null ){
                                    System.out.println("No such category exists");
                                    break;
                                }
                                if(food.size() == 0){
                                    System.out.println("No food by this category is in the menu");
                                    break;
                                }
                                System.out.println("Available Foods : ");
                                test.printFoodList( food )  ;
                                food.clear();
                                System.out.println("\n");
                                break;

                            case 4: //  cat in res

                                System.out.printf("Enter Category name: ");
                                tempString1 = scanner.nextLine();
                                System.out.printf("Enter Restaurant Name: ");
                                tempString2 = scanner.nextLine();
                                food = test.searchFoodByCategoryInRes(tempString1,tempString2);
                                if( food == null ){
                                    System.out.println("No such Restaurant exists");
                                    break;
                                }
                                if(food.size() == 0){
                                    System.out.println("No food by this category is in the restaurant");
                                    break;
                                }
                                System.out.println("Available Foods : ");
                                test.printFoodList( food );
                                food.clear();
                                System.out.println("\n");
                                break;

                            case 5:  // price

                                System.out.printf("Enter lower limit of price range : ");
                                temp_double1 = scanner.nextDouble();
                                System.out.printf("Enter upper limit of price range : ");
                                temp_double2 = scanner.nextDouble();
                                food = test.searchInPriceRange( temp_double1, temp_double2);
                                if(food.size() == 0){
                                    System.out.println("No food wothin this price range is in the menu");
                                    break;
                                }
                                System.out.println("Available Foods : ");
                                test.printFoodList( food );
                                food.clear();
                                System.out.println("\n");
                                break;

                            case 6:  // price in res

                                System.out.printf("Enter lower limit of price range : ");
                                temp_double1 = scanner.nextDouble();
                                System.out.printf("Enter upper limit of price range : ");
                                temp_double2 = scanner.nextDouble();
                                scanner.nextLine();
                                System.out.printf("Enter Restaurant Name : ");
                                tempString1 = scanner.nextLine();
                                food = test.searchInPriceRangeInRes( temp_double1, temp_double2, tempString1);
                                if( food == null ){
                                    System.out.println("No such restaurant exists");
                                    break;
                                }
                                if( food.size() == 0){
                                    System.out.println("No food within this price range is in the restaurant");
                                    break;
                                }
                                System.out.println("Available Foods : ");
                                test.printFoodList( food );
                                food.clear();
                                System.out.println("\n");
                                break;

                            case 7: // costliest

                                System.out.printf("Enter Restaurant name : ");
                                tempString1 = scanner.nextLine();
                                food  = test.getCostliest( tempString1 );
                                if( food == null ) {
                                    System.out.println("No such restaurant exists");
                                    break;
                                }
                                if(food.size() == 0){
                                    System.out.println("There is no food in the menu");
                                    break;
                                }
                                System.out.println("Costliest Foods : ");
                                test.printFoodList( food )  ;
                                food.clear();
                                System.out.println("\n");
                                break;

                            case 8:  // itemCount

                                System.out.println(test.getTotalItemCount());
                                break;

                            case 9:  // back to main
                                break;

                            default:
                                System.out.println("Invalid Choice.Please select within 1-9");
                                break;
                        }
                    }while(choiceFood != 9);
                    break;

                case 3: // Add Restaurants

                    System.out.printf("Enter Restaurant Name : ");
                    tempString1 = scanner.nextLine();
                    System.out.printf("Enter Restaurant ID : ");
                    temp_int1 = scanner.nextInt();
                    scanner.nextLine();
                    System.out.printf("Enter Restaurant Score : ");
                    temp_double1 = scanner.nextDouble();
                    scanner.nextLine();
                    System.out.printf("Enter Restaurant Price : ");
                    tempString2 = scanner.nextLine();
                    System.out.printf("Enter Restaurant Zip-Code : ");
                    temp_int2 = scanner.nextInt();
                    scanner.nextLine();
                    System.out.printf("Enter Restaurant Categories : ");
                    String catagories_fullline = scanner.nextLine();

                    if(catagories_fullline.isEmpty()){
                        System.out.println("Restaurant must have 1-3 categories.");
                        break;
                    }
                    String [] array = catagories_fullline.split(",", -1);   // splits the comma separated word

                    if(array.length < 1 || array.length > 3 ) {
                        System.out.println("Restaurant must have 1-3 categories.");
                        break;
                    }

                    //Restaurant(int id, String name, double score, String price, int zip, String[] categories)
                    Restaurant restaurant = new Restaurant(temp_int1,tempString1,temp_double1,tempString2,temp_int2,array);
                    int checker = test.addRestaurant( restaurant );
                    if( checker == 0) System.out.println("Restaurant successfully added.");
                    else if( checker == 1 ) System.out.println("Restaurant Name or Id already exists in database.");
                    else if( checker == -1 ) System.out.println("Restaurant must have 1-3 categories.");
                    break;

                case 4: // Add food to menu

                    System.out.print("Enter Restaurant Name : ");
                    tempString3 = scanner.nextLine();
                    //System.out.print("Enter Restaurant ID : ");
                    //temp_int1 = scanner.nextInt();
                    //scanner.nextLine();
                    System.out.print("Enter Food Category : ");
                    tempString1 = scanner.nextLine();
                    System.out.print("Enter Food Name : ");
                    tempString2 = scanner.nextLine();
                    System.out.print("Enter Food Price : ");
                    temp_double1 = scanner.nextDouble();
                    scanner.nextLine();

                    Food tempo_food = new Food( 99999, tempString1,tempString2,temp_double1);
                    checker = test.addFoodInRes(tempString3, tempo_food );
                    if( checker == 0 ) System.out.println("Food successfully added to menu");
                    else if ( checker == -1 ) System.out.println("The Restaurant does not exist");
                    else if ( checker == 1) System.out.println("Food by that name and category already exists in that restaurant");
                    break;

                case 5:  // exit
                    System.out.println("Saved in database...\nExiting System");
                    test.output_restaurant( OUTPUT_RESTAURANT_FILE_NAME );
                    test.output_menu( OUTPUT_FOOD_FILE_NAME );
                    break;

                default:
                    System.out.println("Invalid Choice.Please select within 1-5");
                    break;
            }
        }while( choice != 5 );
    }
}

///////////  