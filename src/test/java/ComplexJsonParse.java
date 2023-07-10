import files.payload;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;

public class ComplexJsonParse {

	public static void main(String[] args) {

		JsonPath json = new JsonPath(payload.CoursePrice());

//		1. Print No of courses returned by API

		int count = json.getInt("courses.size()");
		System.out.println(count);

//		2.Print Purchase Amount

		int totalAmount = json.getInt("dashboard.purchaseAmount");
		System.out.println(totalAmount);

//		3. Print Title of the first course

		String titleFirstCourse = json.get("courses[2].title");
		System.out.println(titleFirstCourse);

//		4. Print All course titles and their respective Prices

		for (int i = 0; i < count; i++) {

			String allCoursesTitles = json.get("courses[" + i + "].title");

			// Integer allCoursesPrices = json.get("courses[" + i + "].price");
			System.out.println(json.get("courses[" + i + "].price").toString());

			System.out.println(allCoursesTitles);
			// System.out.println(allCoursesPrices);

		}

//		5. Print no of copies sold by RPA Course

		System.out.println("Print no of copies sold by RPA Course");

		for (int i = 0; i < count; i++) {

			String allCoursesTitles = json.get("courses[" + i + "].title");
			if (allCoursesTitles.equalsIgnoreCase("RPA")) {
				
				int copies = json.get("courses[" + i + "].copies");
				System.out.println(copies);
				break;

			}

		}

		
		
		

	}

}
