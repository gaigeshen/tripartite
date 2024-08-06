package work.gaigeshen.tripartite.core.notify;

import work.gaigeshen.tripartite.core.util.ArgumentValidate;

import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;

/**
 * 异步通知参数抽象
 *
 * @author gaigeshen
 */
public abstract class AbstractNotifyParameters extends AbstractNotifyContent implements NotifyParameters {

    private final Map<String, Parameter> parameters = new TreeMap<>();

    @Override
    public final void put(String name, Object value) {
        ArgumentValidate.notNull(name, "name cannot be null");
        ArgumentValidate.notNull(value, "value cannot be null");
        parameters.put(name, new DefaultParameter(name, value));
    }

    @Override
    public final void remove(String name) {
        ArgumentValidate.notNull(name, "name cannot be null");
        parameters.remove(name);
    }

    @Override
    public final Object getValue(String name) {
        ArgumentValidate.notNull(name, "name cannot be null");
        Parameter parameter = parameters.get(name);
        if (Objects.isNull(parameter)) {
            return null;
        }
        return parameter.getValue();
    }

    @Override
    public final Iterator<Parameter> iterator() {
        return parameters.values().iterator();
    }

    @Override
    public final String toString() {
        return super.toString() + ", parameters: " + parameters.values();
    }
}
