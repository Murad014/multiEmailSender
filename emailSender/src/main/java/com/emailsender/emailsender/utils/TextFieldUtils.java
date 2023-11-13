package com.emailsender.emailsender.utils;

import javafx.scene.control.TextField;

import java.util.List;

public class TextFieldUtils {

    public static void clearTextFields(TextField... textFields) {
        for(TextField textField : textFields)
            textField.clear();
    }
}
