package work.gaigeshen.tripartite.core.client;

import work.gaigeshen.tripartite.core.client.accesstoken.AccessToken;
import work.gaigeshen.tripartite.core.client.accesstoken.AccessTokenManager;
import work.gaigeshen.tripartite.core.client.config.Config;
import work.gaigeshen.tripartite.core.client.config.ConfigException;
import work.gaigeshen.tripartite.core.client.parameters.ClientParameters;
import work.gaigeshen.tripartite.core.client.response.ClientResponse;
import work.gaigeshen.tripartite.core.ratelimiter.RateLimiterService;

import java.util.Objects;

/**
 * 接口客户端
 *
 * @author gaigeshen
 */
public interface Client<C extends Config> extends ServerHosts {

    /**
     * 返回此接口客户端的配置信息
     *
     * @return 此接口客户端的配置信息
     * @throws ConfigException 发生异常
     */
    C getConfig() throws ConfigException;

    /**
     * 执行接口调用操作
     *
     * @param parameters 需要传递的参数对象
     * @param responseClass 响应对象的类型
     * @param path 接口的路径
     * @param uriVariables 接口路径中的变量值
     * @return 返回响应对象
     * @param <R> 表示响应对象的类型
     * @param <P> 表示需要传递的参数的类型
     * @throws ClientException 执行接口调用操作的时候发生异常
     */
    <R extends ClientResponse, P extends ClientParameters> R executeDelete(
            P parameters, Class<R> responseClass, String path, Object... uriVariables
    ) throws ClientException;

    /**
     * 执行接口调用操作
     *
     * @param parameters 需要传递的参数对象
     * @param responseClass 响应对象的类型
     * @param path 接口的路径
     * @param uriVariables 接口路径中的变量值
     * @return 返回响应对象
     * @param <R> 表示响应对象的类型
     * @param <P> 表示需要传递的参数的类型
     * @throws ClientException 执行接口调用操作的时候发生异常
     */
    <R extends ClientResponse, P extends ClientParameters> R executePut(
            P parameters, Class<R> responseClass, String path, Object... uriVariables
    ) throws ClientException;

    /**
     * 执行接口调用操作
     *
     * @param parameters 需要传递的参数对象
     * @param responseClass 响应对象的类型
     * @param path 接口的路径
     * @param uriVariables 接口路径中的变量值
     * @return 返回响应对象
     * @param <R> 表示响应对象的类型
     * @param <P> 表示需要传递的参数的类型
     * @throws ClientException 执行接口调用操作的时候发生异常
     */
    <R extends ClientResponse, P extends ClientParameters> R execute(
            P parameters, Class<R> responseClass, String path, Object... uriVariables
    ) throws ClientException;

    /**
     * 执行接口调用操作
     *
     * @param responseClass 响应对象的类型
     * @param path 接口的路径
     * @param uriVariables 接口路径中的变量值
     * @return 返回响应对象
     * @param <R> 表示响应对象的类型
     * @throws ClientException 执行接口调用操作的时候发生异常
     */
    <R extends ClientResponse> R execute(
            Class<R> responseClass, String path, Object... uriVariables
    ) throws ClientException;

    /**
     * 此接口客户端被创建之后可以调用此方法来做些初始化的工作
     *
     * @throws ClientException 在初始化的时候发生异常
     */
    default void init() throws ClientException { }

    /**
     * 返回访问令牌的值
     *
     * @return 访问令牌的值，默认如果没有访问令牌管理器，则返回空对象
     */
    default String getAccessTokenValue() {
        AccessToken accessToken = getAccessToken();
        return Objects.isNull(accessToken) ? "" : accessToken.getAccessToken();
    }

    /**
     * 返回访问令牌对象
     *
     * @return 访问令牌对象，默认如果没有访问令牌管理器，则返回空对象
     */
    default AccessToken getAccessToken() {
        AccessTokenManager<C> accessTokenManager = getAccessTokenManager();
        if (Objects.isNull(accessTokenManager)) {
            return null;
        }
        return accessTokenManager.findAccessToken(getConfig());
    }

    /**
     * 返回此接口客户端的访问令牌管理器
     *
     * @return 此接口客户端的访问令牌管理器可能为空，表示不需要访问令牌
     */
    default AccessTokenManager<C> getAccessTokenManager() {
        return null;
    }

    /**
     * 返回此接口客户端的限流服务对象，执行接口调用操作的时候应该先通过限流服务检查
     *
     * @return 此接口客户端的限流服务对象可能为空，表示不限流
     */
    default RateLimiterService getRateLimiterService() {
        return null;
    }
}
