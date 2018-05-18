package getRequest;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;

import java.net.URI;
import java.net.URISyntaxException;

import org.apache.http.HttpStatus;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

// API Examples from: https://openweathermap.org/current

public class GetData3 {

	String urlJSON = "http://samples.openweathermap.org/data/2.5/weather?q=London,uk&appid=b1b15e88fa797225412429c1c50c122a1";
	String urlXML = "http://samples.openweathermap.org/data/2.5/weather?q=London&mode=xml&appid=b1b15e88fa797225412429c1c50c122a1";

	@Test(priority = 0)
	public void testResponseCode() {
		int code = get(urlJSON).getStatusCode();
		System.out.println("Status code is: " + code);
		AssertJUnit.assertEquals(code, 200);
	}

	@Test(priority = 1)
	public void testBody() {

		String data = get(urlJSON).asString();
		System.out.println("Data is: " + data);

		long time = get(urlJSON).getTime();
		System.out.println("Response time " + time);
	}

	@Test(priority = 2)
	public void testGet() throws URISyntaxException {
		// Get the url content in XML
		Response response = given().accept(ContentType.XML).when().get(new URI(urlXML));
		System.out.println(response.asString());
	}

	@Test(priority = 3)
	public void testStatusCode1() throws URISyntaxException {
		// Capture the status code or any other info: thenReturns
		int code = given().accept(ContentType.XML).when().get(new URI(urlXML)).thenReturn().statusCode();
		System.out.println("Status code is: " + code);
		AssertJUnit.assertEquals(HttpStatus.SC_OK, code);
	}

	@Test(priority = 4)
	public void testStatusCode2() throws URISyntaxException {
		// Validation of response: then
		given().accept(ContentType.XML).when().get(new URI(urlXML)).then().assertThat().statusCode(HttpStatus.SC_OK);
	}
}
