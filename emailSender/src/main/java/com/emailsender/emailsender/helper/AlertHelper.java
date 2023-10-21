package com.emailsender.emailsender.helper;

import javafx.scene.control.Alert;

public class AlertHelper {
    public static void showAlert(Alert.AlertType alertType, String title, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
