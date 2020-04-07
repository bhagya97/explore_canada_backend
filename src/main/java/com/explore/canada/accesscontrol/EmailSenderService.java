package com.explore.canada.accesscontrol;
import java.nio.charset.StandardCharsets;
import java.util.Properties;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import com.explore.canada.beans.Mail;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

public class EmailSenderService {

    private JavaMailSenderImpl emailSender = new JavaMailSenderImpl();
    private TemplateConfig htmlTemplateEngine = new TemplateConfig();

    String HOST = "smtp.gmail.com";
    int PORT = 587;
    String PROTOCOL = "smtp";
    String USERNAME = "akki007.singh@gmail.com";
    String PASSWORD = "rafccitrvzozfkrr";

    public void sendEmail(Mail mail) {
        try {
            // Basic mail sender configuration, based on emailconfig.properties
            emailSender.setHost(HOST);
            emailSender.setPort(PORT);
            emailSender.setProtocol(PROTOCOL);
            emailSender.setUsername(USERNAME);
            emailSender.setPassword(PASSWORD);
            Properties mailProperties = new Properties();
            mailProperties.put("mail.smtp.auth", false);
            mailProperties.put("mail.smtp.starttls.enable", true);
            mailProperties.put("mail.smtp.timeout", 5000);
            mailProperties.put("mail.smtp.connectiontimeout", 5000);
            emailSender.setJavaMailProperties(mailProperties);

            MimeMessage message = emailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message,
                    MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                    StandardCharsets.UTF_8.name());

            String html = htmlTemplateEngine.process("/templates/notification-template.html", mail.getProps());
            helper.setTo(mail.getMailTo());
            helper.setText(html, true);
            helper.setSubject(mail.getSubject());
            helper.setFrom(mail.getFrom());
            emailSender.send(message);
        }
        /*
        catch (IOException ioEx){
            System.out.println(ioEx);
        }
         */
        catch (MessagingException msgEx){
            System.out.println(msgEx);
        }
        catch (Exception ex){
            System.out.println(ex);
        }

    }
}
