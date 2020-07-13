package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class ReadConfig {
	private Properties prop;
	
	public ReadConfig() {
		File src = new File("./Configuration/config.properties");
		try {
			FileInputStream fileInputStream = new FileInputStream(src);
			prop = new Properties();
			prop.load(fileInputStream);
		} catch(Exception e) {
			System.out.println("Exception is : "+e.getMessage());
		}
	}
	
	public String getApplicationURL() {
		return prop.getProperty("baseURL");
	}
	
	public String getUserName() {
		return prop.getProperty("userName");
	}
	
	public String getPassword() {
		return prop.getProperty("password");
	}
	
	public String getChromePath() {
		return prop.getProperty("chromepath");
	}
	
	public String getFirefoxPath() {
		return prop.getProperty("firefoxpath");
	}
	
	public String getIePath() {
		return prop.getProperty("iepath");
	}
}
