package work.gaigeshen.tripartite.ding.openapi.client.client;

import work.gaigeshen.tripartite.core.client.Client;
import work.gaigeshen.tripartite.core.client.ClientException;
import work.gaigeshen.tripartite.ding.openapi.config.DingConfig;
import work.gaigeshen.tripartite.ding.openapi.parameters.chat.DingChatCreateParameters;
import work.gaigeshen.tripartite.ding.openapi.parameters.message.DingMessageAsyncSendParameters;
import work.gaigeshen.tripartite.ding.openapi.parameters.message.DingMessageRecallParameters;
import work.gaigeshen.tripartite.ding.openapi.parameters.process.*;
import work.gaigeshen.tripartite.ding.openapi.parameters.user.DingUserIdGetParameters;
import work.gaigeshen.tripartite.ding.openapi.response.chat.DingChatCreateResponse;
import work.gaigeshen.tripartite.ding.openapi.response.message.DingMessageAsyncSendResponse;
import work.gaigeshen.tripartite.ding.openapi.response.message.DingMessageRecallResponse;
import work.gaigeshen.tripartite.ding.openapi.response.process.*;
import work.gaigeshen.tripartite.ding.openapi.response.user.DingUserIdGetResponse;

/**
 *
 * @author gaigeshen
 */
public interface DingOldEditionClient extends Client<DingConfig> {

    /**
     * 查询在职员工用户标识
     *
     * @param parameters 请求参数不能为空
     * @return 响应结果不为空
     * @throws ClientException 执行请求的时候发生异常
     * @see <a href="https://open.dingtalk.com/document/orgapp-server/obtain-the-userid-of-your-mobile-phone-number">接口文档</a>
     */
    default DingUserIdGetResponse userGetId(DingUserIdGetParameters parameters) throws ClientException {
        return execute(parameters, DingUserIdGetResponse.class, "/topapi/v2/user/getbymobile?access_token={access_token}", getAccessTokenValue());
    }

    /**
     * 创建群会话
     *
     * @param parameters 请求参数不能为空
     * @return 响应结果不为空
     * @throws ClientException 执行请求的时候发生异常
     * @see <a href="https://open.dingtalk.com/document/orgapp-server/create-group-session">接口文档</a>
     */
    default DingChatCreateResponse chatCreate(DingChatCreateParameters parameters) throws ClientException {
        return execute(parameters, DingChatCreateResponse.class, "/chat/create?access_token={access_token}", getAccessTokenValue());
    }

    /**
     * 异步发送工作通知消息
     *
     * @param parameters 请求参数不能为空
     * @return 响应结果不为空
     * @throws ClientException 执行请求的时候发生异常
     * @see <a href="https://developers.dingtalk.com/document/isvapp-server/asynchronous-sending-of-enterprise-session-messages">接口文档</a>
     */
    default DingMessageAsyncSendResponse messageAsyncSend(DingMessageAsyncSendParameters parameters) throws ClientException {
        return execute(parameters, DingMessageAsyncSendResponse.class, "/topapi/message/corpconversation/asyncsend_v2?access_token={access_token}", getAccessTokenValue());
    }

    /**
     * 撤回工作通知消息
     *
     * @param parameters 请求参数不能为空
     * @return 响应结果不为空
     * @throws ClientException 执行请求的时候发生异常
     * @see <a href="https://developers.dingtalk.com/document/orgapp-server/notification-of-work-withdrawal">接口文档</a>
     */
    default DingMessageRecallResponse messageRecall(DingMessageRecallParameters parameters) throws ClientException {
        return execute(parameters, DingMessageRecallResponse.class, "/topapi/message/corpconversation/recall?access_token={access_token}", getAccessTokenValue());
    }

    /**
     * 自有工作流根据模板名称获取模板代号
     *
     * @param parameters 请求参数不能为空
     * @return 响应结果不为空
     * @throws ClientException 执行请求的时候发生异常
     * @see <a href="https://developers.dingtalk.com/document/orgapp-server/obtains-the-template-code-based-on-the-template-name">接口文档</a>
     */
    default DingProcessCodeGetResponse processGetCode(DingProcessCodeGetParameters parameters) throws ClientException {
        return execute(parameters, DingProcessCodeGetResponse.class, "/topapi/process/get_by_name?access_token={access_token}", getAccessTokenValue());
    }

    /**
     * 自有工作流创建或更新模板
     *
     * @param parameters 请求参数不能为空
     * @return 响应结果不为空
     * @throws ClientException 执行请求的时候发生异常
     * @see <a href="https://open.dingtalk.com/document/orgapp-server/save-approval-template">接口文档</a>
     */
    default DingProcessSaveResponse processSave(DingProcessSaveParameters parameters) throws ClientException {
        return execute(parameters, DingProcessSaveResponse.class, "/topapi/process/save?access_token={access_token}", getAccessTokenValue());
    }

