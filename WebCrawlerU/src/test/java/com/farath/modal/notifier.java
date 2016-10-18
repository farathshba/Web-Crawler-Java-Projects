package com.farath.modal;

import java.util.ArrayList;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class notifier 
{	
	private static Properties mailServerProperties;
	private static Session getMailSession;
	private static MimeMessage generateMailMessage;
	private static ArrayList<String> recipients;
	private static String subjectOfEmail;
	private static String bodyOfEmail;
 
	public notifier()
	{
		initilize();
		recipients = new ArrayList<String>();
	}
	
	private void initilize()
	{
		System.out.println("\n 1st ===> setup Mail Server Properties..");
		mailServerProperties = System.getProperties();
//		mailServerProperties.put("mail.smtp.port", "587");
//		mailServerProperties.put("mail.smtp.auth", "true");
//		mailServerProperties.put("mail.smtp.starttls.enable", "true");
		mailServerProperties.put("mail.smtp.port", "587");
		mailServerProperties.put("mail.smtp.auth", "true");
		mailServerProperties.put("mail.smtp.starttls.enable", "true");
		System.out.println("Mail Server Properties have been setup successfully..");
		bodyOfEmail = "";
	}
	
	public void assign()
	{
		try
		{
			if(bodyOfEmail.contains("http"))
			{
				System.out.println("\n\n 2nd ===> get Mail Session..");
				getMailSession = Session.getDefaultInstance(mailServerProperties, null);
				generateMailMessage = new MimeMessage(getMailSession);
			
				for(int i =0; i < recipients.size(); i++)
				{
					generateMailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(recipients.get(i)));
				}
				generateMailMessage.setSubject(subjectOfEmail);
				//	String emailBody = "Test email by Crunchify.com JavaMail API example. " + "<br><br> Regards, <br>Crunchify Admin";
			
				generateMailMessage.setContent(bodyOfEmail, "text/html");
				System.out.println("Mail Session has been created successfully..");
			}
		}
		catch(Exception e)
		{
			System.err.println(e.getMessage() + " caught @ notifier class in assign method");
		}
	}
	
	public boolean sendmail() 
	{
		boolean flag = false;
		if(bodyOfEmail.contains("http"))
		{			
			try
			{
				System.out.println("\n\n 3rd ===> Get Session and Send mail");
				Transport transport = getMailSession.getTransport("smtp");
		 
				// Enter your correct gmail UserID and Password
				// if you have 2FA enabled then provide App Specific Password
				//transport.connect("smtp.gmail.com", "MdAnfalAkkram@gmail.com", "qpwoei10");
				transport.connect("smtp.gmail.com", "farisoft@gmail.com", "dvzzrnkqsmxavzui");
				transport.sendMessage(generateMailMessage, generateMailMessage.getAllRecipients());
				transport.close();
				flag = true;
			}
			catch (MessagingException me)
			{
				flag = false;
				System.err.println(me.getMessage() + " caught @ notifier class in sendmail method");
			}
		}
		
		return flag;
	}
	
	public void setRecipient(String r)
	{
		recipients.add(r);
	}
	
	public void setSubj(String subj)
	{
		subjectOfEmail = subj; 
	}
	
	public void setBody(String b)
	{
		bodyOfEmail =  bodyOfEmail + b;
	}
}
