package lib;

import java.text.SimpleDateFormat;
//получаем уникальное число, которое является годом, месяцем, днем, часом, минутой, секундой
//затем это уникальное число добавляем к email перед доменом
//итоговое значение возвращаем
public class DataGenerator {
  public static String getRandomEmail() {
    String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new java.util.Date());
    return "learnqa" + timestamp + "@example.com";
  }
}
