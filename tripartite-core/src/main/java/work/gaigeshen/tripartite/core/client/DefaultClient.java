package work.gaigeshen.tripartite.core.client;

import work.gaigeshen.tripartite.core.RestTemplateWebExecutor;
import work.gaigeshen.tripartite.core.WebExecutor;
import work.gaigeshen.tripartite.core.client.config.Config;
import work.gaigeshen.tripartite.core.interceptor.AbstractInterceptor;
import work.gaigeshen.tripartite.core.parameter.converter.ParametersMetadataParametersConverter;

import java.util.Objects;

/**
 *
 * @author gaigeshen
 */
public class DefaultClient<C extends Config> extends AbstractClient<C> {

    public DefaultClient(C config, WebExecutor executor) {
        super(config, executor);
    }

    public static <C extends Config> DefaultClient<C> create(C config, AbstractInterceptor... interceptors) {
        if (Objects.isNull(interceptors)) {
            throw new IllegalArgumentException("interceptors cannot be null");
        }
        RestTemplateWebExecutor executor = RestTemplateWebExecutor.create();
        executor.setInterceptors(interceptors);
        executor.setParametersConverter(new ParametersMetadataParametersConverter(config));
        return new DefaultClient<>(config, executor);
    }
}
