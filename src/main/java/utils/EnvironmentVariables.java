package utils;

public class EnvironmentVariables {
	static ConfigProperties config = new ConfigProperties();
	public final static String API_KEY = config.getPropertyValue("API_KEY");
	public final static String API_BASE_URL = config.getPropertyValue("API_BASE_URL");
	public final static String API_GET_PET_STATUS = config.getPropertyValue("API_GET_PET_STATUS");
	public final static String API_POST_PET = config.getPropertyValue("API_POST_PET");
	public final static String API_PET_BY_ID = config.getPropertyValue("API_PET_BY_ID");
}
