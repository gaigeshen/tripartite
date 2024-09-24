package work.gaigeshen.tripartite.core.header;

import work.gaigeshen.tripartite.core.util.ArgumentValidate;

import java.util.*;

/**
 * 默认的请求头或者响应头
 *
 * @author gaigeshen
 */
public class DefaultHeaders implements Headers {

    private final Map<String, List<String>> headers = new HashMap<>();

    @Override
    public void addValue(String name, String value) {
        ArgumentValidate.notBlank(name, "name cannot be blank");
        ArgumentValidate.notNull(value, "value cannot be null");
        addValues(name, Collections.singletonList(value));
    }

    @Override
    public void addValues(String name, List<String> values) {
        ArgumentValidate.notBlank(name, "name cannot be blank");
        ArgumentValidate.notEmpty(values, "values cannot be empty");
        headers.computeIfAbsent(name, n -> new ArrayList<>()).addAll(values);
    }

    @Override
    public void putValue(String name, String value) {
        ArgumentValidate.notBlank(name, "name cannot be blank");
        ArgumentValidate.notNull(value, "value cannot be null");
        putValues(name, Collections.singletonList(value));
    }

    @Override
    public void putValues(String name, List<String> values) {
        ArgumentValidate.notBlank(name, "name cannot be blank");
        ArgumentValidate.notEmpty(values, "values cannot be empty");
        headers.put(name, values);
    }

    @Override
    public List<String> getValues(String name) {
        ArgumentValidate.notBlank(name, "name cannot be blank");
        return headers.get(name);
    }

    @Override
    public String getValue(String name) {
        ArgumentValidate.notBlank(name, "name cannot be blank");
        List<String> values = getValues(name);
        if (Objects.isNull(values)) {
            return null;
        }
        return values.get(0);
    }

    @Override
    public boolean contains(String name) {
        ArgumentValidate.notBlank(name, "name cannot be blank");
        return headers.containsKey(name);
    }

    @Override
    public void remove(String name) {
        ArgumentValidate.notBlank(name, "name cannot be blank");
        headers.remove(name);
    }

    @Override
    public String toString() {
        return headers.toString();
    }
}
