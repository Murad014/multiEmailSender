package com.emailsender.emailsender.helper;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CommonHelper {

    public String minusBeforeTimeFromDesiredTime(String userInput, Double totalSecondsToSubtract){
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime parsedDateTime = LocalDateTime.parse(userInput, inputFormatter);
        long totalNanosToSubtract = (long) (totalSecondsToSubtract * 1_000_000_000);
        LocalDateTime resultDateTime = parsedDateTime.minusNanos(totalNanosToSubtract);
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");

        return resultDateTime.format(outputFormatter);
    }




}
