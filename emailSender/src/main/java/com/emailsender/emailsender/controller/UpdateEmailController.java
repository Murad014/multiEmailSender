package com.emailsender.emailsender.controller;

import com.emailsender.emailsender.MainApplication;
import com.emailsender.emailsender.helper.MapHelper;
import com.emailsender.emailsender.model.EmailSenderModel;
import com.emailsender.emailsender.validation.EmailSendFormValidation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.IOException;

public class UpdateEmailController {
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

    private final Stage stage;

    public UpdateEmailController(EmailSenderModel emailSender,
                                 MainController mainController, Stage stage){
        this.emailSender = emailSender;
        this.mainController = mainController;
        this.stage = stage;
    }

    @FXML
    public void initialize(){
        // Initialize the inputs of form
        MapHelper.setControllerFieldsFromEmailSenderModel(this, emailSender);
    }

    @FXML
    private void setEmailSenderBtnClick(){
        MapHelper.setEmailControllerFieldsToEmailSenderModel(this,
                emailSender);
        EmailSendFormValidation.isValidEmailForm(emailSender);
        mainController.updateEmailTableView();
        stage.close();

        mainController.addEmailBtn.setDisable(false);
        mainController.updateEmailBtn.setDisable(false);



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
