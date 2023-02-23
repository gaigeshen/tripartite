package work.gaigeshen.tripartite.core.parameter;

import java.util.*;

/**
 * 默认的请求参数
 *
 * @author gaigeshen
 */
public class DefaultParameters implements Parameters {

    private final Collection<Parameter<?>> parameters = new TreeSet<>();

    private final Type type;

    public DefaultParameters(Type type) {
        if (Objects.isNull(type)) {
            throw new IllegalArgumentException("type cannot be null");
        }
        this.type = type;
    }

    @Override
    public Type getType() {
        return type;
    }

    @Override
    public Parameters put(Collection<Parameter<?>> parameters) {
        if (Objects.isNull(parameters)) {
            return this;
        }
        for (Parameter<?> parameter : parameters) {
            if (Objects.isNull(parameter)) {
                continue;
            }
            this.parameters.add(parameter);
        }
        return this;
    }

    @Override
    public Parameters put(Parameter<?>... parameters) {
        if (Objects.isNull(parameters)) {
            return this;
        }
        return put(Arrays.asList(parameters));
    }

    @Override
    public Iterator<Parameter<?>> iterator() {
        return parameters.iterator();
    }

    @Override
    public String toString() {
        return parameters.toString();
    }
}
