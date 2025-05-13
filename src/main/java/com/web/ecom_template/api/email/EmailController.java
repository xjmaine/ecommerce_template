package com.web.ecom_template.api.email;

import com.web.ecom_template.custom_exception.EmailFailureException;
import com.web.ecom_template.custom_exception.HttpStatusCodes;
import com.web.ecom_template.repository.policies.CustomAnnotation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

@Slf4j
@RestController
@RequestMapping("/api/v1/email")
@Tag(name="Email Handler", description = "java email handler")
@CustomAnnotation()
public class EmailController {
    EmailFailureException emailFailureException;
    HttpStatusCodes httpStatusCodes;
    private final JavaMailSender javaMailSender;

    public EmailController(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @RequestMapping("/send")
    public String sendEmail() {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo("clarikadigital@gmail.com");
            message.setSubject("Test Email");
            message.setText("This is a test email body.");

            javaMailSender.send(message);
        } catch (Exception e) {
            log.error("e: ", e);
            System.err.println("e: " + e);
            return emailFailureException.MAIL_NOT_SENT;
        }
        return "Email sent";
    }

    //send email with attachment
    @PostMapping("/send")
    public String sendEmailWithAttmnt() throws MessagingException {
        try {

            MimeMessage message = javaMailSender.createMimeMessage(); //mimemessage

            MimeMessageHelper helper = new MimeMessageHelper(message, true); //mime helper

            helper.setFrom("clarikadigital@gmail.com");
            helper.setTo("clarikadigital@gmail.com");
            helper.setSubject("Test Email with attachment");
            helper.setText("This is a test email body with some attachment.");
            helper.addAttachment("Reg. Dir - GAR", new File("C:\\Users\\piper\\Downloads\\recovery\\dir.jpg"));
            helper.addAttachment("Reg. Dir - GAR", new File("C:\\Users\\piper\\Downloads\\Senior_Lead_Principal Software Engineer (Remote).pdf"));
            javaMailSender.send(message);

        } catch (MailException e) {
            log.error("e: ", e);
            System.err.println("e: " + e);
            return emailFailureException.MAIL_NOT_SENT;
        }
        return "Email sent";
    }

    //send email with html bpdy
    @PostMapping("/send-parser")
    public String sendEmailWitHtmlValue() throws MessagingException {
        try {

            MimeMessage message = javaMailSender.createMimeMessage(); //mimemessage

            MimeMessageHelper helper = new MimeMessageHelper(message, true); //mime helper

            helper.setFrom("clarikadigital@gmail.com");
            helper.setTo("clarikadigital@gmail.com");
            helper.setSubject("Customer Support");

            String htmlContent;
            try (var inputStream = Objects.requireNonNull(EmailController.class.getResourceAsStream("/templates/emails/email-content.html"))) {
                htmlContent = new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
                htmlContent = htmlContent.replace("{{company_name}}", "BG Day Spring Ltd")
                        .replace("{{customer_name}}", "Dear Customer")
                        .replace("{{discount}}", "10")
                        .replace("{{shop_link}}", "https://bgdayspringltd.com/shop")
                        .replace("{{unsubscribe_link}}", "https://bgdayspringltd.com/unsubscribe");

                helper.setText(htmlContent, true);

                //match CID in HTML to logo
                File logoFile = new File("src/main/resources/static/emails/images/logo.png");
                if (logoFile.exists()) {
                    helper.addInline("logo", logoFile);
                } else {
                    log.warn("logo file not found at {}", logoFile.getAbsolutePath());
                }

            } catch (Exception e) {
                throw new RuntimeException(e);
            }

            //add logo inline
            helper.addInline("logo", new File("src/main/resources/static/emails/images/logo.png"));

            javaMailSender.send(message);

        } catch (MailException e) {
            log.error("e: ", e);
            System.err.println("e: " + e);
            return emailFailureException.MAIL_NOT_SENT;
        }
        return "Email sent";
    }
}
