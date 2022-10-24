package work.gaigeshen.tripartite.ding.openapi.response;

import work.gaigeshen.tripartite.core.client.response.ClientResponse;

/**
 * 所有的钉钉旧版接口客户端响应都需要继承此类
 *
 * @author gaigeshen
 */
public abstract class DingOapiResponse implements ClientResponse {

    public Integer errcode;

    public String errmsg;
}
