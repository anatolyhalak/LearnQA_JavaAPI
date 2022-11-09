import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

public class HelloWorldTest {

  @Test
  public void testRestAssured() {
    Response response = RestAssured
            .given()
            .queryParam("name", "John")
            .get("https://playground.learnqa.ru/api/hello")
            .andReturn();
    response.prettyPrint();
  }
}
