package com.emailsender.emailsender.controller;

import com.emailsender.emailsender.MainApplication;
import com.emailsender.emailsender.exception.ValidationException;
import com.emailsender.emailsender.helper.MapHelper;
import com.emailsender.emailsender.model.EmailSenderModel;
import com.emailsender.emailsender.model.EmailSendersModel;
import com.emailsender.emailsender.utils.Utils;
import com.emailsender.emailsender.validation.EmailSendFormValidation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.IOException;


public class AddEmailController {
    @FXML
    public TextField senderEmailTextField;
    @FXML
    public TextField receiverTextField;
    @FXML
    public PasswordField passwordTextField;
    @FXML
    public TextField desiredTimeTextField;
    @FXML
    public TextField beforeAtTextField;
    @FXML
    public TextField subjectEmailTextField;
    @FXML
    public TextArea contentTextField;
    @FXML
    public Button setBtn;
    @FXML
    public ListView<String> filesListView;


    private final ObservableList<String> sendFilesList = FXCollections.observableArrayList();

    private final EmailSenderModel emailSender;
    private final MainController mainController;


    public AddEmailController(EmailSenderModel emailSender,
                              MainController mainController){
        this.emailSender = emailSender;
        this.mainController = mainController;
    }

    @FXML
    public void initialize(){ }

    @FXML
    private void setEmailSenderBtnClick(){
        EmailSenderModel emailSenderModel = MapHelper.setEmailControllerFieldsToEmailSenderModel(this,
                emailSender);

        EmailSendFormValidation.isValidEmailForm(emailSenderModel);
        mainController.addEmailToEmailsListTable(emailSenderModel);
        mainController.addEmailToEmailsSenderModel(emailSenderModel);

    }

    @FXML
    private void addFileToFileListView(ActionEvent actionEvent){
        Stage primaryStage = new Stage();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open File");
        java.io.File selectedFile = fileChooser.showOpenDialog(primaryStage);
        if (selectedFile != null)
            sendFilesList.add(selectedFile.getAbsolutePath());

        filesListView.setItems(sendFilesList);
    }

    @FXML
    private void propertiesBtnClick(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("emailProperties-view.fxml"));
        PropertiesEmailController emailAddController = new PropertiesEmailController(emailSender);
        fxmlLoader.setController(emailAddController);
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setTitle("Properties");
        stage.setScene(scene);
        stage.show();

    }






}
