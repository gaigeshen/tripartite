package work.gaigeshen.tripartite.core.notify;

import java.util.Objects;

/**
 * 异步通知参数，与异步通知数据体同样都属于异步通知数据
 *
 * @author gaigeshen
 */
public interface NotifyParameters extends NotifyContent, Iterable<NotifyParameters.Parameter> {
    /**
     * 追加参数
     *
     * @param name  参数名称不能为空
     * @param value 参数值不能为空
     */
    void put(String name, Object value);

    /**
     * 删除参数
     *
     * @param name 参数名称不能为空
     */
    void remove(String name);

    /**
     * 获取参数值
     *
     * @param name 参数名称不能为空
     * @return 参数值可能为空
     */
    Object getValue(String name);


    /**
     * 参数接口
     *
     * @author gaigeshen
     */
    interface Parameter extends Comparable<Parameter> {
        /**
         * 返回参数名称
         *
         * @return 参数名称不为空
         */
        String getName();

        /**
         * 返回参数值
         *
         * @return 参数值不为空
         */
        Object getValue();

    }

    /**
     * 默认的参数接口实现，按参数名称自然排序
     *
     * @author gaigeshen
     */
    class DefaultParameter implements Parameter {
        private final String name;
        private final Object value;

        public DefaultParameter(String name, Object value) {
            if (Objects.isNull(name)) {
                throw new IllegalArgumentException("name cannot be null");
            }
            if (Objects.isNull(value)) {
                throw new IllegalArgumentException("value cannot be null");
            }
            this.name = name;
            this.value = value;
        }

        @Override
        public String getName() {
            return name;
        }

        @Override
        public Object getValue() {
            return value;
        }

        @Override
        public int compareTo(Parameter other) {
            if (Objects.isNull(other)) {
                throw new IllegalArgumentException("other parameter cannot be null");
            }
            return getName().compareTo(other.getName());
        }

        @Override
        public String toString() {
            return name + " = " + value;
        }
    }
}
