module myjfx {

      requires javafx.controls;
      requires javafx.fxml;
      requires javafx.graphics;

    opens com.example.javafx_honest_try to javafx.controls, javafx.fxml, javafx.graphics;
    opens prevproject to javafx.controls, javafx.fxml, javafx.graphics;
    //opens util to javafx.controls,javafx.graphics,javafx.fxml;
    exports prevproject;
    exports com.example.javafx_honest_try;
    exports ClientRestaurantUI;
    //exports util;
    opens ClientRestaurantUI to javafx.controls, javafx.fxml, javafx.graphics;
}