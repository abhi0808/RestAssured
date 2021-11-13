package resources;

import java.util.ArrayList;
import java.util.List;

import pojo.AddPlace;
import pojo.location;

public class TestDataBuild {
	
	public AddPlace addPlacePayload(String name,String language,String address)
	{
		AddPlace p=new AddPlace();
		p.setAccuracy(50);
		p.setName(name);
		p.setPhone_number("(+91) 983 893 3937");
		p.setAddress(address);
		p.setWebsite("http://google.com");
		p.setLanguage(language);
		
		List<String>l=new ArrayList<String>();
		l.add("shoe park");
		l.add("shop");
		p.setTypes(l);
		
		location lc=new location();
		lc.setLat(-38.383494);
		lc.setLng(33.427362);
		
		p.setLocation(lc);
		return p;
	}
	public String deletePlacePayload(String placeID)
	{
		return "{\r\n    \"place_id\":\""+placeID+"\"\r\n}";
	}

}
