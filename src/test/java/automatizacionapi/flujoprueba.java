package automatizacionapi;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;

import org.apache.http.HttpStatus;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class flujoprueba {
	private String ide; 
	@BeforeClass
	public void setup() {
		RestAssured.baseURI="https://reqres.in";
		RestAssured.basePath="/api";
		RestAssured.filters(new RequestLoggingFilter(),new ResponseLoggingFilter());
		
	}
	@Test (priority=1)
	public void creaUser() {
			ide=given()
		           .contentType(ContentType.JSON)
				   .body("{\r\n"
				   		+ "    \"name\": \"Joselito\",\r\n"
				   		+ "    \"job\": \"administrador\"\r\n"
				   		+ "}")
				   .when()
				   		.post("/users")
				   .then()
			   		  .log().all()				   
				      .statusCode(201)
					  .extract()
					  .path("name");
			System.out.println("El ide creado es : "+ide);
		
				   		
	}
	@Test (priority=2)
	public void consultaUser() {
		given()
        .contentType(ContentType.JSON)
        .when()
	    	.get("user/2")
	    .then()
	    	.statusCode(200);
		
	}
	@Test (priority=3)
	public void actualizaPUT() {
		given()
		.contentType(ContentType.JSON)
		.body("{\r\n"
				+ "    \"name\": \"Joselito\",\r\n"
				+ "    \"job\": \"zion resident\"\r\n"
				+ "}")
		.when()
			.put("users/2")	
		.then()
			.statusCode(200);		
	}
	@Test (priority=4)
	public void actualizaPATCH() {
		given()
		.contentType(ContentType.JSON)
		.body("{\r\n"
				+ "    \"name\": \"Joselito\",\r\n"
				+ "    \"job\": \"administrador de granja de gallina\"\r\n"
				+ "}")
		.when()	
			.patch("users/2")	
		.then()
			.statusCode(200);		
	}
	
	@Test (priority=5)
	public void ejecutaDelete() {
		given()
		.contentType(ContentType.JSON)
		.when()	
			.delete("users/2")	
		.then()
			.statusCode(204);		
	}
	

	@Test (priority=6)
	public void consultaUserdeleteado() {
		given()
        .contentType(ContentType.JSON)
        .when()
	    	.get("user/"+ide)
	    .then()
	    	.statusCode(404);
		
	}
}
