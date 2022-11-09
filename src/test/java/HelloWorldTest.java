import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

public class HelloWorldTest {

  @Test
  public void testRestAssured() {
    Response response = RestAssured
            .given()
            .body("param1=value1&param2=value2")
            .post("https://playground.learnqa.ru/api/check_type")
            .andReturn();

    response.print();
  }
}
