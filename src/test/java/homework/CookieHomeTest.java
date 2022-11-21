package homework;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class CookieHomeTest {

  @Test
  public void testCookieHome() {
    Response response = RestAssured
            .get("https://playground.learnqa.ru/api/homework_cookie")
            .andReturn();

    assertEquals(response.getStatusCode(), 200,
            "Status code should be 200");

    Map<String, String> cookies = response.getCookies();
    HashMap<String, String> expected = new HashMap<>();
    expected.put("HomeWork", "hw_value");
    assertEquals(expected, cookies);
  }

}
