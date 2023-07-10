package ExcelIntegration;

import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.testng.annotations.Test;

import files.ReUsableMethods;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class excelDataDrivenIntegration {

	@Test
	public void addBook() throws IOException {

		DataDriven dataDriven = new DataDriven();
		ArrayList arrayData = dataDriven.getData("Rest AddBook","Rest Assured");

		HashMap<String, Object> map = new HashMap<String, Object>();// Creating HashMap

		map.put("name", arrayData.get(1));
		map.put("isbn", arrayData.get(2));
		map.put("aisle", arrayData.get(3));
		map.put("author", arrayData.get(4));

//		HashMap<String, Object> map2 = new HashMap<String, Object>();// Creating HashMap
//		map2.put("lat", "123");
//		map2.put("lng", "456");
//		map2.put("location", map2);

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
