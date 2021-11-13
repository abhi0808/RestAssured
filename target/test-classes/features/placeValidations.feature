Feature: Validating Place API
@AddPlace @Regression
Scenario Outline: Verify if place is being successfully added using AddPlaceAPI
Given Add Place Payload with "<name>" "<language>" "<address>"
When User calls "AddPlaceAPI" with "POST" http request
Then the API call got success with status code 200
And "status" in response body is "OK"
And "scope" in response body is "APP"
And verify place_Id created maps to "<name>" with using "GetPlaceAPI"

Examples:
				|name 	 | language |address |
				|AAhouse | English  | World Cross Center |
	#			|BBhouse | English  | Sea Cross Center |
	
@DeletePlace @Regression
	Scenario: Verify If Delete Place Functionality Is Working
	Given DeletePlace payload
	When User calls "DeletePlaceAPI" with "POST" http request
	Then the API call got success with status code 200
	And "status" in response body is "OK"