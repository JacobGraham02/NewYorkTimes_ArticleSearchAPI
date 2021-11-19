module com.jacobdgraham.comp1011assignment2 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.jacobdgraham.comp1011assignment2 to javafx.fxml;
    exports com.jacobdgraham.comp1011assignment2;
    exports com.jacobdgraham.comp1011assignment2.Controller;
    opens com.jacobdgraham.comp1011assignment2.Controller to javafx.fxml;
}