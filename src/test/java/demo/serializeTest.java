package demo;

import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.List;

import io.restassured.response.Response;
import pojo.AddPlace;
import pojo.Location;

public class serializeTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		AddPlace aplace = new AddPlace();
		
		aplace.setAccuracy(50);
		aplace.setAddress("29, side layout, cohen 09");
		aplace.setLanguage("French-IN");
		aplace.setPhone_number("(+91) 983 893 3937");
		aplace.setWebsite("http://google.com");
		aplace.setName("Test Automation");
		
		List<String> myList = new ArrayList<String>();
		myList.add("shoe park");
		myList.add("shop");
		aplace.setTypes(myList);
		
		Location myLocations = new Location();
		myLocations.setLat(-38.383494);
		myLocations.setLng(33.427362);
		aplace.setLocation(myLocations);
		

		Response postResponse = given().queryParam("key", "qaclick123")
				.body(aplace)
				.when().post("/maps/api/place/delete/json")
				.then().assertThat().log().all().statusCode(200)
				.extract().response();

		String responseString = postResponse.asString();
		System.out.println(responseString);

	}

}
