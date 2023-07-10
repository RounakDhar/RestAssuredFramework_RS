package demo;

import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.testng.Assert;

import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;
import pojo.Api;
import pojo.GetCourse;

public class oAuthTest {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub

		String[] courseTitles = { "Selenium Webdriver Java", "Protractor", "Cypress" };

//		System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver.exe");
//		WebDriver driver = new ChromeDriver();
//		driver.get(
//				"https://accounts.google.com/o/oauth2/v2/auth?scope=https://www.googleapis.com/auth/userinfo.email&auth_url=https://accounts.google.com/o/oauth2/v2/auth&client_id=692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com&response_type=code&redirect_uri=https://rahulshettyacademy.com/getCourse.php");
//
//		driver.findElement(By.cssSelector("input[type='email']")).sendKeys("palashdhar1965");
//		driver.findElement(By.cssSelector("input[type='email']")).sendKeys(Keys.ENTER);
//
//		Thread.sleep(3000);
//
//		driver.findElement(By.cssSelector("input[type='password']")).sendKeys("p9331998541d");
//		driver.findElement(By.cssSelector("input[type='password']")).sendKeys(Keys.ENTER);
//
//		Thread.sleep(4000);

//		String extractedUrl = driver.getCurrentUrl();
//		System.out.println(extractedUrl);
//		
//		String partialCode = extractedUrl.split("code=")[1];
//		
//		String actualStringCode = partialCode.split("&scope")[0];
//		System.out.println(actualStringCode);

		// ===After 2020 Google is not allowing to automate Gmail SignIn=====

		String actualStringCode = "https://rahulshettyacademy.com/getCourse.php?code=4%2F0AZEOvhVHb6lulpDBji8W7mNV0znJOPluNl_f92uDhl0eRDY0ZHR18g__EShoJjXtZa2NiQ&scope=email+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email+openid&authuser=0&prompt=consent";

		String accessTokenResponse = given().relaxedHTTPSValidation().urlEncodingEnabled(false).queryParam("code", actualStringCode)
				.queryParam("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
				.queryParam("client_secret", "erZOWM9g3UtwNRj340YYaK_W")
				.queryParam("redirect_uri", "https://rahulshettyacademy.com/getCourse.php")
				.queryParam("grant_type", "authorization_code").when()
				.post("https://www.googleapis.com/oauth2/v4/token").asString();

		JsonPath json = new JsonPath(accessTokenResponse);
		String extractedAccessToken = json.getString("access_token");

//		String getResponse = given().queryParam("access_token", extractedAccessToken).when()
//				.get("https://rahulshettyacademy.com/getCourse.php").asString();

//		System.out.println(getResponse);

		GetCourse gc = given().relaxedHTTPSValidation().queryParam("access_token", extractedAccessToken).expect().defaultParser(Parser.JSON)
				.when().get("https://rahulshettyacademy.com/getCourse.php").as(GetCourse.class);

		System.out.println(gc.getLinkedin());
		System.out.println(gc.getInstructor());
		System.out.println(gc.getCourses().getApi().get(1).getCourseTitle());

		List<Api> apiCourses = gc.getCourses().getApi();
		for (int i = 0; i < apiCourses.size(); i++) {

			if (apiCourses.get(i).getCourseTitle().equalsIgnoreCase("SoapUI Webservices testing")) {

				System.out.println(apiCourses.get(i).getPrice());

			}
		}

		// Get the course names of Web Automation

		ArrayList<String> actualList = new ArrayList<String>();

		List<pojo.WebAutomation> wa = gc.getCourses().getWebAutomation();
		for (int j = 0; j < wa.size(); j++) {

			System.out.println(actualList.add(wa.get(j).getCourseTitle()));
		}

		List<String> expectedList = Arrays.asList(courseTitles);

		Assert.assertTrue(actualList.equals(expectedList));

	}

}
