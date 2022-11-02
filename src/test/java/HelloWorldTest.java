import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

public class HelloWorldTest {

  @Test
  public void testHelloWorld(){
    Response response = RestAssured
            .get("https://playground.learnqa.ru/api/hello")
            .andReturn();
    response.prettyPrint();
  }
}