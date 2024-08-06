package work.gaigeshen.tripartite.core.client;

import lombok.Getter;
import work.gaigeshen.tripartite.core.util.ArgumentValidate;

import java.util.Objects;

/**
 * 表示服务器地址，正确的地址应该至少包含协议、域名等信息
 *
 * @author gaigeshen
 */
@Getter
public class ServerHost {

    private final String serverHost;

    public ServerHost(String serverHost) {
        ArgumentValidate.notNull(serverHost, "serverHost cannot be null");
        this.serverHost = serverHost;
    }

    public static ServerHost create(String serverHost) {
        return new ServerHost(serverHost);
    }

    public String getServerUrl(String path) {
        ArgumentValidate.notNull(path, "path cannot be null");
        return serverHost + path;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ServerHost that = (ServerHost) o;
        return Objects.equals(serverHost, that.serverHost);
    }

    @Override
    public int hashCode() {
        return Objects.hash(serverHost);
    }

    @Override
    public String toString() {
        return serverHost;
    }
}
