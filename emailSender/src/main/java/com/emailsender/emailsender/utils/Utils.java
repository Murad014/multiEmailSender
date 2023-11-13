package com.emailsender.emailsender.utils;

import com.emailsender.emailsender.config.ConfigHelper;
import com.emailsender.emailsender.model.EmailSenderModel;
import com.emailsender.emailsender.model.EmailSendersModel;

import java.util.List;

public class Utils {

    public static <T> void convertObjectListToStringAndWriteToFile(List<T> object){
        String stringJson = JsonUtils.convertObjectListToJsonString(object);
        FileUtils.writeStringToFile(stringJson, ConfigHelper.fileName);
    }

    public static void fillEmailSendersModelFromConfig(EmailSendersModel emailSendersModel){
        String emailSendersModelString = FileUtils.readStringFromFile(ConfigHelper.fileName);
        emailSendersModel.emailSenderModel = JsonUtils.convertJsonStringToObject(emailSendersModelString);
    }


}
