package work.gaigeshen.tripartite.qyweixin.openapi.client;

import work.gaigeshen.tripartite.core.client.Client;
import work.gaigeshen.tripartite.core.client.ClientException;
import work.gaigeshen.tripartite.qyweixin.openapi.config.QyWeixinConfig;
import work.gaigeshen.tripartite.qyweixin.openapi.parameters.api.message.QyWeixinMessageSendParameters;
import work.gaigeshen.tripartite.qyweixin.openapi.parameters.api.user.QyWeixinUserIdByEmailGetParameters;
import work.gaigeshen.tripartite.qyweixin.openapi.parameters.api.user.QyWeixinUserIdByMobileGetParameters;
import work.gaigeshen.tripartite.qyweixin.openapi.response.api.message.QyWeixinMessageSendResponse;
import work.gaigeshen.tripartite.qyweixin.openapi.response.api.user.QyWeixinUserIdByEmailGetResponse;
import work.gaigeshen.tripartite.qyweixin.openapi.response.api.user.QyWeixinUserIdByMobileGetResponse;

/**
 * 企业微信接口客户端
 *
 * @author gaigeshen
 */
public interface QyWeixinClient extends Client<QyWeixinConfig> {

    /**
     * 通过手机号获取用户标识
     *
     * @param parameters 请求参数不能为空
     * @return 响应结果不为空
     * @throws ClientException 执行请求的时候发生异常
     * @see <a href="https://developer.work.weixin.qq.com/document/path/95402">接口文档</a>
     */
    default QyWeixinUserIdByMobileGetResponse userIdGetByMobile(QyWeixinUserIdByMobileGetParameters parameters) throws ClientException {
        return execute(parameters, QyWeixinUserIdByMobileGetResponse.class, "/user/getuserid?access_token={access_token}", getAccessTokenValue());
    }

    /**
     * 通过邮箱获取用户标识
     *
     * @param parameters 请求参数不能为空
     * @return 响应结果不为空
     * @throws ClientException 执行请求的时候发生异常
     * @see <a href="https://developer.work.weixin.qq.com/document/path/95895">接口文档</a>
     */
    default QyWeixinUserIdByEmailGetResponse userIdGetByMobile(QyWeixinUserIdByEmailGetParameters parameters) throws ClientException {
        return execute(parameters, QyWeixinUserIdByEmailGetResponse.class, "/cgi-bin/user/get_userid_by_email?access_token={access_token}", getAccessTokenValue());
    }

    /**
     * 发送应用消息
     *
     * @param parameters 请求参数不能为空
     * @return 响应结果不为空
     * @throws ClientException 执行请求的时候发生异常
     * @see <a href="https://developer.work.weixin.qq.com/document/path/90236">接口文档</a>
     */
    default QyWeixinMessageSendResponse messageSend(QyWeixinMessageSendParameters parameters) throws ClientException {
        return execute(parameters, QyWeixinMessageSendResponse.class, "/cgi-bin/message/send?access_token={access_token}", getAccessTokenValue());
    }
}
