package com.farath.modal;

import java.sql.*;
import java.io.*;
import java.util.*;

public class dbStuffer 
{
	private static Statement stmt;
	
	public dbStuffer() throws IOException, SQLException
	{
		try
		{
			Connection conn = DriverManager.getConnection("jdbc:mysql://192.168.1.16:3306/crawler?useSSL=false", "joker", "alpine"); // MySQL
			stmt = conn.createStatement();
		}
		catch(SQLException IOEception)
		{
			System.out.println("Error detected @ dbStuffer - main constructor");
		}
	}
	
//	public boolean initDBConn() throws IOException, SQLException
//	{
//		
//	}
	
	public int insertSQL(String text)
	{
		int countInserted = 0;
		try
		{
			String insertStmt = "INSERT INTO crawled (url) VALUES ('" + text +"')";
			countInserted = stmt.executeUpdate(insertStmt);
			return countInserted;
		}
		catch(Exception e)
		{
			return countInserted;
		}
	}
	
	public boolean queryDB(String specific) throws SQLException, IOException
	{
		ArrayList<String> myArr = new ArrayList<String>();
		String strSelect = "select * from crawled where url = '" + specific + "'";
		ResultSet rset = stmt.executeQuery(strSelect);
		boolean flag = false;
		
		while(rset.next())
		{
			//	String url = rset.getString("url");
			//	System.out.println("URL >> " + url);
			//	myArr.add(url);
			flag = true;
		}
		
//		for(int i=0; !myArr.isEmpty() && i<myArr.size(); i++)
//		{
//			flag = false;
//			if(myArr.get(i).contains((specific)))
//			{
//				flag = true;
//			}
//			else
//			{
//				flag = false;
//			}
//		}
		return flag;
	}
}
