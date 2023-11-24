package com.emailsender.emailsender.controller;

import com.emailsender.emailsender.model.EmailSenderModel;
import com.emailsender.emailsender.utils.TextFieldUtils;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.util.Enumeration;
import java.util.Map;
import java.util.Properties;

public class PropertiesEmailController {

    @FXML
    public TextField keyPropertyTextField;
    @FXML
    public TextField valuePropertyTextField;
    @FXML
    private final EmailSenderModel emailSenderModel;

    public PropertiesEmailController(EmailSenderModel emailSenderModel){
        this.emailSenderModel = emailSenderModel;
    }
    @FXML
    public TableColumn<Properties, String> keyPropertyCol;
    @FXML
    public TableColumn<Properties, String> valuePropertyCol;
    @FXML
    private TableView<Map.Entry<Object, Object>> propertiesTableView;

    @FXML
    public void initialize(){
        addElementsToPropertiesTableView();
        propertiesTableView.setOnKeyPressed(this::handleKeyPressed);

    }

    @FXML
    public void addPropertyBtnClick(ActionEvent actionEvent) {
        addPropertyToEmailSenderModelProperties();
        addElementsToPropertiesTableView();
        clearInputTextFields();
    }

    private void clearInputTextFields(){
        TextFieldUtils.clearTextFields(keyPropertyTextField, valuePropertyTextField);
    }

    private void addElementsToPropertiesTableView(){
        ObservableList<Map.Entry<Object, Object>> properties = FXCollections.observableArrayList(
                emailSenderModel.getProperties().entrySet());

        keyPropertyCol.setCellValueFactory(cellData -> {
            Map.Entry<Object, Object> entry = (Map.Entry<Object, Object>) cellData.getValue();
            return new SimpleStringProperty(entry.getKey().toString());
        });

        valuePropertyCol.setCellValueFactory(cellData -> {
            Map.Entry<Object, Object> entry = (Map.Entry<Object, Object>) cellData.getValue();
            return new SimpleStringProperty(entry.getValue().toString());
        });

        propertiesTableView.setItems(properties);

    }

    private void addPropertyToEmailSenderModelProperties(){
        String key = keyPropertyTextField.getText();
        String value = valuePropertyTextField.getText();
        emailSenderModel.getProperties().put(key, value);
    }

    private void handleKeyPressed(KeyEvent event) {
        if (event.getCode() == KeyCode.DELETE) {
            deleteSelectedRowAndFromEmailSenderProp();
        }
    }
    private void deleteSelectedRowAndFromEmailSenderProp() {
        Map.Entry<Object, Object> selectedProperty = propertiesTableView.getSelectionModel().getSelectedItem();
        if (selectedProperty != null) {
            emailSenderModel.getProperties().remove(selectedProperty.getKey());
            addElementsToPropertiesTableView();
        }

    }


}
