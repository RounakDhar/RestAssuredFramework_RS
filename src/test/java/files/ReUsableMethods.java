package files;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;

import io.restassured.path.json.JsonPath;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

public class ReUsableMethods {

	public static JsonPath rawToJson(Response response) {
		
		String respString =response.asString();
		
		JsonPath jsonPath=new JsonPath(respString);
		return jsonPath;

	}

	public static XmlPath rawToXML(Response response) {
		// TODO Auto-generated method stub
		//return null;
		
		String respString =response.asString();
		
		XmlPath xmlPath = new XmlPath(respString);
		return xmlPath;
	}

	public static JsonPath rawToJson(String postReponse) {
		// TODO Auto-generated method stub
		//return null;
		
		JsonPath jsonPath=new JsonPath(postReponse);
		return jsonPath;
	}

	



}
