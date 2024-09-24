package work.gaigeshen.tripartite.core.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author gaigeshen
 */
public abstract class TimestampUtils {

    private TimestampUtils() { }

    public static String timestamp() {
        return timestamp(new Date());
    }

    public static String unixTimestamp() {
        return unixTimestamp(new Date());
    }

    public static String timestamp(Date date) {
        ArgumentValidate.notNull(date, "date cannot be null");
        return String.valueOf(date.getTime());
    }

    public static String unixTimestamp(Date date) {
        ArgumentValidate.notNull(date, "date cannot be null");
        return String.valueOf(date.getTime() / 1000);
    }

    public static String format(Date date, String pattern) {
        ArgumentValidate.notNull(date, "date cannot be null");
        ArgumentValidate.notNull(pattern, "pattern cannot be null");
        return new SimpleDateFormat(pattern).format(date);
    }

    public static String format(String pattern) {
        ArgumentValidate.notNull(pattern, "pattern cannot be null");
        return format(new Date(), pattern);
    }

}
