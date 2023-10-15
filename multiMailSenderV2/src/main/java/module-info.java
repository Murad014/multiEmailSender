module com.multimailsender.multimailsenderv2 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.multimailsender.multimailsenderv2 to javafx.fxml;
    exports com.multimailsender.multimailsenderv2;
}