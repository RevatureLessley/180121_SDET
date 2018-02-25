package com.trms.servlets;

import java.io.IOException;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

/**
 * Servlet implementation class SendEmailServlet
 */
public class SendEmailServlet extends HttpServlet {
	private static final Logger logger = Logger.getLogger(SendEmailServlet.class);
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
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

		String to = "lyndaxj9@gmail.com";
		String subject = "New Reimburse";
		Message msg = new MimeMessage(session);
		try {
			msg.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
			msg.setSubject(subject);
			msg.setText("Hi,\n\nHow are you?");

			// Send the message.
			Transport.send(msg);
			logger.info("Email Sent");
		} catch (MessagingException e) {
			logger.error(e.getMessage());
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
