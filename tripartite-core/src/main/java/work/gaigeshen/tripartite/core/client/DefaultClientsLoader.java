package work.gaigeshen.tripartite.core.client;

import work.gaigeshen.tripartite.core.client.config.Config;
import work.gaigeshen.tripartite.core.client.config.ConfigRepository;
import work.gaigeshen.tripartite.core.util.ArgumentValidate;

/**
 * 默认的接口客户端加载器
 *
 * @param <C> 配置信息类型
 */
public class DefaultClientsLoader<C extends Config> implements ClientsLoader<C> {

    private final Clients<C> clients;

    private final ConfigRepository<C> configRepository;

    public DefaultClientsLoader(Clients<C> clients, ConfigRepository<C> configRepository) {
        ArgumentValidate.notNull(clients, "clients cannot be null");
        ArgumentValidate.notNull(configRepository, "configRepository cannot be null");
        this.clients = clients;
        this.configRepository = configRepository;
    }

    @Override
    public Clients<C> getClients() {
        return clients;
    }

    @Override
    public ConfigRepository<C> getConfigRepository() {
        return configRepository;
    }
}
