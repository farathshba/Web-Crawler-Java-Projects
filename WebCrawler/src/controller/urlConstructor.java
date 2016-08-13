package controller;

public class urlConstructor 
{
	//	Filters
	private String _carModel;
	private String _carPriceRangeFrom;
	private String _carPriceRangeTo;
	
	public urlConstructor(String _carModel)
	{
		this._carModel = _carModel;
	}
	
	//	Accessor methods
	public void setCarModel(String _carModel)
	{
		this._carModel = _carModel;
	}
	
	public String getCarModel()
	{
		return _carModel;
	}
	
	public String constructURL()
	{
		String urlTemp = "http://www.sgcarmart.com/used_cars/listing.php?";
		
		if(_carModel != null || _carModel == "")
		{
			urlTemp = urlTemp + "MOD=" + _carModel + "&PR1=0&PR2=&FR=Any&TO=Any&TRN=&ENG=&FUE=&MIL_C=&OMV_C=&COE_C=&OWN_C=&DL=&LOC=&AVL=&ASL=1";
		}
		return urlTemp;
	}
}
