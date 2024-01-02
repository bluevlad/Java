package com.willbes.platform.util.mail;

import java.io.File;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component("sendMail")
public class SendMail {
	@Autowired 
	private JavaMailSender mailSender;

	public void sendMail(String subject, String content, String fromUser, String[] toUser) {
		sendMail(subject, content, fromUser, toUser, null, null, null);
	}
	
	public void sendMail(String subject, String content, String fromUser, String[] toUser, String[] toCC) {
		sendMail(subject, content, fromUser, toUser, toCC, null, null);
	}
	
	public void sendMail(String subject, String content, String fromUser, String[] toUser, String[] toCC, String fileName, String filePath) {
		
		MimeMessage message = mailSender.createMimeMessage();
		
		try {
			MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");
			messageHelper.setSubject(subject);
			if(fileName != null) messageHelper.addAttachment(fileName, new File(filePath));
			messageHelper.setFrom(fromUser);
			messageHelper.setTo(toUser);
			if(toCC != null) messageHelper.setCc(toCC);
			messageHelper.setText(content, true);
			mailSender.send(message);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("SendMail Fail");
		}
	}
}