package com.emailsender.emailsender.utils;

import com.emailsender.emailsender.User;
import com.emailsender.emailsender.helper.AlertHelper;
import com.emailsender.emailsender.model.EmailSenderModel;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.scene.control.Alert;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class JsonUtils {


    public static <T> String convertObjectListToJsonString(List<T> listUsers){
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(listUsers);
        } catch (IOException e) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, "Convert Error",
                    "Error occurred while convert Object List JSON to String");
            e.printStackTrace();
            return null;
        }
    }

    public static List<EmailSenderModel> convertJsonStringToObject(String jsonString) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(jsonString, new TypeReference<>() {});
        } catch (IOException e) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, "Convert Error",
                    "Error occurred while convert Json String to Object");
            e.printStackTrace();
            return null;
        }
    }


}
