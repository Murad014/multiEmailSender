package com.emailsender.emailsender.helper;

import com.emailsender.emailsender.controller.AddEmailController;
import com.emailsender.emailsender.controller.UpdateEmailController;
import com.emailsender.emailsender.model.EmailSenderModel;
import javafx.collections.FXCollections;

import java.util.ArrayList;
import java.util.List;

public class MapHelper {

    public static EmailSenderModel setEmailControllerFieldsToEmailSenderModel(AddEmailController addEmailController,
                                                       EmailSenderModel emailSenderModel){

        emailSenderModel.setSenderEmail(addEmailController.senderEmailTextField.getText());
        emailSenderModel.setPassword(addEmailController.passwordTextField.getText());
        emailSenderModel.setReceiverEmail(addEmailController.receiverTextField.getText());
        emailSenderModel.setSubject(addEmailController.subjectEmailTextField.getText());
        emailSenderModel.setContent(addEmailController.contentTextField.getText());
        emailSenderModel.setBeforeSendSecond(Double.parseDouble(addEmailController.beforeAtTextField.getText()));
        emailSenderModel.setDesiredDateTime(addEmailController.desiredTimeTextField.getText());
        List<String> files = new ArrayList<>(addEmailController.filesListView.getItems());
        emailSenderModel.setFiles(files);


        return emailSenderModel;
    }
    public static EmailSenderModel setEmailControllerFieldsToEmailSenderModel(UpdateEmailController updateEmailController,
                                                                              EmailSenderModel emailSenderModel){

        emailSenderModel.setSenderEmail(updateEmailController.senderEmailTextField.getText());
        emailSenderModel.setPassword(updateEmailController.passwordTextField.getText());
        emailSenderModel.setReceiverEmail(updateEmailController.receiverTextField.getText());
        emailSenderModel.setSubject(updateEmailController.subjectEmailTextField.getText());
        emailSenderModel.setContent(updateEmailController.contentTextField.getText());
        emailSenderModel.setBeforeSendSecond(Double.parseDouble(updateEmailController.beforeAtTextField.getText()));
        emailSenderModel.setDesiredDateTime(updateEmailController.desiredTimeTextField.getText());
        List<String> files = new ArrayList<>(updateEmailController.filesListView.getItems());
        emailSenderModel.setFiles(files);


        return emailSenderModel;
    }

    public static void setControllerFieldsFromEmailSenderModel(UpdateEmailController addEmailController,
                                                               EmailSenderModel emailSenderModel){
        addEmailController.senderEmailTextField.setText(emailSenderModel.getSenderEmail());
        addEmailController.receiverTextField.setText(emailSenderModel.getReceiverEmail());
        addEmailController.subjectEmailTextField.setText(emailSenderModel.getSubject());
        addEmailController.passwordTextField.setText(emailSenderModel.getPassword());
        addEmailController.contentTextField.setText(emailSenderModel.getContent());
        addEmailController.beforeAtTextField.setText(
                String.valueOf(emailSenderModel.getBeforeSendSecond()));
        addEmailController.desiredTimeTextField.setText(emailSenderModel.getDesiredDateTime());
        addEmailController.filesListView.setItems(
                FXCollections.observableArrayList(emailSenderModel.getFiles()));

    }



}
