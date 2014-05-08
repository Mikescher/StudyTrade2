package com.studytrade.studytrade2.model.mail;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import logging.STLog;

public class SendMail {

	public boolean sendRegisterMail(int userID) {
		try {
			String host = "smtp.strato.de";
			int port = 587;

			BufferedReader readerUSER = new BufferedReader(new InputStreamReader(System.in));
			System.out.print("User: ");
			String user = null;
			try {
				user = readerUSER.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
			BufferedReader readerPASSWORD = new BufferedReader(new InputStreamReader(System.in));
			System.out.print("Passwort: ");
			String pass = null;
			try {
				pass = readerPASSWORD.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
			Properties props = new Properties();
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.starttls.enable", "true");
			Session session = Session.getInstance(props);
			Transport transport = session.getTransport("smtp");
			transport.connect(host, port, user, pass);
			Address[] addresses = InternetAddress.parse("mailport@mikescher.de");
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(user));
			message.setRecipients(Message.RecipientType.TO, addresses);
			message.setSubject("Registrierung");
			message.setText("Herzlichen Glückwunsch, sie haben eine lebenslanges Abo für Jamba Klingeltöne abgeschlossen!!!");
			transport.sendMessage(message, addresses);
			transport.close();
			return true;
		} catch (AddressException e) {
			STLog.log(e);
			return false;
		} catch (NoSuchProviderException e) {
			STLog.log(e);
			return false;
		} catch (MessagingException e) {
			STLog.log(e);
			return false;
		}

	}

}
