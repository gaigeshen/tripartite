package work.gaigeshen.tripartite.core.notify;

import java.util.Iterator;
import java.util.Objects;

/**
 * 默认的异步通知数据，是异步通知参数同时也是异步通知数据体，二者接口都已实现，如果不想额外实现自定义的异步通知数据，可以考虑直接使用此类
 *
 * @author gaigeshen
 */
public class DefaultNotifyContent extends AbstractNotifyContent implements NotifyParameters, NotifyBody {

  private final NotifyParameters notifyParameters;

  private final NotifyBody notifyBody;

  private DefaultNotifyContent(byte[] body) {
    if (Objects.isNull(body)) {
      throw new IllegalArgumentException("body cannot be null");
    }
    this.notifyParameters = new AbstractNotifyParameters() {};
    this.notifyBody = new AbstractNotifyBody(body) {};
  }

  /**
   * 创建默认的异步通知数据
   *
   * @param body 数据体字节串不能为空
   * @return 默认的异步通知数据
   */
  public static DefaultNotifyContent create(byte[] body) {
    return new DefaultNotifyContent(body);
  }

  /**
   * 创建默认的异步通知数据，数据体字节串为空串
   *
   * @return 默认的异步通知数据
   */
  public static DefaultNotifyContent create() {
    return new DefaultNotifyContent(new byte[0]);
  }

  @Override
  public byte[] getBody() {
    return notifyBody.getBody();
  }

  @Override
  public void put(String name, Object value) {
    notifyParameters.put(name, value);
  }

  @Override
  public void remove(String name) {
    notifyParameters.remove(name);
  }

  @Override
  public Object getValue(String name) {
    return notifyParameters.getValue(name);
  }

  @Override
  public Iterator<Parameter> iterator() {
    return notifyParameters.iterator();
  }

  @Override
  public String toString() {
    return notifyParameters.toString();
  }
}
