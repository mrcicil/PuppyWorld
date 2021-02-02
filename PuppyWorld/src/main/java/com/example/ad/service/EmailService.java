package com.example.ad.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
private JavaMailSender javaMailSender;
	
	@Autowired
	public EmailService(JavaMailSender javaMailSender) {
		this.javaMailSender=javaMailSender;
	}

	public void sendNotification(String msg, String userEmail) throws MailException{
		
		SimpleMailMessage mail=new SimpleMailMessage();
		mail.setTo(userEmail);
		mail.setFrom("keyint94@gmail.com");
		mail.setSubject("New Password");
		mail.setText(msg);
		
		
		javaMailSender.send(mail);
	}

}
