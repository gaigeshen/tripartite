package work.gaigeshen.triparttite.ding.openapi.parameters.process;

import work.gaigeshen.triparttite.ding.openapi.parameters.DingParameters;

import java.util.Collection;

/**
 *
 * @author gaigeshen
 */
public class DingProcessInstanceCreateParameters implements DingParameters {

  public String process_code;

  public String originator_user_id;

  public String dept_id;

  public Collection<FormComponentValue> form_component_values;


  public static class FormComponentValue {

    public String name;

    public String value;
  }
}
