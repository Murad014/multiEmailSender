package com.emailsender.emailsender;

import com.emailsender.emailsender.config.ConfigHelper;
import com.emailsender.emailsender.model.EmailSenderModel;
import com.emailsender.emailsender.utils.FileUtils;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

public class TestMain {

    public static void main(String... args) throws ParseException {
        String dateTimeString = "2023-11-20 15:30:45.123";

        // Parse the string into LocalDateTime using a formatter
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
        LocalDateTime dateTime1 = LocalDateTime.parse(dateTimeString, formatter);

        // Subtract 6 seconds
        LocalDateTime newDateTime = dateTime1.minusSeconds(6);

        // Format the new date time back to the specified format
        String newDateTimeString = newDateTime.format(formatter);

        System.out.println(newDateTimeString);
    }

    private static String convertObjectListToJsonString(List<User> listUsers){
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(listUsers);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    private static String readStringFromFile(String filePath) {
        try {
            // Create a File object
            File file = new File(filePath);

            // Use Jackson to read the content of the file as a string
            return new ObjectMapper().readValue(file, String.class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }


    private static void stringToModel(){
        String jsonString = FileUtils.readStringFromFile(ConfigHelper.fileName);
        List<EmailSenderModel> userList = convertJsonStringToObject(jsonString);

        // Print the result
        assert userList != null;
        for (EmailSenderModel emailSenderModel : userList) {
            System.out.println("Email: " + emailSenderModel.getSenderEmail() + ", Password: " + emailSenderModel.getPassword());
        }
    }

    private static void writeStringToFile(String content, String filePath) {
        try {
            // Create a File object
            File file = new File(filePath);

            // Use Jackson to write the JSON string to the file
            new ObjectMapper().writeValue(file, content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static List<EmailSenderModel> convertJsonStringToObject(String jsonString) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(jsonString, new TypeReference<List<EmailSenderModel>>() {});
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}


