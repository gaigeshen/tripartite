package work.gaigeshen.tripartite.ding.openapi.client;

import work.gaigeshen.tripartite.ding.openapi.config.DingConfig;

/**
 * 钉钉接口客户端
 *
 * @author gaigeshen
 */
public interface DingClient<DC extends DingConfig> extends DingApiClient<DC>, DingOapiClient<DC> {



}
