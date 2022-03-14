package work.gaigeshen.triparttite.ding.openapi.parameters.message;

import work.gaigeshen.triparttite.ding.openapi.parameters.DingParameters;

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
