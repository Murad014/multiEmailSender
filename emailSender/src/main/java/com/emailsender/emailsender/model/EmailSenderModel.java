package com.emailsender.emailsender.model;


import java.util.List;
import java.util.Properties;

public class EmailSenderModel {

    private String senderEmail;
    private String receiverEmail;
    private String password;
    private String subject;
    private String content;
    private List<String> files;
    private String desiredDateTime;
    private double beforeSendSecond;
    private Properties properties = new Properties();
    private boolean isStop;
    private String status;
    private String sentAtTime;

    public EmailSenderModel(){}

    public boolean isStop() {
        return isStop;
    }

    public void setStop(boolean stop) {
        isStop = stop;
    }

    public EmailSenderModel(String senderEmail,
                            String receiverEmail,
                            String password,
                            String subject,
                            String content,
                            List<String> files,
                            String desiredDateTime,
                            double beforeSendSecond,
                            String status,
                            boolean isStop,
                            String sentAtTime) {
        this.senderEmail = senderEmail;
        this.receiverEmail = receiverEmail;
        this.password = password;
        this.subject = subject;
        this.content = content;
        this.files = files;
        this.desiredDateTime = desiredDateTime;
        this.beforeSendSecond = beforeSendSecond;
        this.status = status;
        this.isStop = isStop;
        this.sentAtTime = sentAtTime;

    }



    public Properties getProperties() {
        return properties;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    public String getSenderEmail() {
        return senderEmail;
    }

    public void setSenderEmail(String senderEmail) {
        this.senderEmail = senderEmail;
    }

    public String getReceiverEmail() {
        return receiverEmail;
    }

    public void setReceiverEmail(String receiverEmail) {
        this.receiverEmail = receiverEmail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<String> getFiles() {
        return files;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setFiles(List<String> files) {
        this.files = files;
    }

    public String getDesiredDateTime() {
        return desiredDateTime;
    }

    public void setDesiredDateTime(String desiredDateTime) {
        this.desiredDateTime = desiredDateTime;
    }

    public double getBeforeSendSecond() {
        return beforeSendSecond;
    }

    public void setBeforeSendSecond(double beforeSendSecond) {
        this.beforeSendSecond = beforeSendSecond;
    }

    public String getSentAtTime(){
        return sentAtTime;
    }

    public void setSentAtTime(String sentAtTime){
        this.sentAtTime = sentAtTime;
    }
    @Override
    public String toString() {
        return "EmailSenderModel{" +
                "senderEmail='" + senderEmail + '\'' +
                ", receiverEmail='" + receiverEmail + '\'' +
                ", password='" + password + '\'' +
                ", subject='" + subject + '\'' +
                ", content='" + content + '\'' +
                ", files=" + files +
                ", desiredDateTime='" + desiredDateTime + '\'' +
                ", beforeSendSecond=" + beforeSendSecond +
                '}';
    }


}
