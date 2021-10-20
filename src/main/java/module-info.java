module com.example.avltre {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;


    opens com.example.avltre to javafx.fxml;
    exports com.example.avltre;
}