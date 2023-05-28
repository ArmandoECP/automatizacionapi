package automatizacionapi;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.equalTo;

public class logueo {
	
	@BeforeClass
	public void setup() {
		RestAssured.baseURI="https://reqres.in";
		RestAssured.basePath="/api";
		//RestAssured.filters(new RequestLoggingFilter(),new ResponseLoggingFilter());
		
	}
	@Test (priority=1)
	public void loguinsucc() {
				given()
		           .contentType(ContentType.JSON)
				   .body("{\r\n"
				   		+ "    \"email\": \"eve.holt@reqres.in\",\r\n"
				   		+ "    \"password\": \"cityslicka\"\r\n"
				   		+ "}")
				   .when()
				   		.post("/login")
				   .then()				   
				   .statusCode(200)
				   .body("token", notNullValue());
		
				   		
	}
	
	@Test (priority=2)
	public void getusuariosimple() {
		  given()
    		.get("/user/2")
		   .then()
		   .log().all()
		   .statusCode(200)
		   .body("data.id",equalTo(2));
		
	}

}
