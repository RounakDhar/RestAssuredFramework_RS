package ExcelIntegration;

import static io.restassured.RestAssured.given;

import java.util.HashMap;

import org.testng.annotations.Test;

import files.ReUsableMethods;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

public class excelDriven {

	@Test
	public void addBook() {

		HashMap<String, Object> map = new HashMap<String, Object>();// Creating HashMap
		
		map.put("name", "Rest Assured");
		map.put("isbn", "Two Three");
		map.put("aisle", "3224");
		map.put("author", "Test Automation");
		
		HashMap<String, Object> map2 = new HashMap<String, Object>();// Creating HashMap
		map2.put("lat", "123");
		map2.put("lng", "456");
		map2.put("location", map2);
		

		RestAssured.baseURI = "https://rahulshettyacademy.com/";
		// RestAssured.baseURI="http://216.10.245.166";

		Response response = given().header("Content-Type", "application/json").body(map).when()
				.post("/Library/Addbook.php").then().assertThat().statusCode(200).and().contentType(ContentType.JSON)
				.extract().response();

		JsonPath jsonPath = ReUsableMethods.rawToJson(response);

		String idString = jsonPath.get("id");
		System.out.println(idString);

	}

}
