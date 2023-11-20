package com.emailsender.emailsender.service;

import com.emailsender.emailsender.model.EmailSenderModel;
import com.emailsender.emailsender.model.EmailSendersModel;
import com.emailsender.emailsender.threads.EmailSenderThread;

public class EmailSenderService {

    public void sendEmails(EmailSendersModel emailSendersModel){
        for(EmailSenderModel emailSenderModel: emailSendersModel.emailSenderModel){
            EmailSenderThread emailSenderThread = new EmailSenderThread(emailSenderModel.getProperties(),
                    emailSenderModel);
            emailSenderThread.start();
        }
    }

    public void stopThreads(EmailSendersModel emailSendersModel){
        for(EmailSenderModel emailSenderModel: emailSendersModel.emailSenderModel)
            emailSenderModel.setStop(true);


    }


}
