package work.gaigeshen.triparttite.core.parameter.typed;

import java.util.Date;

/**
 * 日期类型的单个请求参数
 *
 * @author gaigeshen
 */
public class DateParameter extends AbstractParameter<Date> {

  public DateParameter(String name, Date value) {
    super(name, value);
  }
}
