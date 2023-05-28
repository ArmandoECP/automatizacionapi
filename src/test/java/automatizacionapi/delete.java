package automatizacionapi;

import org.apache.http.HttpStatus;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.RestAssured;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class delete {
	@BeforeClass
	public void setup() {
		RestAssured.baseURI="https://reqres.in";
		RestAssured.basePath="/api";
		RestAssured.filters(new RequestLoggingFilter(),new ResponseLoggingFilter());
		RestAssured.requestSpecification=new RequestSpecBuilder()
				.setContentType(ContentType.JSON)
				.build();		
		
				}		
    @Test
	public void deleteUser() {
		RestAssured.given()
			    .get("user/2")
			    .then()
			    .statusCode(HttpStatus.SC_NO_CONTENT);			    
	}
    
    @Test
	public void patchUserTest() {
		String nombreUp=RestAssured.given()
				.when()
				.body("{\r\n"
						+ "    \"name\": \"morpheus\",\r\n"
						+ "    \"job\": \"zion resident\"\r\n"
						+ "}")
				.patch("user/2")
				.then()
				.statusCode(HttpStatus.SC_OK)
				.extract()
					.jsonPath().get("name");	
		assertThat(nombreUp,equalTo("morpheus"));
	}
    
    @Test
	public void putUserTest() {
		String trabajo=RestAssured.given()
				.when()
				.body("{\r\n"
						+ "    \"name\": \"morpheus\",\r\n"
						+ "    \"job\": \"zion resident\"\r\n"
						+ "}")
				.put("user/2")
				.then()
				.statusCode(HttpStatus.SC_OK)
				.extract()
					.jsonPath().get("job");		
		assertThat(trabajo,equalTo("zion resident"));
	}

}
