package org.project.peerpalapi.service;

import lombok.AllArgsConstructor;
import org.project.peerpalapi.entity.EmailDetails;
import org.project.peerpalapi.exceptions.auth.AuthException;
import org.project.peerpalapi.service.interfaces.EmailServiceInterface;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@AllArgsConstructor
public class EmailService implements EmailServiceInterface {

    private final JavaMailSender mailSender;

    @Override
    public void sendMail(EmailDetails emailDetails) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(emailDetails.getRecipient());
            message.setSubject(emailDetails.getSubject());
            message.setText(emailDetails.getMessageBody());
            message.setSentDate(new Date());
            message.setFrom("PeerPal");

            mailSender.send(message);

        } catch (MailException e) {
            throw new AuthException(500, "failed to send mail!");
        } catch (Exception e) {
            throw new AuthException(500, "something went wrong!");
        }
    }
}
