package stepDefinitions;

import org.apache.commons.lang3.RandomStringUtils;
import org.json.JSONArray;
import org.json.JSONObject;

public class TestData {

	public static JSONObject CreatePetPayload(String status) {
		String randomizedString = RandomStringUtils.randomAlphabetic(8);
		String randomizedNumber = RandomStringUtils.randomNumeric(8);
		
		JSONObject catergoryObject = new JSONObject();
		catergoryObject.put("id", randomizedNumber);
		catergoryObject.put("name", randomizedString);
		
		JSONObject tagsObject = new JSONObject();
		tagsObject.put("id", randomizedNumber);
		tagsObject.put("name", randomizedString);
		JSONArray tagsArrayObject = new JSONArray();
		tagsArrayObject.put(0, tagsObject);
		
		JSONArray photoArrayObject = new JSONArray();
		photoArrayObject.put(0, "https://static.productionready.io/images/smiley-cyrus.jpg");

		JSONObject petObject = new JSONObject();
		petObject.put("category", catergoryObject);
		petObject.put("tags", tagsArrayObject);
		petObject.put("photoUrls", photoArrayObject);
		petObject.put("name", randomizedString);
		petObject.put("id", randomizedNumber);
		petObject.put("status", status);
		
		return petObject;
	}
}
