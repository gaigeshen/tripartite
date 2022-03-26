package work.gaigeshen.tripartite.core.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

/**
 *
 * @author gaigeshen
 */
public abstract class TimestampUtils {

  public static String timestamp() {
    return timestamp(new Date());
  }

  public static String unixTimestamp() {
    return unixTimestamp(new Date());
  }

  public static String timestamp(Date date) {
    if (Objects.isNull(date)) {
      throw new IllegalArgumentException("date cannot be null");
    }
    return String.valueOf(date.getTime());
  }

  public static String unixTimestamp(Date date) {
    if (Objects.isNull(date)) {
      throw new IllegalArgumentException("date cannot be null");
    }
    return String.valueOf(date.getTime() / 1000);
  }

  public static String format(Date date, String pattern) {
    if (Objects.isNull(date)) {
      throw new IllegalArgumentException("date cannot be null");
    }
    if (Objects.isNull(pattern)) {
      throw new IllegalArgumentException("pattern cannot be null");
    }
    return new SimpleDateFormat(pattern).format(date);
  }

  public static String format(String pattern) {
    if (Objects.isNull(pattern)) {
      throw new IllegalArgumentException("pattern cannot be null");
    }
    return format(new Date(), pattern);
  }

}
