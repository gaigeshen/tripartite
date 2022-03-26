package work.gaigeshen.tripartite.ding.openapi;

import work.gaigeshen.tripartite.ding.openapi.config.DingConfig;

/**
 * 钉钉组合客户端，用户最终使用的是此接口
 *
 * @author gaigeshen
 */
public interface DingCompositeClient extends DingRobotClient, DingProcessClient {

  DingConfig getConfig();

}
