package work.gaigeshen.tripartite.ding.openapi;

import work.gaigeshen.tripartite.core.RestTemplateWebExecutor;
import work.gaigeshen.tripartite.core.WebExecutor;
import work.gaigeshen.tripartite.core.interceptor.AbstractInterceptor;
import work.gaigeshen.tripartite.core.parameter.converter.ParametersMetadataParametersConverter;
import work.gaigeshen.tripartite.ding.openapi.config.DingConfig;

import java.util.Objects;

/**
 *
 * @author gaigeshen
 */
public class DefaultDingClient extends DingAbstractClient {

    public DefaultDingClient(DingConfig config, WebExecutor executor) {
        super(config, executor);
    }

    public static DefaultDingClient create(DingConfig config, AbstractInterceptor... interceptors) {
        if (Objects.isNull(interceptors)) {
            throw new IllegalArgumentException("interceptors cannot be null");
        }
        RestTemplateWebExecutor executor = RestTemplateWebExecutor.create();
        executor.setInterceptors(interceptors);
        executor.setParametersConverter(new ParametersMetadataParametersConverter(config));
        return new DefaultDingClient(config, executor);
    }
}
