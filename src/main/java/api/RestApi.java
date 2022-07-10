package api;

import java.util.HashMap;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import utils.EnvironmentVariables;

public class RestApi {

	private HashMap<String, String> headers;
	private String stubStatus = "{:status}";
	private String stubPetId = "{:petId}";

	public RestApi() {
		RestAssured.baseURI = EnvironmentVariables.API_BASE_URL;
		headers = new HashMap<>();
		headers.put("Accept", "application/json");
	}

	public Response PetGETByStatus(String status) {
		String getUrl = EnvironmentVariables.API_GET_PET_STATUS.replace(stubStatus, status);

		Response response = RestAssured.given().headers(headers).get(EnvironmentVariables.API_BASE_URL + getUrl).andReturn();
		return response;
	}
	
	public Response PetGET(String petId) {
		String getUrl = EnvironmentVariables.API_PET_BY_ID.replace(stubPetId, petId);

		Response response = RestAssured.given().headers(headers).get(EnvironmentVariables.API_BASE_URL + getUrl).andReturn();
		return response;
	}

	public Response PetPOST(String body) {
		headers.put("Content-Type", "application/json");

		Response response = RestAssured.given().headers(headers).body(body).post(EnvironmentVariables.API_BASE_URL + EnvironmentVariables.API_POST_PET).andReturn();
		return response;
	}

	public Response PetPOSTUpdateStatus(String petId, String status) {
		headers.put("Content-Type", "application/x-www-form-urlencoded");
		String petUrl = EnvironmentVariables.API_PET_BY_ID.replace(stubPetId, petId);

		Response response = RestAssured.given().headers(headers).body("status=" + status).post(EnvironmentVariables.API_BASE_URL + petUrl).andReturn();
		return response;
	}

	public Response PetDELETE(String petId) {
		headers.put("api_key", EnvironmentVariables.API_KEY);
		String deleteUrl = EnvironmentVariables.API_PET_BY_ID.replace(stubPetId, petId);

		Response response = RestAssured.given().headers(headers).delete(EnvironmentVariables.API_BASE_URL + deleteUrl).andReturn();
		return response;
	}
}
