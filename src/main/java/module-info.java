module com.basiccalculator {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.basiccalculator to javafx.fxml;
    exports com.basiccalculator;
}
