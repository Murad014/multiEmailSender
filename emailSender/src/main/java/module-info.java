module com.emailsender.emailsender {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.fasterxml.jackson.databind;


    requires com.dlsc.formsfx;
    requires javax.mail.api;

    opens com.emailsender.emailsender to javafx.fxml;
    exports com.emailsender.emailsender;
    exports com.emailsender.emailsender.controller;
    opens com.emailsender.emailsender.controller to javafx.fxml;

    exports com.emailsender.emailsender.model;
}