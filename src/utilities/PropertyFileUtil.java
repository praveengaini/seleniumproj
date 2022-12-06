package utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertyFileUtil {
	
	public static String getValueForKey(String key) throws Throwable
	{
		Properties configprop =new Properties();
		configprop.load(new FileInputStream("E:\\eclipse workspace\\Hybrid_Framework1\\PropertyFiles\\Environment.properties"));
		
		return configprop.getProperty(key);
	}

}
