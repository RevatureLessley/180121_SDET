package com.trms.util;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;

public class SendEmail {
	private static final Logger logger = Logger.getLogger(SendEmail.class);
	
	public static void sendEmail(String inTo, String inSubject, String inMsg) {
		String[] email = System.getenv("TRMSEMAIL").split(";");
		java.util.Properties props = new java.util.Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
		
		Authenticator authenticator = new Authenticator() {
		    protected PasswordAuthentication getPasswordAuthentication() {
		        return new PasswordAuthentication(email[0], email[1]);
		    }
		};

		Session session = Session.getInstance(props, authenticator);
		
		String to = inTo;
		String subject = inSubject;
		Message msg = new MimeMessage(session);
		try {
			msg.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
			msg.setSubject(subject);
			String message = inMsg;
			msg.setText(message);

			// Send the message.
			Transport.send(msg);
			logger.info("Email Sent");
		} catch (MessagingException e) {
			logger.error(e.getMessage());
		}
	}
}
