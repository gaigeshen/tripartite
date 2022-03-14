package work.gaigeshen.triparttite.core.notify;

import java.util.Objects;

/**
 * 抽象的异步通知数据体
 *
 * @author gaigeshen
 */
public abstract class AbstractNotifyBody extends AbstractNotifyContent implements NotifyBody {

  private final byte[] body;

  protected AbstractNotifyBody(byte[] body) {
    if (Objects.isNull(body)) {
      throw new IllegalArgumentException("body cannot be null");
    }
    this.body = body;
  }

  @Override
  public final byte[] getBody() {
    return body;
  }

  @Override
  public String toString() {
    return super.toString() + ", body: " + new String(body);
  }
}
