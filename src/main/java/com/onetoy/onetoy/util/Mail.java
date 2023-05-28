package com.onetoy.onetoy.util;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
public class Mail {

    public Mail() {

    }

    public boolean send(String id) {

        String host = "smtp.naver.com";
        String user = "duawptjd99@naver.com";
        String password = "dua62036420~";
        Properties props = new Properties();
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", 465);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.ssl.enable", "true");
        props.put("mail.smtp.ssl.trust", "smtp.naver.com");

        String split[] = id.split("@");
        String originalId = split[0];

        Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(user, password);
            }
        });
        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(user));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(id));
            message.setSubject("Forgot Password");
            message.setContent("<h1>Link for Forgot Password</h1><a href='http://localhost:8080/fixpassword?id="+id+"'>click link</a>", "text/html");
        //    message.setText("테스트 입니다.");
            Transport.send(message);
            System.out.println("Success Message Send");
            return true;
        } catch (MessagingException e) {
            e.printStackTrace();
            return false;
        }
    }
}

