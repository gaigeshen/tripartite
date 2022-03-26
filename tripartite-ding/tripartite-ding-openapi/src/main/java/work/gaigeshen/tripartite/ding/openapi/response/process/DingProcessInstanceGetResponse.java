package work.gaigeshen.tripartite.ding.openapi.response.process;

import work.gaigeshen.tripartite.ding.openapi.response.DingResponse;

/**
 *
 * @author gaigeshen
 */
public class DingProcessInstanceGetResponse implements DingResponse {

  public ProcessInstance process_instance;


  public static class ProcessInstance {

    public String title;

    public String originator_userid;

    public String originator_dept_id;

    public String status;

    public String result;

    public String business_id;

    public String main_process_instance_id;
  }
}
