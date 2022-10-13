package work.gaigeshen.tripartite.core.client;

import work.gaigeshen.tripartite.core.client.config.Config;

/**
 * 客户端选择器，用于特定场景下通过配置信息寻找客户端
 *
 * @author gaigeshen
 */
public interface ClientSelector<C extends Config> {

    /**
     * 此方法可能抛出任何异常，调用此方法尝试寻找该配置信息对应的客户端
     *
     * @param config 配置信息
     * @return 不要返回空对象，如果寻找客户端失败则抛出运行时异常
     */
    Client<C> select(C config);
}
