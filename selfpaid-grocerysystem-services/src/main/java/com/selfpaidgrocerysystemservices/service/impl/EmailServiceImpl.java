package com.selfpaidgrocerysystemservices.service.impl;

import java.io.File;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.selfpaidgrocerysystemservices.service.constants.SelfpaidConstants;

@Service
public class EmailServiceImpl {

	private static Logger logger = LoggerFactory.getLogger(EmailServiceImpl.class);

	@Autowired
	JavaMailSenderImpl emailSender;

	@Value("${spring.mail.fromaddress}")
	private String fromMail;

	@Value("${spring.mail.list}")
	private String mailingList;

	/*public JavaMailSender getJavaMailSender() {
		logger.info("Inside EmailServiceImpl:getJavaMailSender method");
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		mailSender.setHost("smtp.gmail.com");
		mailSender.setPort(587);

		mailSender.setUsername("vijaypeddy@gmail.com");
		mailSender.setPassword("vijay7388");

		Properties props = mailSender.getJavaMailProperties();
		props.put("mail.transport.protocol", "smtp");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.debug", "true");

		return mailSender;
	}*/

	public void sendReceiptAttachment(String subject, String text, String fileName) {
		try {
			MimeMessage message = emailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, true);

			helper.setSubject(subject);
			helper.setText(text);

			helper.setTo(InternetAddress.parse(mailingList));
			helper.setFrom(fromMail);

			helper.addAttachment(fileName, new FileSystemResource(new File(SelfpaidConstants.receiptPath+fileName)));

			emailSender.send(message);
		} catch(Exception e) {
			logger.error("Exception occured in sendMessageWithAttachment method" + e.getMessage());
		}
	}

}
