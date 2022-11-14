import io.restassured.RestAssured;
import io.restassured.internal.common.assertion.Assertion;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.commons.lang3.Validate;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static java.lang.Thread.sleep;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TokenHomeTest {

  @Test
  public void testTokenHome() throws InterruptedException {

    //создает задачу
    String jobUrl = "https://playground.learnqa.ru/ajax/api/longtime_job";
    JsonPath response = RestAssured
            .get(jobUrl)
            .jsonPath();

    String token = response.get("token");
    int time = response.get("seconds");
    System.out.println("Token:" + token);
    System.out.println("Time to ready:" + time);

    // делает один запрос с token ДО того, как задача готова, убеждается в правильности поля status
    Map<String, String> params = new HashMap<>();
    params.put("token", token);

    JsonPath response1 = RestAssured
            .given()
            .queryParams(params)
            .get(jobUrl)
            .jsonPath();

    String text = response1.get("status");
    assertEquals("Job is NOT ready", text);

    // ждет нужное количество секунд с помощью функции time.sleep() - для этого надо сделать import time
    Thread.sleep(time * 1000);

    // делает один запрос c token ПОСЛЕ того, как задача готова, убеждается в правильности поля status и наличии поля result
    JsonPath response2 = RestAssured
            .given()
            .queryParams(params)
            .get(jobUrl)
            .jsonPath();

    String result = response2.get("result");
    String resultStatus = response2.get("status");

    Validate.notNull(result);
    assertEquals("Job is ready", resultStatus);

    System.out.println("Result:" + result);
    System.out.println(resultStatus);
  }
}
