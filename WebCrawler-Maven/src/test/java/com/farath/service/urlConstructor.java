package com.farath.service;

public class urlConstructor 
{
	//	Filters
	private String _carModel;
	private String _carPriceRangeFrom;
	private String _carPriceRangeTo;
	private String _age;
	
	public urlConstructor(String _carModel)
	{
		this._carModel = _carModel;
	}
	
	//	Accessor methods
	public void setCarModel(String __carModel)
	{
		this._carModel = __carModel;
	}
	
	public String getCarModel()
	{
		return this._carModel;
	}
	
	public void setPriceRangeFrom(String __carPriceRangeFrom)
	{
		this._carPriceRangeFrom = __carPriceRangeFrom;
	}
	
	public String getPriceRangeFrom()
	{
		return this._carPriceRangeFrom;
	}
	
	public void setPriceRangeTo(String __carPriceRangeTo)
	{
		this._carPriceRangeTo = __carPriceRangeTo;
	}
	
	public String getPriceRangeTo()
	{
		return this._carPriceRangeTo;
	}
	
	public void setAge(String __age)
	{
		this._age = __age; 
	}
	
	public String getAge()
	{
		return this._age;
	}
	
	public String getConstructURL()
	{
		String urlTemp = "http://www.sgcarmart.com/used_cars/listing.php?";
		
		if(this._carModel != null || this._carModel == "")
		{
			urlTemp = urlTemp + "MOD=" + this._carModel;
		}
		else
		{
			urlTemp = urlTemp + "MOD=";
		}
		
		if(this._carPriceRangeFrom != null || this._carPriceRangeFrom == "")
		{
			urlTemp = urlTemp + "&PR1" + this._carPriceRangeFrom; 
		}
		else
		{
			urlTemp = urlTemp + "&PR1"; 
		}
		
		if(this._carPriceRangeTo != null || this._carPriceRangeTo == "")
		{
			urlTemp = urlTemp + "&PR2" + this._carPriceRangeTo; 
		}
		else
		{
			urlTemp = urlTemp + "&PR2"; 
		}
		
		if(this._age != null || this._age == "")
		{
			urlTemp = urlTemp + "&FR=" + this._age; 
		}
		else
		{
			urlTemp = urlTemp + "&FR=Any"; 
		}
		
		urlTemp = urlTemp + "&TO=Any&TRN=&ENG=&FUE=&MIL_C=&OMV_C=0&OMV=&COE_C=&OWN_C=&DL=&LOC=&AVL=&ASL=1";
		return urlTemp;
	}
}
