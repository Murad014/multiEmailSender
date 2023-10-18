module com.emailsender.emailsender {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;

    opens com.emailsender.emailsender to javafx.fxml;
    exports com.emailsender.emailsender;
}