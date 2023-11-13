package com.emailsender.emailsender.controller;

import com.emailsender.emailsender.MainApplication;
import com.emailsender.emailsender.exception.ResourceNotFoundException;
import com.emailsender.emailsender.model.EmailFormEnum;
import com.emailsender.emailsender.model.EmailSenderModel;
import com.emailsender.emailsender.model.EmailSendersModel;
import com.emailsender.emailsender.utils.TableUtils;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.scene.Scene;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

import com.fasterxml.jackson.databind.ObjectMapper;

public class MainController {
    @FXML
    public Button addEmailBtn;
    @FXML
    public Button updateEmailBtn;
    public Button deleteEmailBtn;
    public Button propertiesEmailBtn;
    @FXML
    public TableColumn<EmailSenderModel, String> senderColumn;
    @FXML
    public TableColumn<EmailSenderModel, String> receiverColumn;
    @FXML
    public TableColumn<EmailSenderModel, String> subjectColumn;
    @FXML
    public TableColumn<EmailSenderModel, String> desiredTimeColumn;
    @FXML
    public TableColumn<EmailSenderModel, Double> beforeTimeColumn;
    @FXML
    private TableView<EmailSenderModel> emailsTableView;

    static EmailSendersModel emailSendersModel = new EmailSendersModel();

    @FXML
    public void initialize(){

    }


    @FXML
    private void addEmailBtnClick(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        addNewEmailToSenderForm(stage, EmailFormEnum.ADD_EMAIL_FORM);
        disableAndEnabledUpdateAndAddButtons(stage);

    }

    @FXML
    private void clickDeleteButton(ActionEvent actionEvent){
        TableUtils.deleteSelectedRows(emailsTableView);
    }


    private void addNewEmailToSenderForm(Stage stage, EmailFormEnum controllerName) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("email-view.fxml"));
        String titleName;
        switch (controllerName) {
            case ADD_EMAIL_FORM -> {
                AddEmailController emailAddController = new AddEmailController(
                        new EmailSenderModel(),
                        this);
                fxmlLoader.setController(emailAddController);
                titleName = "Add new email";
            }
            case UPDATE_EMAIL_FORM -> {
                EmailSenderModel selectedEmailSender = emailsTableView.getSelectionModel().getSelectedItem();
                UpdateEmailController emailUpdateController = new UpdateEmailController(
                        selectedEmailSender,
                        this);
                fxmlLoader.setController(emailUpdateController);
                titleName = "Update email";
            }
            default -> throw new ResourceNotFoundException(controllerName.toString());
        }

        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle(titleName);
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

    void addEmailToEmailsListTable(EmailSenderModel emailSenderModel){
        senderColumn.setCellValueFactory(new PropertyValueFactory<>("senderEmail"));
        receiverColumn.setCellValueFactory(new PropertyValueFactory<>("receiverEmail"));
        desiredTimeColumn.setCellValueFactory(new PropertyValueFactory<>("desiredDateTime"));
        subjectColumn.setCellValueFactory(new PropertyValueFactory<>("subject"));
        beforeTimeColumn.setCellValueFactory(new PropertyValueFactory<>("beforeSendSecond"));

        ObservableList<EmailSenderModel> senders = emailsTableView.getItems();
        senders.add(emailSenderModel);
        emailsTableView.setItems(senders);
    }

    void updateEmailTableView(){
        emailsTableView.getItems().setAll(emailSendersModel.emailSenderModel);
        emailsTableView.refresh();
    }

    void addEmailToEmailsSenderModel(EmailSenderModel emailSenderModel){
        emailSendersModel.emailSenderModel.add(emailSenderModel);
    }

    public void updateEmailBtnClick(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        addNewEmailToSenderForm(stage, EmailFormEnum.UPDATE_EMAIL_FORM);
        disableAndEnabledUpdateAndAddButtons(stage);

    }
}