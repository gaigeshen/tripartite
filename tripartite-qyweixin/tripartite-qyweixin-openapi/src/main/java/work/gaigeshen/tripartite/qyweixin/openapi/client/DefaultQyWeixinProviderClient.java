package work.gaigeshen.tripartite.qyweixin.openapi.client;

import work.gaigeshen.tripartite.core.client.ClientException;
import work.gaigeshen.tripartite.core.client.accesstoken.AccessToken;
import work.gaigeshen.tripartite.core.client.accesstoken.AccessTokenHelper;
import work.gaigeshen.tripartite.core.client.accesstoken.AccessTokenManager;
import work.gaigeshen.tripartite.qyweixin.openapi.config.QyWeixinConfig;
import work.gaigeshen.tripartite.qyweixin.openapi.parameters.accesstoken.QyWeixinProviderAccessTokenParameters;
import work.gaigeshen.tripartite.qyweixin.openapi.parameters.message.QyWeixinMessageSendParameters;
import work.gaigeshen.tripartite.qyweixin.openapi.parameters.user.QyWeixinUserIdByEmailGetParameters;
import work.gaigeshen.tripartite.qyweixin.openapi.parameters.user.QyWeixinUserIdByMobileGetParameters;
import work.gaigeshen.tripartite.qyweixin.openapi.response.accesstoken.QyWeixinProviderAccessTokenResponse;
import work.gaigeshen.tripartite.qyweixin.openapi.response.message.QyWeixinMessageSendResponse;
import work.gaigeshen.tripartite.qyweixin.openapi.response.user.QyWeixinUserIdByEmailGetResponse;
import work.gaigeshen.tripartite.qyweixin.openapi.response.user.QyWeixinUserIdByMobileGetResponse;

import java.util.Objects;

/**
 * 企业微信服务商接口客户端的实现
 *
 * @author gaigeshen
 */
public class DefaultQyWeixinProviderClient extends DefaultQyWeixinClient implements QyWeixinProviderClient {

    protected DefaultQyWeixinProviderClient(QyWeixinConfig config, AccessTokenManager<QyWeixinConfig> accessTokenManager) {
        super(config, accessTokenManager);
    }

    /**
     * 创建默认的企业微信接口客户端，需要企业微信配置信息和访问令牌管理器
     *
     * @param config 企业微信配置信息
     * @param accessTokenManager 访问令牌管理器
     * @return 企业微信接口客户端
     */
    static DefaultQyWeixinProviderClient create(QyWeixinConfig config, AccessTokenManager<QyWeixinConfig> accessTokenManager) {
        return new DefaultQyWeixinProviderClient(config, accessTokenManager);
    }

    /**
     * 服务商获取访问令牌的方式不同
     *
     * @return 获取到的访问令牌
     * @throws ClientException 无法获取访问令牌
     * @see <a href="https://developer.work.weixin.qq.com/document/path/96237">接口文档</a>
     */
    public AccessToken getNewAccessToken() throws ClientException {
        String path = "/cgi-bin/service/get_provider_token";
        QyWeixinConfig config = getConfig();
        QyWeixinProviderAccessTokenParameters parameters = new QyWeixinProviderAccessTokenParameters();
        parameters.corpId = config.getCorpId();
        parameters.providerSecret = config.getProviderSecret();
        QyWeixinProviderAccessTokenResponse response = execute(parameters, QyWeixinProviderAccessTokenResponse.class, path);
        String accessToken = response.provider_access_token;
        Long expireIn = response.expires_in;
        if (Objects.isNull(accessToken) || Objects.isNull(expireIn)) {
            throw new ClientException("acquired access token is invalid: " + config);
        }
        return AccessTokenHelper.createAccessToken(config, accessToken, expireIn);
    }

    @Override
    public QyWeixinUserIdByMobileGetResponse userIdGetByMobile(QyWeixinUserIdByMobileGetParameters parameters) throws ClientException {
        throw new ClientException("could not call this method by suite client");
    }

    @Override
    public QyWeixinUserIdByEmailGetResponse userIdGetByMobile(QyWeixinUserIdByEmailGetParameters parameters) throws ClientException {
        throw new ClientException("could not call this method by suite client");
    }

    @Override
    public QyWeixinMessageSendResponse messageSend(QyWeixinMessageSendParameters parameters) throws ClientException {
        throw new ClientException("could not call this method by suite client");
    }
}
