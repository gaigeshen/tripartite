package work.gaigeshen.tripartite.ding.openapi.parameters.message;

import work.gaigeshen.tripartite.ding.openapi.parameters.DingParameters;

import java.util.Collection;

/**
 *
 * @author gaigeshen
 */
public class DingRobotMessageParameters implements DingParameters {

  public String robotCode;

  public Collection<String> userIds;

  public String msgKey;

  public String msgParam;
}
