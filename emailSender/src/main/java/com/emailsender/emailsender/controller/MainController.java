package com.emailsender.emailsender.controller;

import com.emailsender.emailsender.MainApplication;
import com.emailsender.emailsender.model.EmailSenderModel;
import com.emailsender.emailsender.model.EmailSendersModel;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.scene.Scene;
import java.io.IOException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class MainController {
    @FXML
    public Button addEmailBtn;
    @FXML
    public Button updateEmailBtn;
    public Button deleteEmailBtn;
    public Button propertiesEmailBtn;
    @FXML
    public static TableColumn<EmailSenderModel, String> senderColumn;
    @FXML
    public static TableColumn<EmailSenderModel, String> receiverColumn;
    @FXML
    public static TableColumn<EmailSenderModel, String> subjectColumn;
    @FXML
    public static TableColumn<EmailSenderModel, String> desiredTimeColumn;
    @FXML
    public static TableColumn<EmailSenderModel, Double> beforeTimeColumn;
    @FXML
    private static TableView<EmailSendersModel> emailTableView;

    static EmailSendersModel emailSendersModel;


    @FXML
    private void addEmailBtnClick(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        addNewEmailSenderForm(stage);
        disableAndEnabledUpdateAndAddButtons(stage);
    }


    private void addNewEmailSenderForm(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("email-view.fxml"));
        AddEmailController timerStatusController = new AddEmailController(new EmailSenderModel());
        fxmlLoader.setController(timerStatusController);
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Time Status");
        stage.setScene(scene);
        stage.show();
    }

    private void disableAndEnabledUpdateAndAddButtons(Stage stage){
        addEmailBtn.setDisable(true);
        updateEmailBtn.setDisable(true);
        stage.setOnCloseRequest(event -> {
            addEmailBtn.setDisable(false);
            updateEmailBtn.setDisable(false);
        });
    }

    static void addEmailToEmailsListTable(EmailSenderModel emailSenderModel){
        emailSendersModel.emailSenderModel
                .add(emailSenderModel);

        senderColumn.setCellValueFactory(new PropertyValueFactory<>("senderEmail"));
        receiverColumn.setCellValueFactory(new PropertyValueFactory<>("receiverEmail"));
        desiredTimeColumn.setCellValueFactory(new PropertyValueFactory<>("desiredDateTime"));
        subjectColumn.setCellValueFactory(new PropertyValueFactory<>("subject"));
        beforeTimeColumn.setCellValueFactory(new PropertyValueFactory<>("beforeSendSecond"));

        ObservableList<EmailSendersModel> senders = emailTableView.getItems();
        emailTableView.setItems(senders);
    }

}