package lib;

import io.restassured.response.Response;

import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.not;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Assertions {
  public static void asserJsonByName(Response Response, String name, int expectedValue) {
    Response.then().assertThat().body("$", hasKey(name));

    int value = Response.jsonPath().getInt(name);
    assertEquals(expectedValue, value, "JSON value is not equal to expected value");
  }
  public static void assertResponseTextEquals(Response Response, String expectedAnswer) {
    assertEquals(
            expectedAnswer,
            Response.asString(),
            "Response text is not as expected"
    );
  }
  public static void assertResponseCodeEquals(Response Response, int expectedStatusCode) {
    assertEquals(
            expectedStatusCode,
            Response.statusCode(),
            "Response status code is not as expected"
    );
  }

  //метод проверяет что приходит поле с определенным именем этого поля
  public static void assertJsonHasField(Response Response, String expectedFieldName) {
    Response.then().assertThat().body("$", hasKey(expectedFieldName));
  }

  //метод проверяет все поля через цикл for
  //на вход вторым параметром принимает массив со списком полей
  public static void assertJsonHasFields(Response Response, String[] expectedFieldNames) {
    for (String expectedFieldName : expectedFieldNames) {
      Assertions.assertJsonHasField(Response, expectedFieldName);
    }
  }

  //метод который проверяет что в ответе нет каких-то полей по названиям
  public static void assertJsonHasNotField(Response Response, String unexpectedFieldName) {
    Response.then().assertThat().body("$", not(hasKey(unexpectedFieldName)));
  }


}
