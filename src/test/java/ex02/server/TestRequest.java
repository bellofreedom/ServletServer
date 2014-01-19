package ex02.server;

import java.io.ByteArrayInputStream;
import java.io.InputStream;


import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
public class TestRequest {
	private final String httpRequestString = "GET /helloworld HTTP/1.1\r\n" +
					"User-Agent: curl/7.24.0 (x86_64-apple-darwin12.0) libcurl/7.24.0 OpenSSL/0.9.8y zlib/1.2.5\r\n"
					+ "Host: localhost:8080\r\n"
					+ "Accept: */*\r\n";
	private InputStream input = null;
	
	@BeforeTest
	public void SetUp() {
		input = new ByteArrayInputStream(httpRequestString.getBytes());
	}
	
	@Test
	public void testParse() {
		Request request = new Request(input);
		request.parse();
		System.out.println(request.getUri());
		Assert.assertEquals("/helloworld", request.getUri());
	}
	
	@Test 
	public void testParseUri() {
		Request request = new Request(input);
		String uri = request.parseUri(httpRequestString);
		
		System.out.println(uri);
	}
}
