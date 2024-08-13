package com.example.javafx_honest_try;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import prevproject.Food;

import java.io.IOException;
import java.util.List;
import java.util.Optional;


public class Res_HomePageController {



    private Main main;

    @FXML
    private TableView foodTableview;
    @FXML
    private Button costliestfood;
    @FXML
    private Label foodDetailsLabel;
    @FXML
    private Label labelResName;
    @FXML
    private Button buttonLogOut;
    @FXML
    private Button buttonAddFood;
    @FXML
    private Button buttonOrdered;
    @FXML
    private Button buttonSearch;
    @FXML
    private TextField foodCaragoryText;
    @FXML
    private TextField foodNameText;
    @FXML
    private Label notification;
    @FXML
    private TableView orderView = new TableView<>();
    @FXML
    public Label changelabel;

    public ObservableList<Food> orderList;
    public int orderCount = 0;
    String cssPath = getClass().getResource("tableStyle.css").toExternalForm();


    Stage stage;

    public void setMain(Main main) {
        this.main = main;
    }

    public Res_HomePageController() throws InterruptedException {
        Thread.sleep(80);
        //notification.setVisible(false);
    }
    @FXML
    public void initialize()
    {
        TableColumn<Food, String> col1 = new TableColumn<>("Name");
        col1.setMinWidth(200);
        col1.setCellValueFactory(new PropertyValueFactory<>("food_name"));

        TableColumn<Food, String> col2 = new TableColumn<>("Price");
        col2.setMinWidth(150);
        col2.setCellValueFactory(new PropertyValueFactory<>("food_price"));

        TableColumn<Food, String> col3 = new TableColumn<>("Category");
        col3.setMinWidth(150);
        col3.setCellValueFactory(new PropertyValueFactory<>("food_category"));

        foodTableview.getColumns().addAll(col1, col2, col3);

        foodTableview.getStylesheets().add(cssPath);
    }
    public void foodSearch(ActionEvent actionEvent) throws InterruptedException {
            String cat,name;
            name = foodNameText.getText();
            cat = foodCaragoryText.getText();

            foodTableview.getItems().clear();

            if( name.isEmpty() && cat.isEmpty() ){
                main.showAlert();                                       // should overload this
            }
            else if( name.isEmpty() ){
                List<Food> foodList = main.clientRestaurant.restaurantManager.searchFoodByCategoryInRes(cat,main.getResName());
//                String[] foodNameList = new String[foodList.size()];
//
//                int i = 0;
//                for( Food temp : foodList) {
//                    foodNameList[i] = temp.getFood_name();
//                    i++;
//                }
//                foodListView.getItems().addAll(foodList);
//                foodListView.setOnMouseClicked(new EventHandler<MouseEvent>() {
//
//                    @Override
//                    public void handle(MouseEvent event) {
//                        System.out.println("clicked on " + foodListView.getSelectionModel().getSelectedItem().getFood_name());
//                    }
//                });

                foodTableview.getItems().addAll(foodList);

            }
            else{
                System.out.println(name+ " " + main.getResName() );
                List<Food> foodList = main.clientRestaurant.restaurantManager.searchFoodByNameInRes(name,main.getResName());
//                String[] foodNameList = new String[foodList.size()];
//
//                foodListView.getItems().clear();
//
//                int i = 0;
//                for( Food temp : foodList){
//                    foodNameList[i] = temp.getFood_name();
//                    i++;
//                }
//                foodListView.getItems().addAll(foodList);
//                foodListView.setOnMouseClicked(new EventHandler<MouseEvent>() {
//
//                    @Override
//                    public void handle(MouseEvent event) {
//                        System.out.println("clicked on " + foodListView.getSelectionModel().getSelectedItem());
//                        //String name  = foodListView.getSelectionModel().getSelectedItem();
//                        //Food = main.clientRestaurant.restaurantManager.
//                        //foodDetailsLabel.setText();
//                    }
//                });
                foodTableview.getItems().addAll(foodList);
            }
        }

    public void addFood(ActionEvent actionEvent) throws IOException {
        main.showAddFoodPage();
    }
    public void logOutAction(ActionEvent actionEvent) throws IOException {

        // Confirmation Tab :
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("CONFIRMATION");
        alert.setHeaderText("Are you sure to logout?");
        alert.setContentText("Press OK to logout");
        alert.showAndWait();

        Optional<ButtonType> result = Optional.ofNullable(alert.getResult());

        if( result.get() == ButtonType.OK)
        {
            main.clientRestaurant.restaurantManager.output_restaurant("NewRestaurant.txt");
            main.clientRestaurant.restaurantManager.output_menu("NewMenu.txt");
            System.out.println("Logged out and data saved");
            Platform.exit();
        }
    }

    int flag = 0;
        public void orderListAction(ActionEvent actionEvent) {

            notification.setVisible(false);

            if( flag == 0) {

                orderList = main.clientRestaurant.readThread.orderedFoodList;

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


                // Add all columns to the tableView
                orderView.getColumns().addAll(col1, col2, col3, buttonCol);
                orderView.getStylesheets().add(cssPath);

                flag = 1;
                stage = new Stage();
            }

            stage.setTitle("Orders");
            stage.setWidth(580);
            stage.setHeight(450);

            Scene scene = new Scene(new Group(orderView));
            stage.setScene(scene);
            stage.show();

            //orderView.setItems(null);

            orderList.addListener((ListChangeListener<prevproject.Food>) change -> {
                while (change.next()) {
                    System.out.println("Order List Updated");
                    if (change.wasAdded()) {
                        //System.out.println("Order List Updated");

                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                orderCount++;
                                buttonOrdered.setText("Orders(" + orderCount + ")");
                                notification.setVisible(true);
                            }
                        });
                    }
                    if (change.wasRemoved()) {
                        System.out.println("ooreh!");
                    }
                }
            });

            orderView.setItems(orderList);

        }

    public void changelabel() {
        labelResName.setText(" Welcome to " + main.getResName().toUpperCase()+"!");
    }

    public void costliestFoodAction(ActionEvent actionEvent) {
        List<Food> foodList = main.clientRestaurant.restaurantManager.getCostliest(main.getResName());
        foodTableview.getItems().clear();
        foodTableview.getItems().addAll(FXCollections.observableArrayList(foodList));
    }
}
