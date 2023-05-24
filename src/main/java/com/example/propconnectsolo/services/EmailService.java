package com.example.propconnectsolo.services;

import com.example.propconnectsolo.models.Note;
import com.example.propconnectsolo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service("mailService")
public class EmailService {

    private final UserRepository userDao;

    @Autowired
    public JavaMailSender emailSender;

    @Value("${spring.mail.from}")
    private String from;

    public EmailService(UserRepository userDao) {
        this.userDao = userDao;
    }

    public void prepareAndSend(Note note) {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setFrom(from);
//        msg.setTo(note.getUser().getEmail());
        msg.setTo(note.getProperty().getUser().getEmail());
        msg.setSubject("Post Created/Edited");
        msg.setText(String.format("Post category: '%s'%n Post title: '%s'%n Post description: '%s'%n Post date: '%s'", note.getCategory(),note.getTitle(), note.getBody(), note.getDate()));

        try{
            this.emailSender.send(msg);
        }
        catch (MailException ex) {
            // simply log it and go on...
            System.err.println(ex.getMessage());
        }
    }

}
