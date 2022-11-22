package homework;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class UserAgentHomeTest {

  @ParameterizedTest
  @ValueSource(strings = {
          "Mozilla/5.0 (Linux; U; Android 4.0.2; en-us; Galaxy Nexus Build/ICL53F) AppleWebKit/534.30 (KHTML, like Gecko) Version/4.0 Mobile Safari/534.30",
          "Mozilla/5.0 (iPad; CPU OS 13_2 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) CriOS/91.0.4472.77 Mobile/15E148 Safari/604.1",
          "Mozilla/5.0 (compatible; Googlebot/2.1; +http://www.google.com/bot.html)",
          "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.77 Safari/537.36 Edg/91.0.100.0",
          "Mozilla/5.0 (iPad; CPU iPhone OS 13_2_3 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/13.0.3 Mobile/15E148 Safari/604.1"
  })
  public void testUserAgent(String userAgent) {
    Response response = RestAssured
            .given()
            .header("User-Agent", userAgent)
            .get("https://playground.learnqa.ru/ajax/api/user_agent_check")
            .andReturn();

    assertAll(
            () -> assertNotEquals(response.jsonPath().get("platform"), "Unknown"),
            () -> assertNotEquals(response.jsonPath().get("browser"), "Unknown"),
            () -> assertNotEquals(response.jsonPath().get("device"), "Unknown")

    );
  }

}
