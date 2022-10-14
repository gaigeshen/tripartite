package work.gaigeshen.tripartite.core.util;

import java.util.Collection;

/**
 *
 * @author gaigeshen
 */
public abstract class AssertUtils {

    private AssertUtils() { }

    public static void isTrue(boolean expression, String message) {
        if (!expression) {
            throw new IllegalArgumentException(message);
        }
    }

    public static void notNull(Object object, String message) {
        if (object == null) {
            throw new IllegalArgumentException(message);
        }
    }

    public static void notEmpty(Collection<?> collection, String message) {
        if (collection == null || collection.isEmpty()) {
            throw new IllegalArgumentException(message);
        }
    }

    public static void notBlank(CharSequence text, String message) {
        int length;
        if (text == null || (length = text.length()) == 0) {
            throw new IllegalArgumentException(message);
        }
        for (int i = 0; i < length; i++) {
            if (!Character.isWhitespace(text.charAt(i))) {
                return;
            }
        }
        throw new IllegalArgumentException(message);
    }
}
