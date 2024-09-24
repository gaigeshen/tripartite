package work.gaigeshen.tripartite.core.notify;

import work.gaigeshen.tripartite.core.util.ArgumentValidate;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * 抽象的异步通知数据
 *
 * @author gaigeshen
 */
public abstract class AbstractNotifyContent implements NotifyContent {

    private final Map<String, String[]> headers = new HashMap<>();

    @Override
    public final Map<String, String[]> getHeaders() {
        return headers;
    }

    @Override
    public final void putHeader(String name, String[] values) {
        ArgumentValidate.notNull(name, "name cannot be null");
        ArgumentValidate.notTrue(Objects.isNull(values) || values.length == 0,
                "values cannot be null or empty");
        headers.put(name.toLowerCase(), values);
    }

    @Override
    public final boolean containsHeader(String name) {
        ArgumentValidate.notNull(name, "name cannot be null");
        return headers.containsKey(name.toLowerCase());
    }

    @Override
    public final String[] getHeaderValues(String name) {
        ArgumentValidate.notNull(name, "name cannot be null");
        return headers.get(name.toLowerCase());
    }

    @Override
    public final String getHeaderValue(String name) {
        ArgumentValidate.notNull(name, "name cannot be null");
        String[] values = getHeaderValues(name);
        if (Objects.isNull(values)) {
            return null;
        }
        return values[0];
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        headers.forEach((name, values) -> {
            if (builder.length() > 0) {
                builder.append(", ");
            }
            builder.append(name).append("=").append(Arrays.toString(values));
        });
        return "headers: " + builder;
    }
}
