package com.emailsender.emailsender;

import com.emailsender.emailsender.config.ConfigHelper;
import com.emailsender.emailsender.model.EmailSenderModel;
import com.emailsender.emailsender.utils.FileUtils;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class TestMain {

    public static void main(String... args){
//        List<User> users = List.of(
//                new User("murad@asdasd.az", "asdqwdqd"),
//                new User("asdasd@asdasd.com", "asdaddqwed2")
//        );
//
//        String jsonString = convertObjectListToJsonString(users);
//        jsonString = readStringFromFile("output.txt");
//
//        // Write the JSON string to a text file
////        writeStringToFile(jsonString, "output.txt");
//
//        List<User> userList = convertJsonStringToObject(jsonString);
//        assert userList != null;
//        for (User user : userList) {
//            System.out.println("Email: " + user.getEmail() + ", Password: " + user.getPassword());
//        }
        stringToModel();
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


