import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

public class LongRedirectsTest {

  @Test
  public void testLongRedirects() {
    Response response = RestAssured
            .given()
            .redirects()
            .follow(false)
            .when()
            .get("https://playground.learnqa.ru/api/long_redirect")
            .andReturn();

    String locationHeader = response.getHeader("Location");
    System.out.println(locationHeader);
    int statusCode = response.getStatusCode();
    System.out.println(statusCode);

    while (statusCode != 200) {
      response = RestAssured
              .given()
              .redirects()
              .follow(false)
              .when()
              .get(locationHeader)
              .andReturn();

      locationHeader = response.getHeader("Location");
      System.out.println(locationHeader);
      statusCode = response.getStatusCode();
      System.out.println(statusCode);
    }
  }
}
