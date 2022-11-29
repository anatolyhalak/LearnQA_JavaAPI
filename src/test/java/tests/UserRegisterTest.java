package tests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import lib.Assertions;
import lib.BaseTestCase;
import lib.DataGenerator;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
//проверка api теста с email, который уже существует в системе
//расширен класс с проверками
public class UserRegisterTest extends BaseTestCase {
  @Test
  public void testCreateUserWithExitingEmail() {
    String email = "vinkotov@example.com";

    Map<String, String> userData = new HashMap<>();
    userData.put("email", email);
    userData = DataGenerator.getRegistrationData(userData);

    Response responseCreateAuth = RestAssured
            .given()
            .body(userData)
            .post("https://playground.learnqa.ru/api/user/")
            .andReturn();

    Assertions.assertResponseCodeEquals(responseCreateAuth, 400);
    Assertions.assertResponseTextEquals(responseCreateAuth, "Users with email '" + email + "' already exists");
  }

  @Test
  public void testCreateUserSuccessfully() {
    String email = DataGenerator.getRandomEmail();

    Map<String, String> userData = DataGenerator.getRegistrationData();

    Response responseCreateAuth = RestAssured
            .given()
            .body(userData)
            .post("https://playground.learnqa.ru/api/user/")
            .andReturn();

    Assertions.assertResponseCodeEquals(responseCreateAuth, 200);
    Assertions.assertJsonHasField(responseCreateAuth, "id");
  }
}
