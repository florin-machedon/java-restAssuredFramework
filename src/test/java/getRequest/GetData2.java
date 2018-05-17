package getRequest;

import static io.restassured.RestAssured.get;

import org.testng.Assert;
import org.testng.annotations.Test;

public class GetData2 {

	String url = "http://samples.openweathermap.org/data/2.5/weather?q=London,uk&appid=b1b15e88fa797225412429c1c50c122a1";

	@Test
	public void testResponseCode() {
		int code = get(url).getStatusCode();
		System.out.println("Status code is: " + code);
		Assert.assertEquals(code, 200);
	}

	@Test
	public void testBody() {
		String data = get(url).asString();
		System.out.println("Data is: " + data);

		long time = get(url).getTime();
		System.out.println("Response time " + time);
	}

}
