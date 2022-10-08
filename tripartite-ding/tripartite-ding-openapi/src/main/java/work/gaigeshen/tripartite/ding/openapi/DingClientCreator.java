package work.gaigeshen.tripartite.ding.openapi;

import work.gaigeshen.tripartite.ding.openapi.config.DingConfig;

/**
 * @author gaigeshen
 */
public interface DingClientCreator {

    DingClient create(DingConfig config) throws DingClientCreationException;

}
