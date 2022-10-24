package work.gaigeshen.tripartite.core.client;

import work.gaigeshen.tripartite.core.client.parameters.ClientParameters;
import work.gaigeshen.tripartite.core.client.response.ClientResponse;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 表示服务器地址集合
 *
 * @author gaigeshen
 */
public interface ServerHosts extends Iterable<ServerHost> {

    static Collection<ServerHost> create(String... serverHosts) {
        if (Objects.isNull(serverHosts)) {
            return Collections.emptySet();
        }
        return Arrays.stream(serverHosts).map(ServerHost::create).collect(Collectors.toSet());
    }

    @Override
    default Iterator<ServerHost> iterator() {
        return getServerHosts().iterator();
    }

    /**
     * 通过接口客户端参数对象和接口客户端响应类型来确定服务器地址，此方法有默认实现
     *
     * @param parameters 接口客户端参数
     * @param responseClass 接口客户端响应类型
     * @return 服务器地址
     * @throws ServerHostException 无法确定服务器地址
     */
    default ServerHost getServerHost(ClientParameters parameters,
                                     Class<? extends ClientResponse> responseClass) throws ServerHostException {
        Collection<ServerHost> serverHosts = getServerHosts();
        if (serverHosts.isEmpty()) {
            throw new ServerHostMissingException("server hosts is empty");
        }
        return serverHosts.iterator().next();
    }

    /**
     * 返回所有的服务器地址
     *
     * @return 所有的服务器地址
     * @throws ServerHostException 无法返回所有的服务器地址
     */
    Collection<ServerHost> getServerHosts() throws ServerHostException;
}
