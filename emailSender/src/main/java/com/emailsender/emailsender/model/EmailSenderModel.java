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

    public EmailSenderModel(){}

    public EmailSenderModel(String senderEmail,
                            String receiverEmail,
                            String password,
                            String subject,
                            String content,
                            List<String> files,
                            String desiredDateTime,
                            double beforeSendSecond) {
        this.senderEmail = senderEmail;
        this.receiverEmail = receiverEmail;
        this.password = password;
        this.subject = subject;
        this.content = content;
        this.files = files;
        this.desiredDateTime = desiredDateTime;
        this.beforeSendSecond = beforeSendSecond;

        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.mail.ru");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("smtp.mail.ru", "465");


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
