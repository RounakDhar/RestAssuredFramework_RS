package demo;

import static io.restassured.RestAssured.given;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import pojo.LoginRequestEcommerce;
import pojo.LoginResponse;
import pojo.OrderDetail;
import pojo.Orders;


public class ECommerceAPITest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		RequestSpecification reqSpec = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
				.setContentType(ContentType.JSON).build();
		
		LoginRequestEcommerce loginReq = new LoginRequestEcommerce();
		loginReq.setUserEmail("palashdhar1965@gmail.com");
		loginReq.setUserPassword("PDpd1965@#");
		
		
		RequestSpecification reqLogin = given().relaxedHTTPSValidation().log().all().spec(reqSpec).body(loginReq);
		
		LoginResponse resLogin = reqLogin.when().post("api/ecom/auth/login")
		.then().assertThat().log().all().statusCode(200)
		.extract().response().as(LoginResponse.class);
		
		System.out.println(resLogin.getToken());
		String authToken = resLogin.getToken();
		
		System.out.println(resLogin.getUserId());
		String userID = resLogin.getUserId();
		
		
		//Add Product
		RequestSpecification addProductBaseReq = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
				.addHeader("Authorization", authToken)
				.build();
		
		RequestSpecification givenReqProductBase = given().relaxedHTTPSValidation().log().all().spec(addProductBaseReq).param("productName", "laptop")
		.param("productAddedBy", userID)
		.param("productCategory", "fashion")
		.param("productSubCategory", "shirts")
		.param("productPrice", "11500")
		.param("productDescription", "Addias Originals")
		.param("productFor", "women")
		.multiPart("productImage",new File("C:\\Users\\USER\\Downloads\\laptop-cartoon.jpg"));
		
		String addResProductBase = givenReqProductBase.when().post("api/ecom/product/add-product")
		.then().assertThat().log().all().statusCode(200)
		.extract().response().asString();
		
		JsonPath json = new JsonPath(addResProductBase);
		String productID = json.get("productId");
		
		
		//Create Order
		
		RequestSpecification createOrderBaseReq = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
				.addHeader("Authorization", authToken).setContentType(ContentType.JSON)
				.build();
		
		OrderDetail orderDetail = new OrderDetail();
		orderDetail.setCountry("India");
		orderDetail.setProductOrderedId(productID);
		
		List<OrderDetail> orderDetailList = new ArrayList<OrderDetail>();
		orderDetailList.add(orderDetail);
		
		Orders order = new Orders();
		order.setOrders(orderDetailList);
		
		RequestSpecification givenReqOrder = given().relaxedHTTPSValidation().log().all().spec(createOrderBaseReq).body(order);
		
		String resAddOrder = givenReqOrder.when().post("api/ecom/order/create-order")
		.then().assertThat().log().all().statusCode(200)
		.extract().response().asString();
		System.out.println(resAddOrder);
		
		
		//Delete Product
		
		RequestSpecification reqDeleteProductBase = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
				.addHeader("Authorization", authToken).setContentType(ContentType.JSON)
				.build();
		
		RequestSpecification givenDeleteProduct = given().log().all().spec(reqDeleteProductBase).pathParams("productId",productID);
		
		String resDeleteProductBase = givenDeleteProduct.when().delete("api/ecom/order/delete-order/{productId}")
		.then().assertThat().log().all().statusCode(200)
		.extract().response().asString();
		
		JsonPath json1 = new JsonPath(resDeleteProductBase);
		String deletedProduct = json1.get("productId");
		
		Assert.assertEquals("Product Deleted Successfully", json1.get("message"));

	}

}
