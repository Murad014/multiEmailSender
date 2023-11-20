package com.emailsender.emailsender.service;

import com.emailsender.emailsender.model.EmailSenderModel;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.TableView;
import javafx.util.Duration;

public class MainTableService {
    public void startRefreshTable(TableView<EmailSenderModel> emailSenderModelTableView){
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.millis(10), event -> updateTable(emailSenderModelTableView))
        );
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }
    private void updateTable(TableView<EmailSenderModel> emailSenderModelTableView){
        for(int i = 0; i < emailSenderModelTableView.getItems().size(); i++){
            EmailSenderModel senderModel = emailSenderModelTableView.getItems().get(i);
            emailSenderModelTableView.getItems().get(i).setCountDown(senderModel.getBeforeSendSecond());
        }
        emailSenderModelTableView.refresh();
    }


}
