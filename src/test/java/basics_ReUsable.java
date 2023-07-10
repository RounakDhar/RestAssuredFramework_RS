import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;

import org.testng.Assert;

import files.ReUsableMethods;
import files.payload;

//import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

//@Test
public class basics_ReUsable {

	public static void main(String[] args) {

		RestAssured.baseURI = "http://rahulshettyacademy.com";

		// Add Place

		String response = given().queryParam("key", "qaclick123").header("Content-Type", "application/json")
				.body(payload.addPlace())
				.when().post("maps/api/place/add/json")
				.then().assertThat().log().all()
				.statusCode(200).body("scope", equalTo("APP"))
				.header("server", "Apache/2.4.52 (Ubuntu)")
				.extract().response().asString();

		System.out.println(response);

		JsonPath json = new JsonPath(response); // --for parsing json
		String extractedPlace_ID = json.getString("place_id");
		System.out.println(extractedPlace_ID);

		// Update a New Place

		String newAdresss = "Summer Walk, Africa";

		given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
				.body("{\r\n" + "\"place_id\":\"" + extractedPlace_ID + "\",\r\n" + "\"address\":\"" + newAdresss
						+ "\",\r\n" + "\"key\":\"qaclick123\"\r\n" + "}")
				.when().put("maps/api/place/update/json").then().assertThat().log().all().statusCode(200)
				.body("msg", equalTo("Address successfully updated"));

		// Get Place

		String getNewPlaceResponse = given().log().all().queryParam("key", "qaclick123")
				.queryParam("place_id", extractedPlace_ID).when().get("maps/api/place/get/json").then().assertThat()
				.log().all().statusCode(200).extract().response().asString();

		System.out.println(getNewPlaceResponse);
		
		JsonPath jsonPath = ReUsableMethods.rawToJson(getNewPlaceResponse);

		String extractedAddress = jsonPath.getString("address");
		System.out.println(extractedAddress);

		Assert.assertEquals(extractedAddress, newAdresss);

	}

}
