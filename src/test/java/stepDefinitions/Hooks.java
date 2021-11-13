package stepDefinitions;

import java.io.IOException;

import io.cucumber.java.Before;

public class Hooks {
	
	@Before("@DeletePlace")
	public void beforeScenario() throws IOException
	{
		
		
		AddPlaceSpec aps=new AddPlaceSpec();
		if(AddPlaceSpec.place_id==null)
		{
		aps.add_place_payload_with("Abhinav","English","India");
		aps.user_calls_with_http_request("AddPlaceAPI", "POST");
		aps.verify_place_id_created_maps_to_with_using("Abhinav", "GetPlaceAPI");
		
	}}

}
