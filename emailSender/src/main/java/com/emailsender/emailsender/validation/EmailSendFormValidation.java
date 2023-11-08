package com.emailsender.emailsender.validation;

import com.emailsender.emailsender.exception.ValidationException;
import com.emailsender.emailsender.helper.AlertHelper;
import com.emailsender.emailsender.model.EmailSenderModel;
import javafx.scene.control.Alert;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailSendFormValidation {

    private static void checkValidEmail(String email, String errorMessageIfInvalid) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        if(!matcher.matches()) { // Invalid
            AlertHelper.showAlert(Alert.AlertType.ERROR, "Error", errorMessageIfInvalid);
            throw new ValidationException(errorMessageIfInvalid);
        }
    }

    private static void checkValidDateTime(String inputDateTime, String errorMessageIfInvalid) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
            LocalDateTime dateTime = LocalDateTime.parse(inputDateTime, formatter);
        } catch (RuntimeException e) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, "Error", errorMessageIfInvalid);
            throw new ValidationException(errorMessageIfInvalid);
        }
    }

    public static void isValidEmailForm(EmailSenderModel emailSenderModel){
        System.out.println(emailSenderModel);
        String senderEmail = emailSenderModel.getSenderEmail();
        String receiverEmail = emailSenderModel.getReceiverEmail();
        String desiredTime = emailSenderModel.getDesiredDateTime();
        checkValidEmail(senderEmail, "Sender email is invalid!");
        checkValidEmail(receiverEmail, "Receiver email is invalid!");
        checkValidDateTime(desiredTime, "Desire time is invalid! Format: yyyy-MM-dd HH:mm:ss.SSS");
    }




}
