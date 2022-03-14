package work.gaigeshen.triparttite.ding.openapi.parameters.process;

import work.gaigeshen.triparttite.ding.openapi.parameters.DingParameters;

/**
 *
 * @author gaigeshen
 */
public class DingProcessInstanceTerminateParameters implements DingParameters {

  public Request request;


  public static class Request {

    public String process_instance_id;

    public Boolean is_system;

    public String operating_userid;

    public String remark;
  }
}
