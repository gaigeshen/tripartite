package work.gaigeshen.tripartite.pay.wechat.notify;

import work.gaigeshen.tripartite.core.notify.NotifyContentProcessingException;

/**
 * 空实现
 *
 * @author gaigeshen
 */
public class NoopWechatNotifyContentProcessor extends AbstractWechatNotifyContentProcessor {

    @Override
    protected void handleTransactionSuccessResource(WechatNotifyBody.TransactionSuccessResource transactionSuccessResource) throws NotifyContentProcessingException { }

    @Override
    protected void handleRefundResource(WechatNotifyBody.RefundResource refundResource) throws NotifyContentProcessingException { }
}
