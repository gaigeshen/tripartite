package work.gaigeshen.tripartite.qyweixin.openapi.client;

import work.gaigeshen.tripartite.core.client.ClientException;
import work.gaigeshen.tripartite.qyweixin.openapi.parameters.message.QyWeixinMessageSendParameters;
import work.gaigeshen.tripartite.qyweixin.openapi.parameters.user.QyWeixinUserIdByEmailGetParameters;
import work.gaigeshen.tripartite.qyweixin.openapi.parameters.user.QyWeixinUserIdByMobileGetParameters;
import work.gaigeshen.tripartite.qyweixin.openapi.response.message.QyWeixinMessageSendResponse;
import work.gaigeshen.tripartite.qyweixin.openapi.response.user.QyWeixinUserIdByEmailGetResponse;
import work.gaigeshen.tripartite.qyweixin.openapi.response.user.QyWeixinUserIdByMobileGetResponse;

/**
 * 企业微信接口客户端
 *
 * @author gaigeshen
 */
public interface QyWeixinClient extends BaseQyWeixinClient {

    /**
     * 通过手机号获取用户标识
     *
     * @param parameters 请求参数不能为空
     * @return 响应结果不为空
     * @throws ClientException 执行请求的时候发生异常
     * @see <a href="https://developer.work.weixin.qq.com/document/path/95402">接口文档</a>
     */
    default QyWeixinUserIdByMobileGetResponse userIdGetByMobile(QyWeixinUserIdByMobileGetParameters parameters) throws ClientException {
        return execute(parameters, QyWeixinUserIdByMobileGetResponse.class, "/cgi-bin/user/getuserid?access_token={access_token}", getAccessTokenValue());
    }

    /**
     * 通过邮箱获取用户标识
     *
     * @param parameters 请求参数不能为空
     * @return 响应结果不为空
     * @throws ClientException 执行请求的时候发生异常
     * @see <a href="https://developer.work.weixin.qq.com/document/path/95895">接口文档</a>
     */
    default QyWeixinUserIdByEmailGetResponse userIdGetByEmail(QyWeixinUserIdByEmailGetParameters parameters) throws ClientException {
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
