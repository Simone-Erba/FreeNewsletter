package program;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesReader {
	static PropertiesReader istanza;
	public static synchronized PropertiesReader getInstance() {
		if (istanza == null) {
			istanza = new PropertiesReader();
		}
		return istanza;
	}
	  public synchronized String get(String p ){

	    	Properties prop = new Properties();
	    	InputStream input = null;

	    	try {
	    		//String filename = "C:/Users/leonardo/Desktop/gitNewsletter/src/resources/config.properties";
	    		//input = new FileInputStream(filename);
	    		input = PropertiesReader.class.getResourceAsStream("config.properties");
	    		//load a properties file from class path, inside static method
	    		prop.load(input);

	                //get the property value and print it out
	                return prop.getProperty(p);

	    	} catch (IOException ex) {
	    		ex.printStackTrace();
	        } finally{
	        	if(input!=null){
	        		try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
	        	}
	        }

	    return null;
	}
}
