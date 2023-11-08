package com.emailsender.emailsender;

import java.util.Enumeration;
import java.util.Properties;

public class TestMain {

    public static void main(String... args){
        Properties props = new Properties();
        props.put("email", "quliyev.murad@yahoo.com");
        props.put("password", "4123jwldasd");

        Enumeration<Object> keys = props.keys();
        while(keys.hasMoreElements()){
            Object key = keys.nextElement();
            Object value = props.getProperty(key.toString());
            System.out.println(key + ": " + value);
        }
    }
}
