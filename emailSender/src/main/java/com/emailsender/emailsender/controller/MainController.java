package com.emailsender.emailsender.controller;

import com.emailsender.emailsender.MainApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.Scene;
import java.io.IOException;

public class MainController {
    @FXML
    public Button addEmailBtn;
    @FXML
    public Button updateEmailBtn;

    @FXML
    private void addEmailBtnClick(ActionEvent actionEvent) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("email-view.fxml"));
        AddEmailController timerStatusController = new AddEmailController();
        fxmlLoader.setController(timerStatusController);
        Stage stage = new Stage();
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Time Status");
        stage.setScene(scene);
        stage.show();

        disableAndEnabledUpdateAndAddButton(stage);
    }


    private void addNewEmailSender(){

    }

    private void disableAndEnabledUpdateAndAddButton(Stage stage){
        addEmailBtn.setDisable(true);
        updateEmailBtn.setDisable(true);
        stage.setOnCloseRequest(event -> {
            addEmailBtn.setDisable(false);
            updateEmailBtn.setDisable(false);
        });
    }

}