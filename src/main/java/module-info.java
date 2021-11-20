module com.jacobdgraham.comp1011assignment2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;
    requires java.net.http;
    requires javafx.graphics;


    opens com.jacobdgraham.comp1011assignment2 to javafx.fxml, com.google.gson;
    exports com.jacobdgraham.comp1011assignment2;
    opens com.jacobdgraham.comp1011assignment2.Model to javafx.fxml, com.google.gson;
    exports com.jacobdgraham.comp1011assignment2.Model;
    opens com.jacobdgraham.comp1011assignment2.Controller to javafx.fxml, com.google.gson;
    exports com.jacobdgraham.comp1011assignment2.Controller;
}