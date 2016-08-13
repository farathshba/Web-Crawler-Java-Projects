package com.farath.wrapper;

public class mainCarWrapper 
{
	private String car;
	private String url;
	
	public mainCarWrapper(String _car, String _url)
	{
		this.car = _car;
		this.url = _url;
	}
	
	public String printCar()
	{
		return car;
	}
	
	public String printURL()
	{
		return url;
	}
}
