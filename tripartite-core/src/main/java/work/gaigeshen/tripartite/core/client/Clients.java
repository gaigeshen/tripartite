package work.gaigeshen.tripartite.core.client;

import work.gaigeshen.tripartite.core.client.config.Config;

import java.util.function.Predicate;

/**
 * 维护所有的客户端
 *
 * @author gaigeshen
 */
public interface Clients<C extends Config> {

    /**
     * 直接获取循环到的首个客户端
     *
     * @return 获取到的客户端
     * @throws ClientNotFoundException 客户端未找到的异常
     */
    default Client<C> getClient() throws ClientNotFoundException {
        return getClient(cfg -> true);
    }

    /**
     * 获取配置信息，由于配置信息是从关联的客户端中获取的，所以如果未找到指定的客户端则会抛出异常
     *
     * @param predicate 指定获取配置信息的条件
     * @return 获取到的配置信息
     * @throws ClientNotFoundException 客户端未找到的异常
     */
    default C getConfig(Predicate<C> predicate) throws ClientNotFoundException {
        return getClient(predicate).getConfig();
    }

    /**
     * 获取客户端
     *
     * @param predicate 指定获取客户端的条件
     * @return 获取到的客户端
     * @throws ClientNotFoundException 客户端未找到的异常
     */
    Client<C> getClient(Predicate<C> predicate) throws ClientNotFoundException;

    /**
     * 调用此方法将有可能创建新的客户端，如果指定的客户端不存在的话
     *
     * @param config 配置信息
     * @return 获取到的客户端
     * @throws ClientCreationException 在需要创建客户端的时候，如果创建客户端失败的情况将抛出此异常
     */
    Client<C> getClientOrCreate(C config) throws ClientCreationException;
}
