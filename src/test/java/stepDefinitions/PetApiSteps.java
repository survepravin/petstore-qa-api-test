package stepDefinitions;

import org.json.JSONObject;
import org.junit.Assert;

import api.RestApi;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;

public class PetApiSteps {

	String petId = "";
	String petStatus = "";
	RestApi api;
	Scenario scenario;

	@Before("@api")
	public void beforeApi(Scenario scenario) {
		this.scenario = scenario;
		api = new RestApi();
	}

	@After("@api")
	public void afterApi() {
		petId = "";
		petStatus = "";
	}

	@When("Store owner should able to search pets based on {string}")
	public void store_owner_should_able_to_search_pets_based_on(String status) {
		scenario.log("GET pet with " + status + " status");
		Response getResponse = api.PetGETByStatus(status);
		
		scenario.log("GET pet response: " + getResponse.body().jsonPath().getString("[0]"));
		Assert.assertTrue("Unable to GET Pet by Status: " + status, getResponse.statusCode() == 200);

		String isAvailable = getResponse.body().jsonPath().getString("[0].status");
		Assert.assertEquals("Pet Status is not as expected", status, isAvailable);
	}

	@When("Creates a new pet with status as {string}")
	public void creates_a_new_pet_with_status_as(String status) {
		JSONObject testdataPOST = new JSONObject();
		testdataPOST = TestData.CreatePetPayload(status);
		scenario.log("POST pet request: " + testdataPOST.toString());
		
		Response postResponse = api.PetPOST(testdataPOST.toString());
		scenario.log("POST pet response: " + postResponse.prettyPrint());
		Assert.assertTrue("Unable to CREATE new pet", postResponse.statusCode() == 200);

		String expectedId = testdataPOST.getString("id");
		String actualId = postResponse.body().jsonPath().getString("id");
		Assert.assertEquals("ID doesn't match in response" , expectedId, actualId);
		petId = actualId;

		String expectedName = testdataPOST.getString("name");
		String actualName = postResponse.body().jsonPath().getString("name");
		Assert.assertEquals("NAME doesn't match in response" , expectedName, actualName);

		String expectedStatus = testdataPOST.getString("status");
		String actualStatus = postResponse.body().jsonPath().getString("status");
		Assert.assertEquals("STATUS doesn't match in response" , expectedStatus, actualStatus);
		petStatus = actualStatus;
	}

	@And("Verify pet details")
	public void verify_pet_details() {
		scenario.log("GET pet " + petId + " details");
		Response postResponse = api.PetGET(petId);
		scenario.log("GET pet details response: " + postResponse.prettyPrint());
		Assert.assertTrue("Unable to GET pet details: " + petId, postResponse.statusCode() == 200);

		String id = postResponse.body().jsonPath().getString("id");
		Assert.assertEquals("Body {id} is not as expected" , petId, id);

		String actualStatus = postResponse.body().jsonPath().getString("status");
		Assert.assertEquals("Body {status} is not as expected" , petStatus, actualStatus);
	}

	@When("Updates the pet status to {string}")
	public void updates_the_pet_status_to(String status) {
		scenario.log("Update pet with status: " + status);
		Response postResponse = api.PetPOSTUpdateStatus(petId, status);
		scenario.log("Updated pet details response: " + postResponse.prettyPrint());
		Assert.assertTrue("Unable to UPDATE status for pet: " + petId, postResponse.statusCode() == 200);

		String code = postResponse.body().jsonPath().getString("code");
		Assert.assertEquals("Body {code} is not as expected" , "200", code);

		String message = postResponse.body().jsonPath().getString("message");
		Assert.assertEquals("Body {message} is not as expected" , petId, message);
		petStatus = status;
	}

	@Then("Deletes the pet")
	public void deletes_the_pet() {
		scenario.log("Delete pet: " + petId);
		Response deleteResponse = api.PetDELETE(petId);
		Assert.assertTrue("Unable to DELETE pet: " + petId, deleteResponse.statusCode() == 200);

		String code = deleteResponse.body().jsonPath().getString("code");
		Assert.assertEquals("Body {code} is not as expected" , "200", code);

		String message = deleteResponse.body().jsonPath().getString("message");
		Assert.assertEquals("Body {message} is not as expected" , petId, message);
	}

	@And("Verify pet is deleted")
	public void verify_pet_is_deleted() {
		Response postResponse = api.PetGET(petId);
		Assert.assertTrue("Pet is not deleted: " + petId, postResponse.statusCode() == 404);
	}
}
