package files;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class DynamicJson {

	@Test(dataProvider = "BooksData")
	public void addBook(String isbn, String aisle) {

		RestAssured.baseURI = "http://216.10.245.166";

		String postReponse = given().header("Content-Type", "application/json")
				.body(payload.AddBook(isbn, aisle))
				.when().post("Library/Addbook.php")
				.then().log().all().assertThat().statusCode(200)
				.extract().response().asString();

		JsonPath json = ReUsableMethods.rawToJson(postReponse);
		String extractedId = json.get("ID");
		System.out.println(extractedId);
	}

	@DataProvider(name = "BooksData")
	public Object[][] getData() {

		return new Object[][] { { "ojfwty", "9363" }, { "cewtee", "4253" }, { "okmfet", "3535" } };
	}
}
