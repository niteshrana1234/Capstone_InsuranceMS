package com.takeo.utils;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class EmailSender {
    public static String sendOtp(String email) {

        boolean flag = false;

        // Email configuration
        String host = "smtp.gmail.com";
        String username = "niteshrana1234@gmail.com";
        String password = "yolg vfbd lpwk iovv";
        int port = 587;

        // Sender and recipient email addresses
        String senderEmail = "niteshrana1234@gmail.com";
        String recipientEmail = email;

        // Email subject and body
        String subject = "Verification Code";
        String otp = OtpGenerator.getOtp();
        String body = " OTP : " + otp;

        // Set the mail properties
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", port);

        // Create a Session object with the authentication credentials
        javax.mail.Session session = javax.mail.Session.getInstance(properties, new javax.mail.Authenticator() {
            protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                return new javax.mail.PasswordAuthentication(username, password);
            }

            /*
             * @Override public boolean authenticate(HttpServletRequest request,
             * HttpServletResponse response) throws IOException { // TODO Auto-generated
             * method stub return false; }
             *
             * @Override public void login(String userName, String password, Request
             * request) throws ServletException { // TODO Auto-generated method stub
             *
             * }
             *
             * @Override public void logout(Request request) { // TODO Auto-generated method
             * stub
             *
             * }
             */
        });

        try {
            // Create a MimeMessage object
            javax.mail.internet.MimeMessage message = new javax.mail.internet.MimeMessage(session);

            // Set the sender and recipient addresses
            message.setFrom(new javax.mail.internet.InternetAddress(senderEmail));
            message.addRecipient(javax.mail.Message.RecipientType.TO, new javax.mail.internet.InternetAddress(recipientEmail));

            // Set the email subject and body
            message.setSubject(subject);
            message.setText(body);

            // Send the email

            javax.mail.Transport.send(message);
            flag = true;
        } catch (javax.mail.MessagingException e) {
            e.getMessage();
        }

        if (flag) {
            return otp;
        }
        return null;
    }

    public static String sendRandomPassword(String email, String passCode) {

        boolean flag = false;

        // Email configuration
        String host = "smtp.gmail.com";
        String username = "niteshrana1234@gmail.com";
        String password = "yolg vfbd lpwk iovv";
        int port = 587;
        // Sender and recipient email addresses
        String senderEmail = "niteshrana1234@gmail.com";
        String recipientEmail = email;

        // Email subject and body
        String subject = "Password";
        String body = " Password : " + passCode;

        // Set the mail properties
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", port);

        // Create a Session object with the authentication credentials
        javax.mail.Session session = Session.getInstance(properties, new Authenticator() {
            protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            // Create a MimeMessage object
            javax.mail.internet.MimeMessage message = new MimeMessage(session);

            // Set the sender and recipient addresses
            message.setFrom(new javax.mail.internet.InternetAddress(senderEmail));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipientEmail));

            // Set the email subject and body
            message.setSubject(subject);
            message.setText(body);

            // Send the email

            Transport.send(message);
            flag = true;
        } catch (MessagingException e) {
            e.getMessage();
        }

        if (flag) {
            return passCode;
        }
        return null;
    }
    public static String sendNewPassword(String email,String newPassword) {

        boolean flag = false;

        // Email configuration
        String host = "smtp.gmail.com";
        String username = "niteshrana1234@gmail.com";
        String password = "yolg vfbd lpwk iovv";
        int port = 587;

        // Sender and recipient email addresses
        String senderEmail = "niteshrana1234@gmail.com";
        String recipientEmail = email;

        // Email subject and body
        String subject = "Password changed";
        String otp = newPassword;
        String body = " New password : " + otp;

        // Set the mail properties
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", port);

        // Create a Session object with the authentication credentials
        javax.mail.Session session = javax.mail.Session.getInstance(properties, new javax.mail.Authenticator() {
            protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                return new javax.mail.PasswordAuthentication(username, password);
            }

            /*
             * @Override public boolean authenticate(HttpServletRequest request,
             * HttpServletResponse response) throws IOException { // TODO Auto-generated
             * method stub return false; }
             *
             * @Override public void login(String userName, String password, Request
             * request) throws ServletException { // TODO Auto-generated method stub
             *
             * }
             *
             * @Override public void logout(Request request) { // TODO Auto-generated method
             * stub
             *
             * }
             */
        });

        try {
            // Create a MimeMessage object
            javax.mail.internet.MimeMessage message = new javax.mail.internet.MimeMessage(session);

            // Set the sender and recipient addresses
            message.setFrom(new javax.mail.internet.InternetAddress(senderEmail));
            message.addRecipient(javax.mail.Message.RecipientType.TO, new javax.mail.internet.InternetAddress(recipientEmail));

            // Set the email subject and body
            message.setSubject(subject);
            message.setText(body);

            // Send the email

            javax.mail.Transport.send(message);
            flag = true;
        } catch (javax.mail.MessagingException e) {
            e.getMessage();
        }

        if (flag) {
            return otp;
        }
        return null;
    }
}
