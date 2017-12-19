package com.spring.test.web.service;

import java.nio.charset.StandardCharsets;

import javax.mail.Message;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
//import org.thymeleaf.spring4.SpringTemplateEngine;

import com.spring.test.web.dao.Mail;

@Service
	public class EmailService {

	    @Autowired
	    private JavaMailSender mailSender;

	  //  @Autowired
	    //private SpringTemplateEngine templateEngine;

	    public void sendEmail(Mail mail) {
	        try {
	            MimeMessage message = mailSender.createMimeMessage();
	            

	         //   Context context = new Context();
	           // context.setVariables(mail.getModel());
	         //   String html = templateEngine.process("email/email-template", context);

	            message.setRecipients(Message.RecipientType.TO,mail.getTo());
	          //  helper.setText(html, true);
	            message.setSubject(mail.getSubject());
	            message.setFrom(mail.getFrom());
	            message.setText("To reset your password, click the link below:\n" + mail.getModel().get("resetUrl"));

	            mailSender.send(message);
	        } catch (Exception e){
	        	System.out.println("EEEEE");
	            throw new RuntimeException(e);
	        }
	    }

	}


