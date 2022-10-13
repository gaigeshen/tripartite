package work.gaigeshen.tripartite.core.client;

import work.gaigeshen.tripartite.core.client.config.Config;
import work.gaigeshen.tripartite.core.client.config.ConfigException;
import work.gaigeshen.tripartite.core.client.parameters.ClientParameters;
import work.gaigeshen.tripartite.core.client.response.ClientResponse;

/**
 * 客户端
 *
 * @author gaigeshen
 */
public interface Client<C extends Config> {

    /**
     * 返回此客户端对应的配置信息
     *
     * @return 此客户端对应的配置信息
     * @throws ConfigException 配置异常
     */
    C getConfig() throws ConfigException;

    /**
     * 调用此方法将会让此客户端执行请求操作
     *
     * @param parameters 客户端参数
     * @param responseClass 客户端响应类型
     * @param uri 请求的地址字符串
     * @return 客户端响应
     * @param <R> 客户端响应类型
     * @param <P> 客户端参数类型
     * @throws ClientException 执行请求的时候发生异常
     */
    <R extends ClientResponse, P extends ClientParameters> R execute(
            P parameters, Class<R> responseClass, String uri
    ) throws ClientException;
}
