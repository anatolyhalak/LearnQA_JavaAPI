package homework;

import io.restassured.RestAssured;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class HeaderHomeTest {

  @Test
  public void testHeaderHome() {
    Response response = RestAssured
            .get("https://playground.learnqa.ru/api/homework_header")
            .andReturn();

    assertEquals(response.getStatusCode(), 200,
            "Status code should be 200");

    Headers headers = response.getHeaders();
    String expected = "Content-Type=application/json\n" +
            "Content-Length=15\n" +
            "Connection=keep-alive\n" +
            "Keep-Alive=timeout=10\n" +
            "Server=Apache\n" +
            "x-secret-homework-header=Some secret value\n" +
            "Cache-Control=max-age=0";
    assertTrue(headers.toString().contains(expected));
  }

}
