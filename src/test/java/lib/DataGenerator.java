package lib;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

//получаем уникальное число, которое является годом, месяцем, днем, часом, минутой, секундой
//затем это уникальное число добавляем к email перед доменом
//итоговое значение возвращаем
public class DataGenerator {
  public static String getRandomEmail() {
    String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new java.util.Date());
    return "learnqa" + timestamp + "@example.com";
  }

  //вынесена вся логика по созданию регистрации пользователя
  //указаны два метода, хоть и называются они одинаково
  public static Map<String, String> getRegistrationData() {
    Map<String, String> data = new HashMap<>();
    data.put("email", DataGenerator .getRandomEmail());
    data.put("password", "123");
    data.put("username", "learnqa");
    data.put("firstName", "learnqa");
    data.put("lastName", "learnqa");

    return data;
  }

  public static Map<String, String> getRegistrationData(Map<String, String> nonDefaultValues) {
    Map<String, String> defaultValues = DataGenerator.getRegistrationData();

    Map<String, String> userData = new HashMap<>();
    String[] keys = {"email", "password", "username", "firstName", "lastName"};
    for (String key : keys) {
      if (nonDefaultValues.containsKey(key)) {
        userData.put(key, nonDefaultValues.get(key));
      } else {
        userData.put(key, defaultValues.get(key));
      }
    }
    return userData;
  }

}
