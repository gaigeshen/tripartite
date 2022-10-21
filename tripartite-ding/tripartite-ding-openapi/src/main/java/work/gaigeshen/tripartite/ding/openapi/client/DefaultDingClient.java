package work.gaigeshen.tripartite.ding.openapi.client;

import work.gaigeshen.tripartite.core.client.*;
import work.gaigeshen.tripartite.core.client.accesstoken.AccessTokenManager;
import work.gaigeshen.tripartite.core.client.accesstoken.AccessTokenManagerException;
import work.gaigeshen.tripartite.core.client.config.ConfigException;
import work.gaigeshen.tripartite.core.client.parameters.ClientParameters;
import work.gaigeshen.tripartite.core.client.response.ClientResponse;
import work.gaigeshen.tripartite.core.header.Headers;
import work.gaigeshen.tripartite.core.interceptor.AbstractInterceptor;
import work.gaigeshen.tripartite.core.interceptor.InterceptingException;
import work.gaigeshen.tripartite.ding.openapi.client.accesstoken.DingAccessTokenHelper;
import work.gaigeshen.tripartite.ding.openapi.config.DingConfig;
import work.gaigeshen.tripartite.ding.openapi.parameters.DingApiParameters;
import work.gaigeshen.tripartite.ding.openapi.parameters.DingOapiParameters;
import work.gaigeshen.tripartite.ding.openapi.response.DingApiResponse;
import work.gaigeshen.tripartite.ding.openapi.response.DingOapiResponse;

import java.util.*;

/**
 *
 *
 * @author gaigeshen
 */
public class DefaultDingClient extends AbstractWebExecutorClient<DingConfig> implements DingClient {

    private final DingConfig config;

    private final AccessTokenManager<DingConfig> accessTokenManager;

    protected DefaultDingClient(DingConfig config, AccessTokenManager<DingConfig> accessTokenManager) {
        if (Objects.isNull(config)) {
            throw new IllegalArgumentException("config cannot be null");
        }
        if (Objects.isNull(accessTokenManager)) {
            throw new IllegalArgumentException("access token manager cannot be null");
        }
        this.config = config;
        this.accessTokenManager = accessTokenManager;
    }

    public static DefaultDingClient create(DingConfig config, AccessTokenManager<DingConfig> accessTokenManager) {
        return new DefaultDingClient(config, accessTokenManager);
    }

    @Override
    public synchronized void init() throws ClientException {
        super.init();
        try {
            accessTokenManager.addNewAccessToken(config, DingAccessTokenHelper.getNewAccessToken(this));
        } catch (AccessTokenManagerException e) {
            throw new ClientException(e.getMessage(), e);
        }
    }

    @Override
    protected List<AbstractInterceptor> createInterceptors() {
        return Collections.singletonList(new AbstractInterceptor() {
            @Override
            protected void updateRequest(Request request) throws InterceptingException {
                String accessTokenValue = getAccessTokenValue();
                if (Objects.isNull(accessTokenValue)) {
                    return;
                }
                Headers headers = request.headers();
                headers.putValue("x-acs-dingtalk-access-token", accessTokenValue);
            }
            @Override
            protected void validateResponse(Request request, Response response) throws InterceptingException { }
        });
    }

    @Override
    public DingConfig getConfig() throws ConfigException {
        return config;
    }

    @Override
    public AccessTokenManager<DingConfig> getAccessTokenManager() {
        return accessTokenManager;
    }

    @Override
    protected <R extends ClientResponse> R validateResponse(R response) throws ClientException {
        R preValidated = super.validateResponse(response);
        if (preValidated instanceof DingOapiResponse) {
            DingOapiResponse dingOapiResponse = (DingOapiResponse) preValidated;
            if (!Objects.equals(0, dingOapiResponse.errcode)) {
                throw new ClientException("[ " + dingOapiResponse.errcode + " ] - [ " + dingOapiResponse.errmsg + " ]");
            }
        }
        return preValidated;
    }

    @Override
    public ServerHost getServerHost(ClientParameters parameters, Class<? extends ClientResponse> responseClass) throws ServerHostException {
        if (parameters instanceof DingApiParameters || DingApiResponse.class.isAssignableFrom(responseClass)) {
            return ServerHost.create(config.getApiServerHost());
        }
        else if (parameters instanceof DingOapiParameters || DingOapiResponse.class.isAssignableFrom(responseClass)) {
            return ServerHost.create(config.getOapiServerHost());
        }
        throw new ServerHostException("could not determine server host: " + parameters + ", " + responseClass);
    }

    @Override
    public Collection<ServerHost> getServerHosts() throws ServerHostException {
        return ServerHosts.create(config.getApiServerHost(), config.getOapiServerHost());
    }
}
