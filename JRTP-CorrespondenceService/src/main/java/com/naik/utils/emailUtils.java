package com.naik.utils;

import java.io.File;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import jakarta.mail.internet.MimeMessage;

@Component
public class emailUtils {
	@Autowired
	private static JavaMailSender mailSender;
	
	public static void sendmails(String toMail,String subject, String Body,File file) throws Exception {
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper =new MimeMessageHelper(message, true);
		helper.setTo(toMail);
		helper.setSentDate(new Date());
		helper.setSubject(subject);
		helper.setText(Body, true);;
		helper.addAttachment(file.getName(), file);
		mailSender.send(message);
	}
	
}
