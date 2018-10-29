package utils;

public class Utils {
  public static void checkIfNull(Object o, String errMsg) {
    if (errMsg == null)
      throw new IllegalArgumentException("null error message.");

    if (o == null)
      throw new NullPointerException(errMsg);
  }
}
