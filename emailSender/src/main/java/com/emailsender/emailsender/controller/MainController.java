package com.emailsender.emailsender.controller;

import com.emailsender.emailsender.MainApplication;
import com.emailsender.emailsender.exception.ResourceNotFoundException;
import com.emailsender.emailsender.model.EmailFormEnum;
import com.emailsender.emailsender.model.EmailSenderModel;
import com.emailsender.emailsender.model.EmailSendersModel;
import com.emailsender.emailsender.service.EmailSenderService;
import com.emailsender.emailsender.service.MainTableService;
import com.emailsender.emailsender.utils.TableUtils;
import com.emailsender.emailsender.utils.Utils;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.scene.Scene;
import java.io.IOException;

public class MainController {
    @FXML
    public Button addEmailBtn;
    @FXML
    public Button updateEmailBtn;
    public Button deleteEmailBtn;
    public Button propertiesEmailBtn;
    @FXML
    public TableColumn<EmailSenderModel, String> senderColumn;
    @FXML
    public TableColumn<EmailSenderModel, String> receiverColumn;
    @FXML
    public TableColumn<EmailSenderModel, String> subjectColumn;
    @FXML
    public TableColumn<EmailSenderModel, String> desiredTimeColumn;
    @FXML
    public TableColumn<EmailSenderModel, Double>  countDownColumn;
    @FXML
    public TableColumn<EmailSenderModel, String>  statusColumn;
    @FXML
    private TableView<EmailSenderModel> emailsTableView;

    static EmailSendersModel emailSendersModel = new EmailSendersModel();
    private final EmailSenderService emailSenderService = new EmailSenderService();

    @FXML
    public void initialize(){
        Utils.fillEmailSendersModelFromConfig(emailSendersModel); // Config is emails.txt
        addEmailsToEmailsListTable(emailSendersModel);
    }

    @FXML
    private void addEmailBtnClick(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        addNewEmailToSenderForm(stage, EmailFormEnum.ADD_EMAIL_FORM);
        disableAndEnabledUpdateAndAddButtons(stage);
    }

    @FXML
    private void clickDeleteButton(ActionEvent actionEvent){
        Utils.deleteEmailSenderFromEmailSendersModel(emailSendersModel, emailsTableView);
        TableUtils.deleteSelectedRows(emailsTableView);
        Utils.convertObjectListToStringAndWriteToFile(emailSendersModel.emailSenderModel);
    }


    private void addNewEmailToSenderForm(Stage stage, EmailFormEnum controllerName) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("email-view.fxml"));
        String titleName;
        switch (controllerName) {
            case ADD_EMAIL_FORM -> {
                AddEmailController emailAddController = new AddEmailController(
                        new EmailSenderModel(),
                        this,
                        stage);
                fxmlLoader.setController(emailAddController);
                titleName = "Add new email";
            }
            case UPDATE_EMAIL_FORM -> {
                EmailSenderModel selectedEmailSender = emailsTableView.getSelectionModel().getSelectedItem();
                UpdateEmailController emailUpdateController = new UpdateEmailController(
                        selectedEmailSender,
                        this,
                        stage);
                fxmlLoader.setController(emailUpdateController);
                titleName = "Update email";
            }
            default -> throw new ResourceNotFoundException(controllerName.toString());
        }

        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle(titleName);
        stage.setScene(scene);
        stage.show();
    }

    private void disableAndEnabledUpdateAndAddButtons(Stage stage){
        addEmailBtn.setDisable(true);
        updateEmailBtn.setDisable(true);
        stage.setOnCloseRequest(event -> {
            addEmailBtn.setDisable(false);
            updateEmailBtn.setDisable(false);
        });
    }

    void addEmailToEmailsListTable(EmailSenderModel emailSenderModel){
        senderColumn.setCellValueFactory(new PropertyValueFactory<>("senderEmail"));
        receiverColumn.setCellValueFactory(new PropertyValueFactory<>("receiverEmail"));
        desiredTimeColumn.setCellValueFactory(new PropertyValueFactory<>("desiredDateTime"));
        subjectColumn.setCellValueFactory(new PropertyValueFactory<>("subject"));
        countDownColumn.setCellValueFactory(new PropertyValueFactory<>("countDown"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));

        ObservableList<EmailSenderModel> senders = emailsTableView.getItems();
        senders.add(emailSenderModel);
        emailsTableView.setItems(senders);
    }

    private void addEmailsToEmailsListTable(EmailSendersModel emailSendersModel){
        for(EmailSenderModel emailSenderModel: emailSendersModel.emailSenderModel)
            addEmailToEmailsListTable(emailSenderModel);
    }

    void updateEmailTableView(){
        emailsTableView.getItems().setAll(emailSendersModel.emailSenderModel);
        emailsTableView.refresh();
        Utils.convertObjectListToStringAndWriteToFile(emailSendersModel.emailSenderModel);
    }

    void addEmailToEmailsSenderModel(EmailSenderModel emailSenderModel){
        emailSendersModel.emailSenderModel.add(emailSenderModel);
        Utils.convertObjectListToStringAndWriteToFile(emailSendersModel.emailSenderModel);

    }

    public void updateEmailBtnClick(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        addNewEmailToSenderForm(stage, EmailFormEnum.UPDATE_EMAIL_FORM);
        disableAndEnabledUpdateAndAddButtons(stage);
    }

    @FXML
    public void startThreadBtnBtnClick(ActionEvent actionEvent){
        emailSenderService.sendEmails(emailSendersModel);
        new MainTableService().startRefreshTable(emailsTableView);
    }

    @FXML
    public void stopThreadBtnBtnClick(ActionEvent actionEvent){
        emailSenderService.stopThreads(emailSendersModel);
    }
}