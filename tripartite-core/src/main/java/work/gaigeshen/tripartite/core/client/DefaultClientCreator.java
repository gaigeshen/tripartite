package work.gaigeshen.tripartite.core.client;

import work.gaigeshen.tripartite.core.client.config.Config;

import java.util.Objects;

/**
 *
 * @author gaigeshen
 */
public class DefaultClientCreator<C extends Config> implements ClientCreator<C> {

    private final ServerHostDeterminer<C> determiner;

    public DefaultClientCreator(ServerHostDeterminer<C> determiner) {
        if (Objects.isNull(determiner)) {
            throw new IllegalArgumentException("server host determiner cannot be null");
        }
        this.determiner = determiner;
    }

    public static <C extends Config> DefaultClient<C> create(C config, ServerHostDeterminer<C> determiner) {
        return (DefaultClient<C>) new DefaultClientCreator<>(determiner).create(config);
    }

    @Override
    public Client<C> create(C config) throws ClientCreationException {
        return new DefaultClient<>(config, determiner);
    }
}
