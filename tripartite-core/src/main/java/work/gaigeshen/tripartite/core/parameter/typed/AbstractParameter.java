package work.gaigeshen.tripartite.core.parameter.typed;

import work.gaigeshen.tripartite.core.parameter.Parameter;
import work.gaigeshen.tripartite.core.util.ArgumentValidate;

import java.util.Objects;

/**
 * 抽象的单个请求参数
 *
 * @author gaigeshen
 */
public class AbstractParameter<T> implements Parameter<T> {
    private final String name;
    private final T value;

    public AbstractParameter(String name, T value) {
        ArgumentValidate.notTrue(Objects.isNull(name) || Objects.isNull(value),
                "name and value cannot be null");
        this.name = name;
        this.value = value;
    }

    @Override
    public final String getName() {
        return name;
    }

    @Override
    public final T getValue() {
        return value;
    }

    @Override
    public final int hashCode() {
        return name.hashCode();
    }

    @Override
    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof AbstractParameter)) {
            return false;
        }
        return name.equals(((AbstractParameter<?>) obj).name);
    }

    @Override
    public final int compareTo(Parameter<?> parameter) {
        ArgumentValidate.notNull(parameter, "parameters cannot be null");
        return getName().compareTo(parameter.getName());
    }
}
