package files;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;
import static org.testng.Assert.assertEquals;


import io.restassured.path.json.JsonPath;

public class GraphQLScript {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		

		//===Query===
		
		
		
		// CharecterId = 14;
		
		String resposeQueryString = given().log().all().header("Content-Type", "application/json")
				.body("")
				.when()																										//--value will be passed in the Body Section for Query
				.post("https://rahulshettyacademy.com/gq/graphql").then().extract().response().asString();				

		System.out.println(resposeQueryString);

		JsonPath jsonPath = new JsonPath(resposeQueryString);
		String charecterNameString = jsonPath.getString("data.charecter.name");
		
		assertEquals(charecterNameString, "Robin");
		
		
		
		//===Mutations===
		
		
		//String newCharecterName = "Baskin Robbins";
		
		String resposeMutationString = given().log().all().header("Content-Type", "application/json")
				.body("")
				.when()																										//--value will be passed in the Body Section for Mutation
				.post("https://rahulshettyacademy.com/gq/graphql").then().extract().response().asString();				

		System.out.println(resposeMutationString);

		JsonPath json = new JsonPath(resposeMutationString);
		

	}

}
