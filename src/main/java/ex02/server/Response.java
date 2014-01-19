package ex02.server;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Locale;

import javax.servlet.ServletOutputStream;
import javax.servlet.ServletResponse;

public class Response implements ServletResponse{
	
	private static final int BUFFER_SIZE = 1024; 
	
	private OutputStream outStream = null;
	private PrintWriter writer = null;
	private Request request = null;
	
	public Response(OutputStream output) {
		// TODO Auto-generated constructor stub
		this.outStream = output;
	}

	public void setRequest(Request request) {
		// TODO Auto-generated method stub
		this.request = request;
	}

	public void sendStaticResource() throws IOException {
		byte[] bytes = new byte[BUFFER_SIZE];
		FileInputStream fileInputStream = null;
		
		File file = new File(Constants.WEB_ROOT, request.getUri());
		try {
			fileInputStream = new FileInputStream(file);
			/*
			 * HTTP RESPONSE = Status-Line
			 * 	*( (general-header | response-header | entity-header) CRLF)
			 * 	CRLF
			 *  [message-body]
			 *  
			 *  Status-Line = HTTP-Version SP Status-Code SP Reason-Phrase CRLF
			 */
			int ch = fileInputStream.read(bytes, 0, BUFFER_SIZE);
			while (ch != -1) {
				outStream.write(bytes);
				ch = fileInputStream.read(bytes, 0, BUFFER_SIZE);
			}
		} catch (FileNotFoundException e) {
			String errorMessageString = "HTTP/1.1 404 File Not Found\r\n" +
					"Content-Type: text/html\r\n" + 
					"Content-Length: 23\r\n" +
					"\r\n" + 
					"<h1>file not found</h1>";
			outStream.write(errorMessageString.getBytes());
		} finally {
			if (fileInputStream != null) {
				fileInputStream.close();
			}
		}
		
		
	}

	public void flushBuffer() throws IOException {
		// TODO Auto-generated method stub
		
	}

	public int getBufferSize() {
		// TODO Auto-generated method stub
		return 0;
	}

	public String getCharacterEncoding() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getContentType() {
		// TODO Auto-generated method stub
		return null;
	}

	public Locale getLocale() {
		// TODO Auto-generated method stub
		return null;
	}

	public ServletOutputStream getOutputStream() throws IOException {
		// TODO Auto-generated method stub
		return (ServletOutputStream) outStream;
	}

	public PrintWriter getWriter() throws IOException {
		// TODO Auto-generated method stub
		this.writer = new PrintWriter(outStream, true);
		return writer;
	}

	public boolean isCommitted() {
		// TODO Auto-generated method stub
		return false;
	}

	public void reset() {
		// TODO Auto-generated method stub
		
	}

	public void resetBuffer() {
		// TODO Auto-generated method stub
		
	}

	public void setBufferSize(int arg0) {
		// TODO Auto-generated method stub
		
	}

	public void setCharacterEncoding(String arg0) {
		// TODO Auto-generated method stub
		
	}

	public void setContentLength(int arg0) {
		// TODO Auto-generated method stub
		
	}

	public void setContentType(String arg0) {
		// TODO Auto-generated method stub
		
	}

	public void setLocale(Locale arg0) {
		// TODO Auto-generated method stub
		
	}
}
