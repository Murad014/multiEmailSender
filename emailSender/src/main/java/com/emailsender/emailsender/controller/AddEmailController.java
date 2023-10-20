package com.emailsender.emailsender.controller;

import com.emailsender.emailsender.helper.MapHelper;
import com.emailsender.emailsender.model.EmailSenderModel;
import com.emailsender.emailsender.model.EmailSendersModel;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;


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

    private final EmailSenderModel emailSender;


    public AddEmailController(EmailSenderModel emailSender){
        this.emailSender = emailSender;
    }

    @FXML
    public void initialize(){

    }

    @FXML
    private void setEmailSender(){
        EmailSenderModel emailSenderModel = MapHelper.setEmailControllerFieldsToEmailSenderModel(this,
                emailSender);
        MainController.addEmailToEmailsListTable(emailSenderModel);
    }





}
