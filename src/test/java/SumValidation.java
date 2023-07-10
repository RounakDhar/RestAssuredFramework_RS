import org.testng.Assert;
import org.testng.annotations.Test;

import files.payload;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;

public class SumValidation {
	
	
	
	//6. Verify if Sum of all Course prices matches with Purchase Amount
	
	
	@Test
	public void sumOfCourses() {
		
		int sum =0;
		
		JsonPath json = new JsonPath(payload.CoursePrice());
		
		int count = json.getInt("courses.size()");
		
		for (int i = 0; i < count; i++) {

			
			int price = json.get("courses[" + i + "].price");
			int copy = json.get("courses[" + i + "].copies");
			int amount = price * copy;

			System.out.println(amount);
			
			sum = sum + amount;
			

		}
		
		System.out.println(sum);
		
		int purchaseAmount = json.getInt("dashboard.purchaseAmount");
		System.out.println(purchaseAmount);
		
		Assert.assertEquals(sum, purchaseAmount);
		
	}

}
