import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class ParseJsonTest {


  @Test

  public void testRestAssured() {
    Map<String, String> params = new HashMap<>();
    params.put("header1", "value1");

    JsonPath response = RestAssured
            .given()
            .queryParams(params)
            .get("https://playground.learnqa.ru/api/get_json_homework")
            .jsonPath();

    ArrayList<LinkedHashMap<String, String>> answer = response.get("messages");
    LinkedHashMap<String, String> message = answer.get(1);

    System.out.println(message);
    System.out.println(message.get("message"));


  }
}
