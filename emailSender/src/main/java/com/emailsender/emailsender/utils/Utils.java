package com.emailsender.emailsender.utils;

import com.emailsender.emailsender.config.ConfigHelper;
import com.emailsender.emailsender.model.EmailSenderModel;
import com.emailsender.emailsender.model.EmailSendersModel;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;

import java.util.List;

public class Utils {

    public static <T> void convertObjectListToStringAndWriteToFile(List<T> object){
        String stringJson = JsonUtils.convertObjectListToJsonString(object);
        FileUtils.writeStringToFile(stringJson, ConfigHelper.fileName);
    }

    public static void fillEmailSendersModelFromConfig(EmailSendersModel emailSendersModel){
        String emailSendersModelString = FileUtils.readStringFromFile(ConfigHelper.fileName);
        emailSendersModel.emailSenderModel = JsonUtils.convertJsonStringToObject(emailSendersModelString);
    }

    public static <T> void deleteEmailSenderFromEmailSendersModel(EmailSendersModel emails,
                                                              TableView<T> tableView){
        ObservableList<T> selectedItems = tableView.getSelectionModel().getSelectedItems();
        emails.emailSenderModel.remove(selectedItems.get(0));
    }




}
