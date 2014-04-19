package edu.iiitb.util;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import edu.iiitb.config.Config;
 
public class SendMailSSL {
	public static void sendEmail(String to , String name) {
		
		Config.loadProperties();
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");
 
		Session session = Session.getDefaultInstance(props,
			new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(Config.USERNAME,Config.MAILIDPASSWORD);
				}
			});
 
		try {
 
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(Config.EMAILID));
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(to));
			message.setSubject("FlipKart Team");
			message.setText("Dear "+name+" ," +
					"\n\n You have successfully register for Flipkart.com!");
 
			Transport.send(message);
 
			System.out.println("User Registration Done");
 
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
	public static void sendPassword(String to , String password) {
		
		Config.loadProperties();
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");
 
		Session session = Session.getDefaultInstance(props,
			new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(Config.USERNAME,Config.MAILIDPASSWORD);
				}
			});
 
		try {
 
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(Config.EMAILID));
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(to));
			message.setSubject("FlipKart Team");
			message.setText("Your password is "+password+"\n\n To avoid losing your password chose it carefully\n\n\n Flipkart Live Oak Team" );
			Transport.send(message);
  
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
	
public static void sendUpdatedEmail(String to , String name) {
		
		Config.loadProperties();
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");
 
		Session session = Session.getDefaultInstance(props,
			new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(Config.USERNAME,Config.MAILIDPASSWORD);
				}
			});
 
		try {
 
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(Config.EMAILID));
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(to));
			message.setSubject("FlipKart Team:updated email-id");
			message.setText("Dear "+name+" ," +
					"\n\n You have successfully changed your email address for Flipkart.com!");
 
			Transport.send(message);
 
			System.out.println("User updated email Done");
 
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
	public void sendMailReceiptGenerated(String recipientEmail, String name, String transactionId, int orderId) 
	{
		Config.loadProperties();
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");
 
		Session session = Session.getDefaultInstance(props,
			new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(Config.USERNAME,Config.MAILIDPASSWORD);
				}
			});
 
		try {
 
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(Config.EMAILID));
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(recipientEmail));
			message.setSubject("FlipKart Team : Order Summary ");
			message.setText("Dear "+name+" ," +
					"\n \n " +
					"\n   You have successfully placed order at flipkart. " +
					"\n   Your Order Id is : " + orderId  +
					"\n   Your Transaction Id is :  " + transactionId +
					"\n \n "+
					"\n   Your order will be delivered to you in 5-8 days." +
					"\n   Thank you for shopping with Flipkart." +
					"\n \n \n " +
					"----------" +
					"\n Thanks and Regards" +
					"\n Team Flipkart" );
 
			Transport.send(message);
 
			System.out.println("User updated email Done");
 
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
		
	}
}
