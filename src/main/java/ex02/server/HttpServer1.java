package ex02.server;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ResponseCache;
import java.net.ServerSocket;
import java.net.Socket;

public class HttpServer1 {
	private static final String SHUTDOWN_COMMAND_STRING = "/SHUTDOWN";
	private static final int BUFFER_SIZE = 1024;
	private boolean shutdown = false;
	
	public static void main (String[] args) {
		HttpServer1 server1 = new HttpServer1();
		server1.await();
	}

	private void await() {
		// TODO Auto-generated method stub
		ServerSocket serverSocket = null;
		int port = 8080;
		try {
			serverSocket = new ServerSocket(port, 1, 
					InetAddress.getByName("127.0.0.1"));
		} catch( Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
		
		while (!shutdown) {
			Socket socket = null;
			InputStream input = null;
			OutputStream output = null;
			
			try {
				socket = serverSocket.accept();
				System.out.println("Get new socket");
				input = socket.getInputStream();
				System.out.println("Get new input stream");
				output = socket.getOutputStream();
				
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.exit(1);
			}
			
			Request request = new Request(input);
			request.parse();
			
			Response response = new Response(output);
			response.setRequest(request);
			
			if (request.getUri().startsWith("/servlet/")) {
				ServletProcessor1 processor = new ServletProcessor1();
				processor.process(request, response);
			} else {
				StaticServletProcessor processor = new StaticServletProcessor();
				processor.process(request, response);
			}
			
			try {
				socket.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.exit(1);
			}
			
			shutdown = request.getUri().equals(SHUTDOWN_COMMAND_STRING);
			
		}
	}
}
