import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class HelloWorldTest {

  @Test
  public void testRestAssured() {
    Response response = RestAssured
            .get("https://playground.learnqa.ru/api/check_type")
            .andReturn();

    int statusCode = response.getStatusCode();
    System.out.println(statusCode);
  }
}

