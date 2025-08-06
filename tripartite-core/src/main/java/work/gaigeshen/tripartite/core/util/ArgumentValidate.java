package work.gaigeshen.tripartite.core.util;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Collection;
import java.util.Objects;

/**
 * 参数校验工具
 *
 * @author gaigeshen
 */
public abstract class ArgumentValidate {

    private ArgumentValidate() { }

    /**
     * 校验目标参数不能为空
     *
     * @param object 目标参数
     * @param message 错误消息内容
     */
    public static void notNull(Object object, String message) {
        if (Objects.isNull(object)) {
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * 校验目标参数不能为空字符串
     *
     * @param charSequence 目标字符串参数
     * @param message 错误消息内容
     */
    public static void notBlank(CharSequence charSequence, String message) {
        if (StringUtils.isBlank(charSequence)) {
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * 校验目标参数不能为空集合
     *
     * @param collection 目标集合参数
     * @param message 错误消息内容
     */
    public static void notEmpty(Collection<?> collection, String message) {
        if (CollectionUtils.isEmpty(collection)) {
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * 校验目标参数计算结果不能为真
     *
     * @param condition 目标计算表达式参数
     * @param message 错误消息内容
     */
    public static void notTrue(boolean condition, String message) {
        if (condition) {
            throw new IllegalArgumentException(message);
        }
    }
}
