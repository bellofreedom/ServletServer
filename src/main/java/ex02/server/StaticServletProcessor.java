package ex02.server;

import java.io.IOException;

public class StaticServletProcessor {

	public void process(Request request, Response response) {
		// TODO Auto-generated method stub
		try {
			response.sendStaticResource();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
