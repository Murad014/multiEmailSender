package com.emailsender.emailsender.threads;

import com.emailsender.emailsender.model.EmailSenderModel;
import com.emailsender.emailsender.utils.Utils;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class EmailSenderThread extends Thread{
    private final Properties properties;
    private final EmailSenderModel senderModel;

    public EmailSenderThread(Properties properties, EmailSenderModel senderModel) {
        this.properties = properties;
        this.senderModel = senderModel;
    }


    @Override
    public void run(){
        try {
            waitForFinishCountdown();
            sendEmail(senderModel, properties);
        } catch (ParseException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void waitForFinishCountdown() throws ParseException, InterruptedException {
        System.out.println("waitForFinishCountdown... ");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date desiredTime = dateFormat.parse(Utils.minusBeforeAtFromDesiredTime(senderModel.getDesiredDateTime(),
                senderModel.getBeforeSendSecond()));

        long timeDiff = (desiredTime.getTime() - new Date().getTime()) / 100;

        while(timeDiff > 0){
            if(senderModel.isStop()) {
                System.out.println("Stopped thread. Email: " + senderModel.getSenderEmail());
                senderModel.setStatus("STOPPED");
                throw new RuntimeException("Stopped thread. Email: " + senderModel.getSenderEmail());
            }
            Thread.sleep(0);
            senderModel.setCountDown(timeDiff);
            timeDiff = (desiredTime.getTime() - new Date().getTime()) / 100;
        }
    }


    private void sendEmail(EmailSenderModel sender,
                           Properties properties) {
        System.out.println("sendEmail method started... Email: " + sender.getSenderEmail() + " - " + LocalDateTime.now());
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(sender.getSenderEmail(), sender.getPassword());
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(sender.getSenderEmail()));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(sender.getReceiverEmail()));
            message.setSubject(sender.getSubject());

            MimeMultipart multipart = new MimeMultipart();

            MimeBodyPart textPart = new MimeBodyPart();
            textPart.setText(sender.getContent());
            multipart.addBodyPart(textPart);

            for(String filePath: sender.getFiles()) {
                MimeBodyPart attachmentPart = new MimeBodyPart();
                attachmentPart.attachFile(new File(filePath));
                multipart.addBodyPart(attachmentPart);
            }

            message.setContent(multipart);
            Transport.send(message);
            sender.setStatus("SUCCESS");
            sender.setSentAtTime(LocalDateTime.now().toString());

            System.out.println("Email sent successfully from " + sender.getSenderEmail() + " with attachment");
            System.out.println("sendEmail method Email: " + sender.getSenderEmail());
            System.out.println("sendEmail method sent time: " + LocalDateTime.now());
            System.out.println("sendEmail method  Desired Time: " + sender.getDesiredDateTime());
            System.out.println("--------------------------------------------------------------------");
        } catch (MessagingException | IOException e) {
            sender.setStatus("FAILED");
            System.out.println("Error sending email from " + sender.getSenderEmail() + ": " + e.getMessage());
        }


    }

}
