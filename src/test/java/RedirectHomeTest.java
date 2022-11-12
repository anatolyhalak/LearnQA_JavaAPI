import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

public class RedirectHomeTest {

  @Test
  public void testRedirectHome() {

    Response response = RestAssured
            .given()
            .redirects()
            .follow(false)
            .when()
            .get("https://playground.learnqa.ru/api/long_redirect")
            .andReturn();

    response.prettyPrint();
    String locationHeader = response.getHeader("Location");
    System.out.println(locationHeader);
  }
}
