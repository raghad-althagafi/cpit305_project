/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg305_project;

/**
 *
 * @author AHC
 */

import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;
import java.util.List;

public class Email {
    private static String to ;
    private static final String from = "kauevents102@gmail.com";
    private static final String host = "smtp.gmail.com";
    
  
    public static void sendEmail(List<String> users,String eventName,String date,String time,String location) {
       
        String body =  "We are excited to inform you that a new event has been added:\n"
                + "\n"
                + "Event Name: "+eventName+"\n"
                + "Date: "+date+"\n"
                + "Time: "+time+"\n"
                + "Location: "+location+"\n"
                + "\n"
                + "\n"
                + "\n"
                + "Best regards,\n"
                + "KAU events";
        
      
        
        Properties properties = System.getProperties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.ssl.protocols", "TLSv1.2");

        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("kauevents102@gmail.com", "uosy okxk nixr neoh");
            }
        });

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
   
            for (String recipient : users) {
                message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
            }

            message.setSubject("\"New Event Added: "+eventName+" on "+date+"\"");
            message.setText(body);

            Transport.send(message);
            System.out.println("Email sent successfully to multiple recipients.");

        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }

    
}
