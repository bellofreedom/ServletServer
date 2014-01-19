package ex02.server;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TestResponse {
	private  final String httpRequestString1 = "GET /helloworld HTTP/1.1\r\n";
	private  final String httpRequestString2 = "GET /test HTTP/1.1\r\n";
	private Request request1 = null;
	private Request request2 = null;
	
	@BeforeTest
	public void setUp() {
		request1 = new Request(new ByteArrayInputStream(httpRequestString1.getBytes()));
		request1.parse();
		System.out.println("request1 build success");
	}
	
	@Test
	public void testSendStaticResponse() {
		
		Response response = new Response(new ByteArrayOutputStream(1024));
		response.setRequest(request1);

		System.out.println(request1.getUri());
		
		try {
			response.sendStaticResource();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			OutputStream out = response.getOutputStream();
			System.out.println(out.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
