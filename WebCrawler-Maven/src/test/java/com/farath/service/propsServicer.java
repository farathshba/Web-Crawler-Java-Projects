package com.farath.service;

import java.util.*;
import java.io.*;

public class propsServicer 
{
	private Properties prop;
	private InputStream input;
	private OutputStream output;
	
	public propsServicer() throws FileNotFoundException, IOException
	{
		prop = new Properties();
		input = new FileInputStream("filters.properties");

		// load a properties file
		prop.load(input);
	}
	
	public String getCarModel()
	{
		return prop.getProperty("com.sgcarmart.carmodel");
	}
	
	public String getPriceRangeFrom()
	{
		return prop.getProperty("com.sgcarmart.priceRangeFrom");
	}
	
	public String getPriceRangeTo()
	{
		return prop.getProperty("com.sgcarmart.priceRangeTo");
	}
	
	public String getAge()
	{
		return prop.getProperty("com.sgcarmart.year");
	}
}
