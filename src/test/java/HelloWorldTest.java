import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class HelloWorldTest {

  @Test
  public void testRestAssured() {
    Map<String, String> params = new HashMap<>();
    params.put("name", "John");

    Response response = RestAssured
            .given()
            .queryParams(params)
            .get("https://playground.learnqa.ru/api/hello")
            .andReturn();
    response.prettyPrint();
  }
}
