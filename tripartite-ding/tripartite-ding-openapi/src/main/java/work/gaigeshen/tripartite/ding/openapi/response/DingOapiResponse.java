package work.gaigeshen.tripartite.ding.openapi.response;

import work.gaigeshen.tripartite.core.client.response.ClientResponse;

/**
 * @author gaigeshen
 */
public abstract class DingOapiResponse implements ClientResponse {

    public Integer errcode;

    public String errmsg;
}
