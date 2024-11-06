package com.naik.utils;

import java.io.File;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import jakarta.mail.internet.MimeMessage;

@Component
public class emailUtils {

	@Autowired
	private JavaMailSender sender;
	public void sendmailS(String toMail,String subject, String Body,File file) throws Exception {
		MimeMessage message = sender.createMimeMessage();
		MimeMessageHelper helper =new MimeMessageHelper(message, true);
		helper.setTo(toMail);
		helper.setSentDate(new Date());
		helper.setSubject(subject);
		helper.setText(Body);
		helper.addAttachment(file.getName(), file);
		sender.send(message);
	}
	
}
