package work.gaigeshen.tripartite.core.notify;

import work.gaigeshen.tripartite.core.util.ArgumentValidate;

/**
 * 抽象的异步通知数据体
 *
 * @author gaigeshen
 */
public abstract class AbstractNotifyBody extends AbstractNotifyContent implements NotifyBody {

    private final byte[] body;

    protected AbstractNotifyBody(byte[] body) {
        ArgumentValidate.notNull(body, "body cannot be null");
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
