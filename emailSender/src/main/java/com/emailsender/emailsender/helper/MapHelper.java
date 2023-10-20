package com.emailsender.emailsender.helper;

import com.emailsender.emailsender.controller.AddEmailController;
import com.emailsender.emailsender.model.EmailSenderModel;

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



}
