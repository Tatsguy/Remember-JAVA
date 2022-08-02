package Modelos;

import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class CreateEmail {
    public void enviarEmail() throws AddressException, MessagingException{
        String correo = "its.remember.time@gmail.com";
        String contra = "dlqryxgnmpxkbkrh";
        String correoDestino = "jarvis77vg@gmail.com";
        
        Properties p = new Properties();
        p.put("mail.smtp.host","smtp.gmail.com");
        p.setProperty("mail.smtp.starttls.enable", "true");
        p.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        p.setProperty("mail.smtp.port", "587");
        p.setProperty("mail.smtp.user",correo);
        p.setProperty("mail.smtp.auth", "true");
        Session s =Session.getDefaultInstance(p);
        
        MimeMessage mensaje = new MimeMessage(s);
        mensaje.setFrom(new InternetAddress(correo));
        mensaje.addRecipient(Message.RecipientType.TO, new InternetAddress(correoDestino));
        mensaje.setSubject("Prueba de envio de correo.");
        mensaje.setText("Mensaje enviado desde Java");
        
        Transport t = s.getTransport("smtp");
        t.connect(correo,contra);
        t.sendMessage(mensaje, mensaje.getAllRecipients());
        t.close();
    }
}