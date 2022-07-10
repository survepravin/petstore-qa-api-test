package utils;

import java.io.InputStream;
import java.util.Properties;

public class ConfigProperties {

	private static String configFilePath = "config.properties";
	private static Properties prop = new Properties();

	public ConfigProperties() {
		loadProperties();
	}

	private void loadProperties() {
		try {
			ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
			InputStream input = classLoader.getResourceAsStream(configFilePath);
			if (input != null) {
				prop.load(input);
			} else {
				new Exception("Error in getting properties: " + configFilePath);
			}
		} catch (Exception ex) {
		}
	}
	
	public String getPropertyValue(String key) {
		return prop.getProperty(key);
	}
}
