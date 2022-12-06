package work.gaigeshen.tripartite.core.client;

import work.gaigeshen.tripartite.core.client.config.Config;
import work.gaigeshen.tripartite.core.client.config.ConfigRepository;

import java.util.Objects;

/**
 * 默认的接口客户端加载器
 *
 * @param <C> 配置信息类型
 */
public class DefaultClientsLoader<C extends Config> implements ClientsLoader<C> {

    private final Clients<C> clients;

    private final ConfigRepository<C> configRepository;

    public DefaultClientsLoader(Clients<C> clients, ConfigRepository<C> configRepository) {
        if (Objects.isNull(clients)) {
            throw new IllegalArgumentException("clients cannot be null");
        }
        if (Objects.isNull(configRepository)) {
            throw new IllegalArgumentException("config repository cannot be null");
        }
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
