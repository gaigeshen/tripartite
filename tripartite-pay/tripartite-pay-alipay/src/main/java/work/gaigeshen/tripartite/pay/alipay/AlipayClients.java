package work.gaigeshen.tripartite.pay.alipay;

import work.gaigeshen.tripartite.pay.alipay.config.AlipayConfig;

import java.util.Objects;
import java.util.function.Predicate;

/**
 * 多支付宝客户端
 *
 * @author gaigeshen
 */
public interface AlipayClients {
    /**
     * 查询支付宝客户端的配置
     *
     * @param appId 应用编号不能为空
     * @return 支付宝客户端的配置不为空
     * @throws AlipayClientNotFoundException 没有找到对应的支付宝客户端
     */
    default AlipayConfig getConfig(String appId) throws AlipayClientNotFoundException {
        if (Objects.isNull(appId)) {
            throw new IllegalArgumentException("appId cannot be null");
        }
        return getConfig(ac -> Objects.equals(ac.getAppId(), appId));
    }

    /**
     * 查询支付宝客户端的配置
     *
     * @param predicate 查询条件不能为空
     * @return 支付宝客户端的配置不为空
     * @throws AlipayClientNotFoundException 没有找到对应的支付宝客户端
     */
    default AlipayConfig getConfig(Predicate<AlipayConfig> predicate) throws AlipayClientNotFoundException {
        return getClient(predicate).getAlipayConfig();
    }

    /**
     * 查询支付宝客户端
     *
     * @param appId 应用编号不能为空
     * @return 支付宝客户端不为空
     * @throws AlipayClientNotFoundException 没有找到对应的支付宝客户端
     */
    default AlipayClient getClient(String appId) throws AlipayClientNotFoundException {
        return getClient(ac -> Objects.equals(ac.getAppId(), appId));
    }

    /**
     * 查询支付宝客户端
     *
     * @param predicate 查询条件不能为空
     * @return 支付宝客户端不为空
     * @throws AlipayClientNotFoundException 没有找到对应的支付宝客户端
     */
    AlipayClient getClient(Predicate<AlipayConfig> predicate) throws AlipayClientNotFoundException;

    /**
     * 查询支付宝客户端，此方法只会返回首个支付宝客户端，如果不存在任何客户端则会抛出异常
     *
     * @return 支付宝客户端不为空
     * @throws AlipayClientNotFoundException 没有找到对应的支付宝客户端
     */
    AlipayClient getClient() throws AlipayClientNotFoundException;
}
