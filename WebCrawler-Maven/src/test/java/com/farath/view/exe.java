package com.farath.view;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Properties;
import com.farath.modal.*;
import com.farath.service.GoogleMail;
import com.google.api.services.gmail.Gmail;
import com.farath.service.urlConstructor;
import com.farath.service.*;
import java.io.*;

public class exe 
{
	public static void main(String[] args)
	{
		//	Frame would take charge soon
		propsServicer propsObj = null;
		try
		{
			propsObj = new propsServicer();
		}
		catch(IOException ioe)
		{
			System.out.println("com.farath.view > " + ioe.getMessage());
		}
		
		//	URL Builder
		urlConstructor url = new urlConstructor(propsObj.getCarModel());
		url.setPriceRangeFrom(propsObj.getPriceRangeFrom());
		url.setPriceRangeTo(propsObj.getPriceRangeTo());
		
		crawler crawl = new crawler(url.getConstructURL());
		ArrayList<String> carInfo = crawl.getCar();
		ArrayList<String> carURL = crawl.getFullURL();
		
		//	Email Sender
		try
		{
			notifier not = new notifier();
			//	
			//not.setRecipient("farisoft@gmail.com");
			not.setRecipient("russellsg888@gmail.com");
			not.setSubj("Match results from Peach Car Crawler");
			not.setBody("Greetings from Farath's Crawler:</br></br>");
			not.setBody("<strong>Filters used:</strong></br>");
			
			if(!propsObj.getCarModel().isEmpty() && propsObj.getPriceRangeFrom().isEmpty() && propsObj.getPriceRangeTo().isEmpty() && propsObj.getAge().isEmpty())
			{
				not.setBody("<ul>Car Brand >> " + propsObj.getCarModel() + "</ul></br>");
			}
			else if(!propsObj.getCarModel().isEmpty() && !propsObj.getPriceRangeFrom().isEmpty() && propsObj.getPriceRangeTo().isEmpty() && propsObj.getAge().isEmpty())
			{
				not.setBody("<ul>Car Brand >> " + propsObj.getCarModel() + "</ul></br>");
				not.setBody("<ul>Price From >> S$ " + propsObj.getPriceRangeFrom() + ",000 </ul></br>");
			}
			else if(!propsObj.getCarModel().isEmpty() && !propsObj.getPriceRangeFrom().isEmpty() && !propsObj.getPriceRangeTo().isEmpty() && propsObj.getAge().isEmpty())
			{
				not.setBody("<ul>Car Brand >> " + propsObj.getCarModel() + "</ul></br>");
				not.setBody("<ul>Price From >> S$ " + propsObj.getPriceRangeFrom() + ",000 </ul></br>");
				not.setBody("<ul>Price To >> S$ " + propsObj.getPriceRangeTo() + ",000 </ul></br>");
			}
			else if(!propsObj.getCarModel().isEmpty() && !propsObj.getPriceRangeFrom().isEmpty() && !propsObj.getPriceRangeTo().isEmpty() && !propsObj.getAge().isEmpty())
			{
				not.setBody("<ul>Car Brand >> " + propsObj.getCarModel() + "</ul></br>");
				not.setBody("<ul>Price From >> S$ " + propsObj.getPriceRangeFrom() + ",000 </ul></br>");
				not.setBody("<ul>Price To >> S$ " + propsObj.getPriceRangeTo() + ",000 </ul></br>");
				not.setBody("<ul>Age of vehicle >> " + (Calendar.getInstance().get(Calendar.YEAR) - Integer.parseInt(propsObj.getAge())) + " years old </ul></br>");
			}
			
			not.setBody("</br>Matches:</br>");
			if(!propsObj.getCarModel().isEmpty() && propsObj.getPriceRangeFrom().isEmpty() && propsObj.getPriceRangeTo().isEmpty())
			{
				for(int i=0; i<carInfo.size(); i++)
				{
					not.setBody("<ul>" + carInfo.get(i) + " -> <a href=\"" + carURL.get(i) + "\">\"" + carURL.get(i) + "</a>" +"</ul></br>");
				}
			}
			else if(!propsObj.getCarModel().isEmpty() && !propsObj.getPriceRangeFrom().isEmpty() && propsObj.getPriceRangeTo().isEmpty())
			{
				for(int i=0; i<carInfo.size(); i++)
				{
					not.setBody("<ul>" + carInfo.get(i) + " -> <a href=\"" + carURL.get(i) + "\">\"" + carURL.get(i) + "</a>" +"</ul></br>");
				}
			}
			else if(!propsObj.getCarModel().isEmpty() && !propsObj.getPriceRangeFrom().isEmpty() && !propsObj.getPriceRangeTo().isEmpty())
			{
				for(int i=0; i<carInfo.size(); i++)
				{
					not.setBody("<ul>" + carInfo.get(i) + " -> <a href=\"" + carURL.get(i) + "\">\"" + carURL.get(i) + "</a>" +"</ul></br>");
				}
			}
			
			not.assign();
			not.sendmail();
		}
		catch(Exception e)
		{
			System.err.println("com.farath.view exe - " + e.getMessage());
		}
		
		//	Spreadsheet Curator
		
	}
}
