package stepDefinitions;


import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import java.io.IOException;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;



public class AddPlaceSpec extends Utils {
	ResponseSpecification res1;
	RequestSpecification response;
	Response finalresponse;
	TestDataBuild build = new TestDataBuild();
	static String place_id;
	
	
	@Given("Add Place Payload with {string} {string} {string}")
	public void add_place_payload_with(String name, String language, String address) throws IOException {
	    // Write code here that turns the phrase above into concrete actions
		
		 response=	given().spec(requestSpecification())
		.body(build.addPlacePayload(name,language,address));
		
		
	}
	@When("User calls {string} with {string} http request")
	public void user_calls_with_http_request(String resource, String method) {
	    // Write code here that turns the phrase above into concrete actions
	APIResources resourceAPI=APIResources.valueOf(resource);
	System.out.println(resourceAPI.getresource());
		
		
		res1=new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		
		if(method.equalsIgnoreCase("POST"))
		{
		
		finalresponse=	response.when().post(resourceAPI.getresource());
			//	.then().spec(res1).extract().response();
		}
		else if(method.equalsIgnoreCase("GET"))
		{
			finalresponse=	response.when().get(resourceAPI.getresource());
		}
		
	}
	@Then("the API call got success with status code {int}")
	public void the_api_call_got_success_with_status_code(Integer int1) {
	    // Write code here that turns the phrase above into concrete actions
	 
		assertEquals(finalresponse.getStatusCode(),200);
	}
	@And("{string} in response body is {string}")
	public void in_response_body_is(String keyvalue, String expectedValue) {
		
		
		assertEquals(getJsonPath(finalresponse,keyvalue),expectedValue);
	}
	@Then("verify place_Id created maps to {string} with using {string}")
	public void verify_place_id_created_maps_to_with_using(String expectedName, String resource) throws IOException {
	   
	 place_id=getJsonPath(finalresponse, "place_id");
		response=	given().spec(requestSpecification()).queryParam("place_id", place_id);
		user_calls_with_http_request(resource,"GET");
		String name=getJsonPath(finalresponse, "name");
		assertEquals(name, expectedName);
	    
	}
	@Given("DeletePlace payload")
	public void delete_place_payload() throws IOException {
		response=	given().spec(requestSpecification()).body(build.deletePlacePayload(place_id));
	    
	}
}
