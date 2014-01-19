package ex02.server;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLStreamHandler;

import javax.servlet.Servlet;
import javax.servlet.ServletException;

public class ServletProcessor1 {

	public void process(Request request, Response response) {
		String uri = request.getUri();
		String servletName = uri.substring(uri.lastIndexOf('/') + 1);
		URLClassLoader loader = null;
		
		URL[] urls = new URL[1];
		URLStreamHandler streamHandler = null;
		File classPath = new File(Constants.WEB_ROOT);
		String repository = null;
		try {
			repository = (new URL("file", null, (classPath.getCanonicalPath() + 
					File.separator).toString()).toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			urls[0] = new URL(null, repository, streamHandler);
		} catch (MalformedURLException e1) {
			e1.printStackTrace();
		}
		loader = new URLClassLoader(urls);
		
		Class<?> myClass = null;
		
		try {
			myClass = loader.loadClass(servletName);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		Servlet servlet = null;
		
		try {
			servlet = (Servlet) myClass.newInstance();
			servlet.service(request, response);
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
