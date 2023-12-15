package com.emailsender.emailsender.utils;

import com.emailsender.emailsender.config.ConfigHelper;
import com.emailsender.emailsender.model.EmailSenderModel;
import com.emailsender.emailsender.model.EmailSendersModel;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Utils {

    public static <T> void convertObjectListToStringAndWriteToFile(List<T> object){
        String stringJson = JsonUtils.convertObjectListToJsonString(object);
        FileUtils.writeStringToFile(stringJson, ConfigHelper.fileName);
    }

    public static void fillEmailSendersModelFromConfig(EmailSendersModel emailSendersModel){
        String emailSendersModelString = FileUtils.readStringFromFile(ConfigHelper.fileName);
        if(emailSendersModelString != null)
            emailSendersModel.emailSenderModel = JsonUtils.convertJsonStringToObject(emailSendersModelString);
    }

    public static <T> void deleteEmailSenderFromEmailSendersModel(EmailSendersModel emails,
                                                              TableView<T> tableView){
        ObservableList<T> selectedItems = tableView.getSelectionModel().getSelectedItems();
        emails.emailSenderModel.remove(selectedItems.get(0));
    }

    public static String minusBeforeAtFromDesiredTime(String desiredTime, double beforeAtTime){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
        LocalDateTime dateTime1 = LocalDateTime.parse(desiredTime, formatter);
        LocalDateTime newDateTime = dateTime1.minusSeconds((long) beforeAtTime);

        return newDateTime.format(formatter);
    }




}
