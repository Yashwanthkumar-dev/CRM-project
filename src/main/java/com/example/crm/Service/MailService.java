package com.example.crm.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MailService {
    private final JavaMailSender mail;

    public void welcomeMail(
            String toMail,
            String toSubject,
            String toBody
    ) {
        SimpleMailMessage mailSender = new SimpleMailMessage();
        mailSender.setFrom("Yr375003@gmail.com");
        mailSender.setTo(toMail);
        mailSender.setText(toBody);
        mailSender.setSubject(toSubject);
        mail.send(mailSender);
        System.out.print("mail send successfully :" + toMail);
    }
}
