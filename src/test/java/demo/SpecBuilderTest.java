package demo;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;

import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.List;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.AddPlace;
import pojo.Location;

public class SpecBuilderTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		RestAssured.baseURI = "https://rahulshettyacademy.com/";

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

		RequestSpecification reqSpec = new RequestSpecBuilder()
				.setBaseUri("https://rahulshettyacademy.com/")
				.addQueryParam("key", "qaclick123").setContentType(ContentType.JSON).build();
		
		ResponseSpecification resSpec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();

		RequestSpecification givenResponse = given().spec(reqSpec).body(aplace);
		
		Response whenResponse = givenResponse.when().post("/maps/api/place/delete/json")
				.then().spec(resSpec).extract().response();

		String responseString = whenResponse.asString();
		System.out.println(responseString);

	}

}
