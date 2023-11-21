package com.emailsender.emailsender.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class FileUtils {

    public static String readStringFromFile(String filePath) {
        try {
            File file = new File(filePath);
            return new ObjectMapper().readValue(file, String.class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void writeStringToFile(String content, String filePath) {
        try {
            File file = new File(filePath);
            new ObjectMapper().writeValue(file, content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
