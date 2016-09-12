package com.farath.view;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Properties;
import com.farath.modal.*;
import java.io.*;

public class exe
{
	public static void routine(String url)
	{
		//	Read in the fucking URLs n Pass them to crawler to crawl for fucking shite
		crawler crawl = new crawler(url);
				
		//	Get the info and package them as fucking data to be sent to User
		System.out.println(crawl.getCar());
	}
	public static void main(String[] args)
	{
		//	Read in the properties file
		Properties prop = new Properties();
		InputStream input = null;
		
		try
		{
			input = new FileInputStream("URL.properties");
			
			routine(prop.getProperty("com.firstURL"));
			routine(prop.getProperty("com.secondURL"));
		}
		catch(IOException err)
		{
			
		}
		
		
		

	}
}