    /**
     * 自有工作流删除模板
     *
     * @param parameters 请求参数不能为空
     * @return 响应结果不为空
     * @throws ClientException 执行请求的时候发生异常
     * @see <a href="https://developers.dingtalk.com/document/orgapp-server/delete-a-template">接口文档</a>
     */
    default DingProcessDeleteResponse processDelete(DingProcessDeleteParameters parameters) throws ClientException {
        return execute(parameters, DingProcessDeleteResponse.class, "/topapi/process/delete?access_token={access_token}", getAccessTokenValue());
    }

    /**
     * 自有工作流创建实例
     *
     * @param parameters 请求参数不能为空
     * @return 响应结果不为空
     * @throws ClientException 执行请求的时候发生异常
     * @see <a href="https://developers.dingtalk.com/document/orgapp-server/initiate-an-approval-process-without-a-process">接口文档</a>
     */
    default DingProcessWorkRecordCreateResponse processCreateWorkRecord(DingProcessWorkRecordCreateParameters parameters) throws ClientException {
        return execute(parameters, DingProcessWorkRecordCreateResponse.class, "/topapi/process/workrecord/create?access_token={access_token}", getAccessTokenValue());
    }

    /**
     * 自有工作流更新实例状态
     *
     * @param parameters 请求参数不能为空
     * @return 响应结果不为空
     * @throws ClientException 执行请求的时候发生异常
     * @see <a href="https://developers.dingtalk.com/document/orgapp-server/to-do-instance-status">接口文档</a>
     */
    default DingProcessWorkRecordUpdateResponse processUpdateWorkRecord(DingProcessWorkRecordUpdateParameters parameters) throws ClientException {
        return execute(parameters, DingProcessWorkRecordUpdateResponse.class, "/topapi/process/workrecord/update?access_token={access_token}", getAccessTokenValue());
    }

    /**
     * 自有工作流批量更新实例状态
     *
     * @param parameters 请求参数不能为空
     * @return 响应结果不为空
     * @throws ClientException 执行请求的时候发生异常
     * @see <a href="https://developers.dingtalk.com/document/orgapp-server/update-the-status-of-multiple-instances-at-a-time">接口文档</a>
     */
    default DingProcessWorkRecordBatchUpdateResponse processBatchUpdateWorkRecord(DingProcessWorkRecordBatchUpdateParameters parameters) throws ClientException {
        return execute(parameters, DingProcessWorkRecordBatchUpdateResponse.class, "/topapi/process/workrecord/batchupdate?access_token={access_token}", getAccessTokenValue());
    }

    /**
     * 自有工作流创建待办任务
     *
     * @param parameters 请求参数不能为空
     * @return 响应结果不为空
     * @throws ClientException 执行请求的时候发生异常
     * @see <a href="https://developers.dingtalk.com/document/orgapp-server/create-a-to-do-task">接口文档</a>
     */
    default DingProcessWorkRecordTaskCreateResponse processCreateWorkRecordTask(DingProcessWorkRecordTaskCreateParameters parameters) throws ClientException {
        return execute(parameters, DingProcessWorkRecordTaskCreateResponse.class, "/topapi/process/workrecord/task/create?access_token={access_token}", getAccessTokenValue());
    }

    /**
     * 自有工作流更新待办状态
     *
     * @param parameters 请求参数不能为空
     * @return 响应结果不为空
     * @throws ClientException 执行请求的时候发生异常
     * @see <a href="https://developers.dingtalk.com/document/orgapp-server/update-to-do-task-status">接口文档</a>
     */
    default DingProcessWorkRecordTaskUpdateResponse processUpdateWorkRecordTask(DingProcessWorkRecordTaskUpdateParameters parameters) throws ClientException {
        return execute(parameters, DingProcessWorkRecordTaskUpdateResponse.class, "/topapi/process/workrecord/task/update?access_token={access_token}", getAccessTokenValue());
    }

    /**
     * 自有工作流查询待办列表
     *
     * @param parameters 请求参数不能为空
     * @return 响应结果不为空
     * @throws ClientException 执行请求的时候发生异常
     * @see <a href="https://developers.dingtalk.com/document/orgapp-server/query-a-user-s-to-do-items">接口文档</a>
     */
    default DingProcessWorkRecordTaskQueryResponse processQueryWorkRecordTask(DingProcessWorkRecordTaskQueryParameters parameters) throws ClientException {
        return execute(parameters, DingProcessWorkRecordTaskQueryResponse.class, "/topapi/process/workrecord/task/query?access_token={access_token}", getAccessTokenValue());
    }

    /**
     * 自有工作流批量取消待办
     *
     * @param parameters 请求参数不能为空
     * @return 响应结果不为空
     * @throws ClientException 执行请求的时候发生异常
     * @see <a href="https://developers.dingtalk.com/document/orgapp-server/cancel-multiple-tasks">接口文档</a>
     */
    default DingProcessWorkRecordTaskCancelResponse processCancelWorkRecordTask(DingProcessWorkRecordTaskCancelParameters parameters) throws ClientException {
        return execute(parameters, DingProcessWorkRecordTaskCancelResponse.class, "/topapi/process/workrecord/taskgroup/cancel?access_token={access_token}", getAccessTokenValue());
    }
}
