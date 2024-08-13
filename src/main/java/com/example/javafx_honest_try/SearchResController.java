package com.example.javafx_honest_try;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import prevproject.Food;
import prevproject.Restaurant;
import util.OrderedFoodDTO;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class SearchResController implements Initializable {

    public MainCustomer main;
    public List<Food> cartList = new ArrayList<>();
    String resName;

    @FXML
    private Label bigLabel;
    @FXML
    private Button buttonOrder;
    @FXML
    private Button buttonCart;
    @FXML
    private TextField TextfieldResName;
    @FXML
    private Label labelResName;
    @FXML
    private TextField searchOptionText;
    @FXML
    private TextField searchOptionTextFood;
    @FXML
    private Label enterDetailslabel;
    @FXML
    private Button buttonSearch;
    @FXML
    private TableView tableView;
    @FXML
    private Button buttonBack;
    @FXML
    private TableView cartView = new TableView<>();
    @FXML
    private ChoiceBox<String> choiceBoxRes;
    @FXML
    private ChoiceBox<String> choiceBoxResFood;
    String[] choicesRes = {"Search By Name","Search By Price","Search By Zip","Search By Category","Different Category Wise Restaurant"};
    String[] choicesFood = {"Search By Name","Search By Name in a Restaurant","Search By Category","Search By Category in a Restaurant","Search By Price","By Price in a given Restaurant","Costliest Food Item(s) on the Menu in a Given Restaurant","List of Restaurants and Total Food Item on the Menu"};
    int foodUsed = 0;
    int resUsed = 0;
    String cssPath = getClass().getResource("tableStyle.css").toExternalForm();




    public void setMain(MainCustomer main) {
        this.main = main;
    }

    public void choiceBoxRestaurantAction(ActionEvent actionEvent) {
        String cmd = choiceBoxRes.getValue();

        if( !cmd.equalsIgnoreCase("Different Category Wise Restaurant"))
        {
            bigLabel.setText(null);
        }
    }


    public void choiceBoxAction(ActionEvent actionEvent) {
        String cmd = choiceBoxResFood.getValue();

        if (cmd == null) return;
        if( cmd.equalsIgnoreCase("Search By Name in a Restaurant")){
            TextfieldResName.setVisible(true);
            labelResName.setVisible(true);
        }
        else if( cmd.equalsIgnoreCase("Search By Category in a Restaurant")){
            TextfieldResName.setVisible(true);
            labelResName.setVisible(true);
        }
        else if( cmd.equalsIgnoreCase("By Price in a given Restaurant")){
            TextfieldResName.setVisible(true);
            labelResName.setVisible(true);
        }
        else if ( cmd.equalsIgnoreCase("Costliest Food Item(s) on the Menu in a Given Restaurant")){
            TextfieldResName.setVisible(true);
            labelResName.setVisible(true);
            labelResName.setText("Enter Restaurant Name :");
        }
        else   {
            TextfieldResName.setVisible(false);
            labelResName.setVisible(false);
        }

        if( !cmd.equalsIgnoreCase("List of Restaurants and Total Food Item on the Menu")){
            bigLabel.setText(null);
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        choiceBoxRes.getItems().addAll( choicesRes );
        choiceBoxRes.setValue("Search By Name");
        //choiceBoxRes.setVisible(false);

        choiceBoxResFood.getItems().addAll( choicesFood );
        choiceBoxResFood.setValue("Search By Name");

        /////////////////////////// Table implementing ////////////////////////////

    }

    public void searchAction(ActionEvent actionEvent) {         //// job after search, one button hanfles 2 search

        String text1 = searchOptionText.getText();
        String text2 = searchOptionTextFood.getText();

        if(text1 == null && text2 == null){
            return;
        }
        else if(text1 == null){
            if( text2.isEmpty() ){}
            else{                           // text 2 te kisu ase
                if(foodUsed == 0){
                    resUsed = 0;
                    foodUsed = 1;
                    tableView.getColumns().clear();
                    initializeColumnsFood();
                }
                foodTableLoader();
                searchOptionText.setText(null);
                searchOptionTextFood.setText(null);
            }
        }
        else if ( text2 == null ) {
            if( text1.isEmpty() ){}
            else{                           // text 1 te kisu ase
                if(resUsed == 0){
                    foodUsed = 0;
                    resUsed = 1;
                    tableView.getColumns().clear();
                    initializeColumnsRes();
                }
                resTableLoader();
                searchOptionText.setText(null);
                searchOptionTextFood.setText(null);
            }
        }
        else if(text2.isEmpty() && text1.isEmpty()){
            return;
        }
        else if( text1.isEmpty() ){    //text 2 dise
            if(foodUsed == 0){
                resUsed = 0;
                foodUsed = 1;
                tableView.getColumns().clear();
                initializeColumnsFood();
            }
            foodTableLoader();
            searchOptionText.setText(null);
            searchOptionTextFood.setText(null);
        }
        else if ( text2.isEmpty() ) {
            if(resUsed == 0){
                foodUsed = 0;
                resUsed = 1;
                tableView.getColumns().clear();
                //System.out.println("asdasdasdasdasd");
                initializeColumnsRes();

                resName = text1;
            }
            resTableLoader();
            searchOptionText.setText(null);
            searchOptionTextFood.setText(null);
        }
        else{
            System.out.println("Other textfield options");
        }
    }

    private void resTableLoader()           // loads Restaurant Table
    {
        String text = searchOptionText.getText();
        String search = choiceBoxRes.getValue();

        List<Restaurant> restaurantList = new ArrayList<>();

        if( text.isEmpty() || search.isEmpty() ){
            main.showAlert("Enter necessary details!",1);
            return;
        }

        if( search.equalsIgnoreCase("Search By Name")) {
            restaurantList = main.clientCustomer.restaurantManager.searchByName(text);
        }
        if( search.equalsIgnoreCase("Search By Price")){
            restaurantList = main.clientCustomer.restaurantManager.searchByPrice(text);
        }
        if( search.equalsIgnoreCase("Search By Zip")){
            restaurantList = main.clientCustomer.restaurantManager.searchByZip(Integer.parseInt(text));
        }
        if( search.equalsIgnoreCase("Search By Category")){
            restaurantList = main.clientCustomer.restaurantManager.searchByCategory(text);
        }
        if( search.equalsIgnoreCase("Different Category Wise Restaurant")) {
            List <String> allCategoryNames = new ArrayList<>();
            allCategoryNames = main.clientCustomer.restaurantManager.getAllCategoryNames();
            List< List<Restaurant> > catwise_res_list = new ArrayList<>();
            catwise_res_list = main.clientCustomer.restaurantManager.getCategoryWiseRestaurant( allCategoryNames );
            String ans = main.clientCustomer.restaurantManager.getCatWiseRestaurantet(catwise_res_list,allCategoryNames);// UPDATE
            bigLabel.setText(ans);
        }
        if( restaurantList == null || restaurantList.isEmpty()) {            // if no restaurant found
            searchOptionText.setText(null);
            tableView.setEditable(true);
            tableView.getColumns().clear();
            resUsed = 0;
            //System.out.println("dhukloooo");
            return;
        }
        loadRes(restaurantList);
        searchOptionText.setText(null);
        searchOptionTextFood.setText(null);
    }

    public void foodTableLoader()                       // initializes and loads food table
    {
        String text = searchOptionTextFood.getText();
        String search = choiceBoxResFood.getValue();

        //initializeColumnsFood();

        List<Food> foodList = new ArrayList<>();

        if( text.isEmpty() || search.isEmpty() ) {
            main.showAlert("Enter necessary details!",1);
            return;
        }

        if( search.equalsIgnoreCase("Search By Name")) {
            foodList = main.clientCustomer.restaurantManager.searchFoodByName(text);
        }
        if( search.equalsIgnoreCase("Search By Name in a Restaurant")) {
            String temp = TextfieldResName.getText();
            System.out.println(temp);
            System.out.println(text);
            foodList = main.clientCustomer.restaurantManager.searchFoodByNameInRes(text,temp);
        }
        if( search.equalsIgnoreCase("Search By Price")){
            String[] temp= text.split("-",-1);
            foodList = main.clientCustomer.restaurantManager.searchInPriceRange(Double.parseDouble(temp[0]),Double.parseDouble(temp[1]));
        }
        if( search.equalsIgnoreCase("By Price in a given Restaurant")){
            String[] temp= text.split("-",-1);
            String tempo = TextfieldResName.getText();
            foodList = main.clientCustomer.restaurantManager.searchInPriceRangeInRes(Double.parseDouble(temp[0]),Double.parseDouble(temp[1]),tempo);
        }
        if( search.equalsIgnoreCase("Search By Category")){
            foodList = main.clientCustomer.restaurantManager.searchFoodByCategory(text);
        }
        if( search.equalsIgnoreCase("Search By Category in a Restaurant")){
            String tempo = TextfieldResName.getText();
            System.out.println(text+","+tempo);
            foodList = main.clientCustomer.restaurantManager.searchFoodByCategoryInRes(text,tempo);
        }
//        if( search.equalsIgnoreCase("Search By Category in a Restaurant")){
//            foodList = main.clientCustomer.restaurantManager.searchFoodByCategoryInRes(text);             // UPDATE////
//        }
        if( search.equalsIgnoreCase("List of Restaurants and Total Food Item on the Menu")) {
            //foodList = main.clientCustomer.restaurantManager.getCategoryWiseRestaurant();
            System.out.println("dhukse");
            bigLabel.setText( main.clientCustomer.restaurantManager.getTotalItemCount() );
        }
        if( search.equalsIgnoreCase("Costliest Food Item(s) on the Menu in a Given Restaurant")){
            //System.out.println("hoise re hoise");
            String temp = TextfieldResName.getText();               // UPDATE
            foodList = main.clientCustomer.restaurantManager.getCostliest(temp);
            labelResName.setText("Enter food Deatils : ");
        }


        if( foodList == null || foodList.isEmpty()) {            // if no restaurant found
            searchOptionTextFood.setText(null);
            searchOptionText.setText(null);
            tableView.setEditable(true);
            tableView.getColumns().clear();
            foodUsed = 0;
            return;
        }

        loadFood(foodList);
        searchOptionTextFood.setText(null);
        searchOptionText.setText(null);
    }
    private void initializeColumnsRes() {
        TableColumn<Restaurant, String> col1 = new TableColumn<>("Name");
        col1.setMinWidth(100);
        col1.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Restaurant, String> col2 = new TableColumn<>("Price");
        col2.setMinWidth(100);
        col2.setCellValueFactory(new PropertyValueFactory<>("price"));

        TableColumn<Restaurant, String> col3 = new TableColumn<>("Zip");
        col3.setMinWidth(100);
        col3.setCellValueFactory(new PropertyValueFactory<>("zip"));

//        TableColumn<Restaurant, String> col4 = new TableColumn<>("Category");
//        col4.setMinWidth(250);
//        col4.setCellValueFactory(new PropertyValueFactory<>("categoriesString"));

        tableView.getColumns().addAll(col1, col2, col3 );
        tableView.getStylesheets().add(cssPath);
    }

    private void initializeColumnsFood() {

            TableColumn<Food, String> col1 = new TableColumn<>("Name");
            col1.setMinWidth(180);
            col1.setCellValueFactory(new PropertyValueFactory<>("food_name"));

            TableColumn<Food, String> col2 = new TableColumn<>("Price");
            col2.setMinWidth(140);
            col2.setCellValueFactory(new PropertyValueFactory<>("food_price"));

            TableColumn<Food, String> col3 = new TableColumn<>("Category");
            col3.setMinWidth(140);
            col3.setCellValueFactory(new PropertyValueFactory<>("food_category"));

//            TableColumn<Food, String> col4 = new TableColumn<>("ID");
//            col3.setMinWidth(50);
//            col3.setCellValueFactory(new PropertyValueFactory<>("res_id"));
            TableColumn<Food, String> col4 = new TableColumn<>("ID");
            col4.setMinWidth(50);
            col4.setCellValueFactory(new PropertyValueFactory<>("id"));

            //////////////////// Create a button column //////////////////////////
            TableColumn<Food, String> buttonCol = new TableColumn<>("Add To Cart");
            buttonCol.setMinWidth(70);

            // Define the cell factory for the button column
            buttonCol.setCellFactory(param -> new TableCell<Food, String>() {
                final Button addToCartButton = new Button("Add");
                {
                    // Set the default background color of the button to lime
                    addToCartButton.setStyle("-fx-background-color: #fdf2d7;");
                }


                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);

                    if (empty) {
                        setGraphic(null);
                    } else {                                            /// Button click korle ki hobe
                        // Set the action for the button when clicked
                        addToCartButton.setOnAction(event -> {
                            Food food = getTableView().getItems().get(getIndex());
                            //System.out.println("shei!!");
                            cartList.add(food);
                            buttonCart.setText("Cart("+cartList.size()+")");
                            for( var temp : cartList){
                                temp.showFoodDetails();
                            }
                            getTableView().getItems().remove(getIndex());
                            //getTableRow().setStyle("-fx-background-color: lime;");
                        });
                        setGraphic(addToCartButton);
                    }
                }
            });

            // Add all columns to the tableView
            tableView.getColumns().addAll(col1, col2, col3, col4, buttonCol);
            tableView.getStylesheets().add(cssPath);

    }


    public void loadRes(List<Restaurant> restaurantList) {

        ObservableList<Restaurant> data;
        data = FXCollections.observableArrayList( restaurantList );

        tableView.setEditable(true);
        tableView.setItems(data);
    }

    public void loadFood(List<Food> foodList) {

        ObservableList<Food> data;
        data = FXCollections.observableArrayList( foodList );

        tableView.setEditable(true);
        tableView.setItems(data);
    }

    public  int flag = 0;
    public void openCart(ActionEvent actionEvent) {

        if( flag == 0) {
            TableColumn<Food, String> col1 = new TableColumn<>("Name");
            col1.setMinWidth(200);
            col1.setCellValueFactory(new PropertyValueFactory<>("food_name"));

            TableColumn<Food, String> col2 = new TableColumn<>("Price");
            col2.setMinWidth(150);
            col2.setCellValueFactory(new PropertyValueFactory<>("food_price"));

            TableColumn<Food, String> col3 = new TableColumn<>("Category");
            col3.setMinWidth(150);
            col3.setCellValueFactory(new PropertyValueFactory<>("food_category"));

            //////////////////// Create a button column //////////////////////////
            TableColumn<Food, String> buttonCol = new TableColumn<>("Remove");
            buttonCol.setMinWidth(80);

            buttonCol.setCellFactory(param -> new TableCell<Food, String>() {
                final Button removeFromCart = new Button("Remove");{
                    removeFromCart.setStyle("-fx-background-color: #fdf2d7;");
                }

                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);

                    if (empty) {
                        setGraphic(null);
                    } else {
                        // Set the action for the button when clicked
                        removeFromCart.setOnAction(event -> {
                            Food food = getTableView().getItems().remove(getIndex());
                            cartList.remove(food);
                            buttonCart.setText("Cart("+cartList.size()+")");
                            //food.showFoodDetails();
                            /// changing tableview row colour back to normal

//                            TableView<Food> temptable = tableView;                              // row er color back to normal korar jnno
//                            for (Food temp_food :  temptable.getItems()) {
//                                System.out.println(temp_food.getFood_name());
//                                System.out.println(food.getFood_name());
//                                if (temp_food.getFood_name().equals(food.getFood_name())) {
//                                    System.out.println();
//                                    getTableRow().setStyle("-fx-background-color: yellow;");
//                                }
//                            }

                        });
                        //getTableRow().setStyle("-fx-background-color: lime;");                        });
                    setGraphic(removeFromCart);
                    }
                }
            });

            // Add all columns to the tableView
            cartView.getColumns().addAll(col1, col2, col3, buttonCol);
            cartView.getStylesheets().add(cssPath);

            flag = 1;
        }

        Stage stage = new Stage();
        stage.setTitle("Food Cart");
        stage.setWidth(580);
        stage.setHeight(450);

        cartView.setItems(FXCollections.observableArrayList(cartList) );
        Scene scene = new Scene(new Group(cartView));

        stage.setScene(scene);
        stage.show();
    }

    public void orderAction(ActionEvent actionEvent) {
        List<Food> food = cartList;
        List<List<Food>> allFoodList = groupFoodsById(food);

        for( List<Food> temp : allFoodList) {
           String resName = main.clientCustomer.restaurantManager.idToResNameFinder(temp.get(0));

           ///////////////////////// Sends orderFoodDTO to server //////////////////////
           try {
               main.clientCustomer.getSocketWrapper().write(new OrderedFoodDTO(temp, resName));
               System.out.println("order sent to "+ resName);
           }catch (Exception e){
               e.printStackTrace();
           }
        }
    }

    public List<List<Food>> groupFoodsById(List<Food> cartList) {
        Map<Integer, List<Food>> idToFoodMap = new HashMap<>();

        // Group foods by ID
        for (Food food : cartList) {
            int id = food.getRes_id();
            idToFoodMap.computeIfAbsent(id, k -> new ArrayList<>()).add(food);
        }

        // Extract the grouped lists and return them as a 2D list
        List<List<Food>> groupedFoods = new ArrayList<>(idToFoodMap.values());

        return groupedFoods;
    }

    public void logoutAction(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("CONFIRMATION");
        alert.setHeaderText("Are you sure to logout?");
        alert.setContentText("Press OK to logout");
        alert.showAndWait();

        Optional<ButtonType> result = Optional.ofNullable(alert.getResult());

        if( result.get() == ButtonType.OK)
        {
            System.out.println("Logged out and data saved");
            Platform.exit();
        }
    }

    public void backAction(ActionEvent actionEvent) throws Exception {
        main.showHomeCustomer();
    }
}

