package com.farath.view;

import com.farath.modal.*;
import com.farath.service.GoogleMail;

public class exe 
{
	public static void main(String[] args)
	{
		//	Frame would taken ownership after tests
		//crawler crawl = new crawler("http://www.sgcarmart.com/used_cars/listing.php?MOD=&PR1=0&PR2=&FR=Any&TO=Any&TRN=&ENG=&FUE=&MIL_C=&OMV_C=&COE_C=&OWN_C=&DL=&LOC=&AVL=&ASL=1");
		//crawl.printCrawledInfo();
		
		try
		{
			GoogleMail.generateAndSendEmail();
		}
		catch(Exception e)
		{
			System.err.println("com.farath.view exe - " + e.getMessage());
		}
		notifier not = new notifier();
		not.sendMail();
	}
}
