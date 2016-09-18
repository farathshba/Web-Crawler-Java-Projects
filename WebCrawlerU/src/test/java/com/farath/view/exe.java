package com.farath.view;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import com.farath.modal.*;
import com.farath.wrapper.mainCarWrapper;
import java.io.*;
import java.sql.*;


public class exe
{
	private static ArrayList<mainCarWrapper> container = new ArrayList<mainCarWrapper>();
	
	public static void collate(String url)
	{
		try
		{
			//	Read in the fucking URLs n Pass them to crawler to crawl for fucking shite
			crawler crawl = new crawler(url);
			dbStuffer dbStuff = new dbStuffer();
			
			ArrayList<mainCarWrapper> allCarObj = crawl.getCarObjects();
			
			for(int i=0; i<allCarObj.size(); i++)
			{
				if(dbStuff.queryDB(allCarObj.get(i).printURL()) != true)
				{
					//	If the DB doesn't contain the URLs from the crawled URLs
					//	Store into DB and send it to User 
					if(url.contains("Toyota") && allCarObj.get(i).printCar().contains("Toyota"))
					{
						//	System.out.println("Statements wrote >> " + dbStuff.insertSQL(allCarObj.get(i).printURL()));
						dbStuff.insertSQL(allCarObj.get(i).printURL().trim());
						container.add(allCarObj.get(i));
						System.out.println(i + " Toyotas");
					}
					else if(url.contains("Honda") && allCarObj.get(i).printCar().contains("Honda"))	
					{
						dbStuff.insertSQL(allCarObj.get(i).printURL().trim());
						container.add(allCarObj.get(i));
						System.out.println(i + " Hondas");
					}
					
				}
				//	If not, ignore the URLs
			}
		}
		catch(Exception se)
		{
			System.err.println(se.getMessage() + " occurred @ exe - collate method");
		}
	}
	
	public static void sendMail()
	{
		try
		{
			notifier not = new notifier();
			not.setRecipient("farisoft@gmail.com");
			not.setRecipient("russellsg888@gmail.com");
			not.setSubj("New Car Results");
			not.setBody("<strong>Greetings from Farath's Crawler,</strong><br>");
				
			for(int k=0; k<container.size(); k++)
			{
				not.setBody("<br>"+container.get(k).printCar() + " -> " + "http://www.sgcarmart.com/used_cars/" +container.get(k).printURL());
				not.setBody("<br>");
			}
		
			not.setBody("</br>END OF MESSAGE");
			
			not.assign();
			not.sendmail();
		}
		catch(Exception ae)
		{
			System.err.println(ae.getMessage() + " occurred @ exe - sendMail method");
		}		
	}
	
	
	public static void main(String[] args)
	{
		//	Read in the properties file
		Properties prop = new Properties();
		InputStream input = null;
		
		notifier not = new notifier();
		
		try
		{
			input = new FileInputStream("/usr/local/bin/url.properties");
			prop.load(input);
			
			collate(prop.getProperty("com.firstURL"));
			collate(prop.getProperty("com.secondURL"));
			sendMail();
		}
		catch(Exception e)
		{
			System.err.println(e.getMessage() + " @ exe.main");
		}
	}
}
