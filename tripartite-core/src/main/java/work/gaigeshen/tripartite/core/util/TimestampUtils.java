package work.gaigeshen.tripartite.core.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 时间戳工具类
 *
 * @author gaigeshen
 */
public abstract class TimestampUtils {

    private TimestampUtils() { }

    /**
     * 返回当前时间戳精确到毫秒的字符串
     *
     * @return 时间戳字符串
     */
    public static String timestamp() {
        return timestamp(new Date());
    }

    /**
     * 返回当前时间戳精确到秒的字符串
     *
     * @return 时间戳字符串
     */
    public static String unixTimestamp() {
        return unixTimestamp(new Date());
    }

    /**
     * 返回指定时间戳精确到毫秒的字符串
     *
     * @param date 指定时间
     * @return 时间戳字符串
     */
    public static String timestamp(Date date) {
        ArgumentValidate.notNull(date, "date cannot be null");
        return String.valueOf(date.getTime());
    }

    /**
     * 返回指定时间戳精确到秒的字符串
     *
     * @param date 指定时间
     * @return 时间戳字符串
     */
    public static String unixTimestamp(Date date) {
        ArgumentValidate.notNull(date, "date cannot be null");
        return String.valueOf(date.getTime() / 1000);
    }

    /**
     * 格式化指定时间
     *
     * @param date 指定时间
     * @param pattern 格式字符串
     * @return 指定时间的格式化结果
     */
    public static String format(Date date, String pattern) {
        ArgumentValidate.notNull(date, "date cannot be null");
        ArgumentValidate.notNull(pattern, "pattern cannot be null");
        return new SimpleDateFormat(pattern).format(date);
    }

    /**
     * 格式化当前时间
     *
     * @param pattern 格式字符串
     * @return 当前时间的格式化结果
     */
    public static String format(String pattern) {
        ArgumentValidate.notNull(pattern, "pattern cannot be null");
        return format(new Date(), pattern);
    }

}
