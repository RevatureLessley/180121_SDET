package com.project.util;

	import java.util.Properties;    
	import javax.mail.*;    
	import javax.mail.internet.*;  
	
/**
 * Email For notification purpose but have issue with session conflict 	
 * @author haris
 *
 */
public class Mailer{  
	    public static void send(String to){  
	          
	    	System.out.println(to);
	    	to="harishkumarchandra@gmail.com";
	    		
	    	  String from = "harishkumarchandra@gmail.com";
	    	  String password = System.getenv("pass");
	    	  String sub = "Tuition Reimbursement Notification";
	    	  String msg = "Hi, Please login in to Tuition Reimbursement";
	 
	    	  //Get properties object    
	          Properties props = new Properties();    
	          props.put("mail.smtp.host", "smtp.gmail.com");    
	          props.put("mail.smtp.socketFactory.port", "465");    
	          props.put("mail.smtp.socketFactory.class",    
	                    "javax.net.ssl.SSLSocketFactory");    
	          props.put("mail.smtp.auth", "true");    
	          props.put("mail.smtp.port", "465");    
	          //get Session   
	          Session session = Session.getDefaultInstance(props,    
	           new javax.mail.Authenticator() {    
	           protected PasswordAuthentication getPasswordAuthentication() {    
	           return new PasswordAuthentication(from,password);  
	           }    
	          });    
	          //compose message    
	          try {    
	           MimeMessage message = new MimeMessage(session);    
	           message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));    
	           message.setSubject(sub);    
	           message.setText(msg);    
	           //send message  
	           Transport.send(message);    
	           System.out.println("message sent successfully");    
	          } catch (MessagingException e) {throw new RuntimeException(e);}    
	             
	    }  
	}  

