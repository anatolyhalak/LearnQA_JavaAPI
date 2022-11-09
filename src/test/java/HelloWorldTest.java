import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class HelloWorldTest {

  @Test
  public void testRestAssured() {
    Map<String, String> params = new HashMap<>();
    params.put("name", "John");

    JsonPath response = RestAssured
            .given()
            .queryParams(params)
            .get("https://playground.learnqa.ru/api/hello")
            .jsonPath();

    String name = response.get("answer2");
    if (name == null) {
      System.out.println("The key 'answer2' is absent");
    } else {
      System.out.println(name);
    }
  }
}
