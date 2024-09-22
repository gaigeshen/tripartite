package work.gaigeshen.tripartite.qyweixin.openapi.notify.message.notify;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import work.gaigeshen.tripartite.core.client.Client;
import work.gaigeshen.tripartite.core.client.Clients;
import work.gaigeshen.tripartite.core.notify.DefaultNotifyContent;
import work.gaigeshen.tripartite.qyweixin.openapi.client.QyWeixinProviderClient;
import work.gaigeshen.tripartite.qyweixin.openapi.config.QyWeixinConfig;
import work.gaigeshen.tripartite.qyweixin.openapi.suite.QyWeixinSuiteTicket;

import java.util.Map;
import java.util.Objects;

/**
 * 企业微信应用票据通知推送处理
 *
 * @author gaigeshen
 */
@RequiredArgsConstructor
@Slf4j
public class QyWeixinSuiteTicketMessageNotifyContentProcessor extends QyWeixinMessageNotifyContentProcessor {

    private final Clients<QyWeixinConfig> qyWeixinClients;

    @Override
    protected boolean supportsMessageContent(Map<?, ?> messageContent) {
        return StringUtils.equalsIgnoreCase((CharSequence) messageContent.get("InfoType"), "suite_ticket");
    }

    @Override
    protected void processMessageContent(Map<?, ?> messageContent, DefaultNotifyContent content, ProcessorChain<DefaultNotifyContent> chain) {
        String suiteId = (String) messageContent.get("SuiteId");
        String suiteTicket = (String) messageContent.get("SuiteTicket");
        if (!StringUtils.isAnyBlank(suiteId, suiteTicket)) {
            Client<QyWeixinConfig> qyWeixinClient = qyWeixinClients.getClient(cfg -> Objects.equals(cfg.getSuiteId(), suiteId));
            if (qyWeixinClient instanceof QyWeixinProviderClient) {
                QyWeixinProviderClient providerClient = (QyWeixinProviderClient) qyWeixinClient;
                providerClient.setNewSuiteTicket(QyWeixinSuiteTicket.builder().setSuiteId(suiteId).setTicket(suiteTicket).build());
            }
        }
    }
}
