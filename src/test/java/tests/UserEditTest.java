package tests;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import lib.Assertions;
import lib.BaseTestCase;
import lib.DataGenerator;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class UserEditTest extends BaseTestCase {
  @Test
  public void testEditJustCreatedTest() {
  //GENERATE user
    Map<String, String> userData = DataGenerator.getRegistrationData();

    JsonPath responseCreateAuth = RestAssured
            .given()
            .body(userData)
            .post("https://playground.learnqa.ru/api/user/")
            .jsonPath();

    String userId = responseCreateAuth.getString("id");

    //LOGIN
    Map<String, String> authData = new HashMap<>();
    authData.put("email", userData.get("email"));
    authData.put("password", userData.get("password"));

    Response responseGetAuth = RestAssured
            .given()
            .body(authData)
            .post("https://playground.learnqa.ru/api/user/login")
            .andReturn();

    //EDIT
    String newName = "Changed Name";
    Map<String, String> editData = new HashMap<>();
    editData.put("firstName", newName);

    Response responseEditUser = RestAssured
            .given()
            .header("x-csrf-token", this.getHeader(responseGetAuth, "x-csrf-token"))
            .cookie("auth_sid", this.getCookie(responseGetAuth, "auth_sid"))
            .body(editData)
            .put("https://playground.learnqa.ru/api/user" + userId)
            .andReturn();

    //GET
    Response responseUserData = RestAssured
            .given()
            .header("x-csrf-token", this.getHeader(responseGetAuth, "x-csrf-token"))
            .cookie("auth_sid", this.getCookie(responseGetAuth, "auth_sid"))
            .get("https://playground.learnqa.ru/api/user" + userId)
            .andReturn();

    Assertions.asserJsonByName(responseUserData, "firstName", newName);
  }
}
