package com.farath.modal;

import com.farath.wrapper.*;
import org.jsoup.Jsoup;  
import org.jsoup.nodes.Document;  
import org.jsoup.nodes.Element;
import java.util.*;

//	This class file crawls the Web Content for keywords
public class crawler 
{
	//	Search parameters
	private static final String USER_AGENT = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/45.0.2454.101 Safari/537.36";
	private ArrayList<mainCarWrapper> myStrArr = new ArrayList<mainCarWrapper>();
	
	public crawler(String url)
	{
		try
		{
			final Document doc = Jsoup.connect(url).userAgent(USER_AGENT).get();
			
			for (Element result : doc.select("a[href^=info.php?ID=]"))
			{
	            //final String title = result.text();
	            String _url = result.attr("href");
	            String _car = result.text();
	            String _tempUrl = "";

	            //Now do something with the results (maybe something more useful than just printing to console)
	            //System.out.println(_car + " -> " + _url);
	            if(!_car.equals("Details") && !_tempUrl.equals(_url))
	            {
	            	if(!_car.isEmpty() )
	            	{
	            		myStrArr.add(new mainCarWrapper(_car, _url));
		            	_tempUrl = _url;
	            	}
	            }
	        }
		}
		catch(Exception e)
		{
			System.out.println("Error >> " + e.getMessage());
		}
	}
	
	//	Return methods
	public ArrayList<String> getCar()
	{
		ArrayList<String> myTestCar = new ArrayList<String>(); 
		for(int i=1; i<myStrArr.size(); i++)
		{
			myTestCar.add(myStrArr.get(i).printCar());
		}
		return myTestCar;
	}
	
	public ArrayList<String> getURL()
	{
		ArrayList<String> myTestURL = new ArrayList<String>(); 
		for(int i=1; i<myStrArr.size(); i++)
		{
			myTestURL.add(myStrArr.get(i).printURL());
		}
		return myTestURL;
	}
	
	public ArrayList<String> getFullURL()
	{
		ArrayList<String> myTestURL = new ArrayList<String>(); 
		for(int i=1; i<myStrArr.size(); i++)
		{
			myTestURL.add("http://www.sgcarmart.com/used_cars/" + myStrArr.get(i).printURL());
		}
		return myTestURL;
	}
	
	//	Test Methods
	public void printCrawledInfo()
	{
		for(int i=1; i<myStrArr.size(); i++)
		{
			System.out.println(i + " : " + myStrArr.get(i).printCar() + " >> " + myStrArr.get(i).printURL());
		}
	}
}
