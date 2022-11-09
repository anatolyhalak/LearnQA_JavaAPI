import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

public class HelloWorldTest {

  @Test
  public void testRestAssured() {
    Response response = RestAssured
            .get("https://playground.learnqa.ru/api/check_type")
            .andReturn();

    response.prettyPrint();
  }
}
