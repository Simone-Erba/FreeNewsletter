package program;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Data {
private final String url="http://localhost:8080/newsletter/";
public String getUrl()
{
	/*Properties prop = new Properties();
	InputStream input = null;
String url2=null;
String user=null;
	try {


		ClassLoader t=Thread.currentThread().getContextClassLoader();
		System.out.println(t);
		input = t.getResourceAsStream("config.properties");

		prop.load(input);

		url2=prop.getProperty("url");
		System.out.println(url);
		user=prop.getProperty("users");
		System.out.println(user);
	} catch (IOException ex) {
		ex.printStackTrace();
	} finally {
		if (input != null) {
			try {
			input.close();
			} catch (IOException e) {
				e.printStackTrace();
		}
		}
	}*/
	return url;
}
}
