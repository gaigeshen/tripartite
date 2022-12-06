package work.gaigeshen.tripartite.core.client;

import work.gaigeshen.tripartite.core.client.config.Config;
import work.gaigeshen.tripartite.core.client.config.ConfigException;
import work.gaigeshen.tripartite.core.client.config.ConfigRepository;

import java.util.Objects;

/**
 * 接口客户端加载器，当需要创建新的接口客户端的时候有用，依赖配置信息资源库来获取所有的配置信息，同时也依赖接口客户端集合对象，用于维护所有的接口客户端
 *
 * @param <C> 配置信息类型
 */
public interface ClientsLoader<C extends Config> {

    /**
     * 调用此方法将会可能创建新的接口客户端，取决于从配置信息资源库获取的配置信息是否已经创建过接口客户端
     *
     * @throws ConfigException 无法从关联的配置信息资源库中获取配置信息
     * @throws ClientCreationException 在创建接口客户端的时候发生异常
     */
    default void load() throws ConfigException, ClientCreationException {
        ConfigRepository<C> repository = getConfigRepository();
        Clients<C> clients = getClients();
        if (Objects.isNull(repository) || Objects.isNull(clients)) {
            return;
        }
        repository.findAll().forEach(clients::getClientOrCreate);
    }

    /**
     * 获取此加载器关联的接口客户端集合对象
     *
     * @return 关联的接口客户端集合对象
     */
    Clients<C> getClients();

    /**
     * 获取此加载器关联的配置信息资源库
     *
     * @return 关联的配置信息资源库
     */
    ConfigRepository<C> getConfigRepository();
}
