package work.gaigeshen.tripartite.qyweixin.openapi.response.auth;

import work.gaigeshen.tripartite.qyweixin.openapi.response.QyWeixinResponse;

/**
 *
 * @author gaigeshen
 */
public class QyWeixinAuthInfoGetResponse extends QyWeixinResponse {

    public DealerCorpInfo dealer_corp_info;

    public static class DealerCorpInfo {

        public String corpid;

        public String corp_name;
    }
}
