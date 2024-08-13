package com.example.javafx_honest_try;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Test extends Application {
    private ObservableList<Food> foodList = FXCollections.observableArrayList();
    private TableView<Food> tableView = new TableView<>();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws InterruptedException {
        primaryStage.setTitle("Food Table Example");

        // Create columns for the TableView
        TableColumn<Food, String> nameCol = new TableColumn<>("Name");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("foodName")) ;

        TableColumn<Food, Double> priceCol = new TableColumn<>("Price");
        priceCol.setCellValueFactory(new PropertyValueFactory<>("foodName "));

        tableView.getColumns().addAll(nameCol);

        // Create a VBox to hold the TableView
        VBox vbox = new VBox(tableView);

        Scene scene = new Scene(vbox, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.show();

        // Bind the TableView to the ObservableList
        tableView.setItems(foodList);

        // Add a listener to the ObservableList to update the TableView automatically
        foodList.addListener((ListChangeListener<Food>) change -> {
            while (change.next()) {
                if (change.wasAdded()) {
                    //tableView.getItems().addAll(change.getAddedSubList());
                    System.out.println("ooreh!");
                }
                if (change.wasRemoved()) {
                    System.out.println("ooreh!");
                }
            }
        });

        // Simulate adding items to the list (for testing purposes)
        //Thread.sleep(2000);
        simulateAddingItems();
    }

    // Simulate adding items to the ObservableList (for testing purposes)
    private void simulateAddingItems() {
        for (int i = 1; i <= 5; i++) {
            foodList.add(new Food("Food " + i, Math.random() * 10 + 1));
        }
    }

    public static class Food {
        private final String foodName;
        private final double foodPrice;

        public Food(String foodName, double foodPrice) {
            this.foodName = foodName;
            this.foodPrice = foodPrice;
        }

        public String getFoodName() {
            return foodName;
        }

        public double getFoodPrice() {
            return foodPrice;
        }

        // You can create JavaFX properties for these fields if needed
    }
}

