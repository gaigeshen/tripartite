package work.gaigeshen.tripartite.ding.openapi.client;

import work.gaigeshen.tripartite.core.client.Client;
import work.gaigeshen.tripartite.core.client.ClientException;
import work.gaigeshen.tripartite.ding.openapi.config.DingConfig;
import work.gaigeshen.tripartite.ding.openapi.parameters.oapi.chat.DingChatCreateParameters;
import work.gaigeshen.tripartite.ding.openapi.parameters.oapi.department.DingDepartmentListParameters;
import work.gaigeshen.tripartite.ding.openapi.parameters.oapi.department.DingDepartmentParentByUserListParameters;
import work.gaigeshen.tripartite.ding.openapi.parameters.oapi.message.DingMessageAsyncSendParameters;
import work.gaigeshen.tripartite.ding.openapi.parameters.oapi.message.DingMessageRecallParameters;
import work.gaigeshen.tripartite.ding.openapi.parameters.oapi.process.*;
import work.gaigeshen.tripartite.ding.openapi.parameters.oapi.user.DingUserByCodeGetParameters;
import work.gaigeshen.tripartite.ding.openapi.parameters.oapi.user.DingUserByDepartmentListParameters;
import work.gaigeshen.tripartite.ding.openapi.parameters.oapi.user.DingUserByMobileGetParameters;
import work.gaigeshen.tripartite.ding.openapi.response.oapi.chat.DingChatCreateResponse;
import work.gaigeshen.tripartite.ding.openapi.response.oapi.department.DingDepartmentListResponse;
import work.gaigeshen.tripartite.ding.openapi.response.oapi.department.DingDepartmentParentByUserListResponse;
import work.gaigeshen.tripartite.ding.openapi.response.oapi.message.DingMessageAsyncSendResponse;
import work.gaigeshen.tripartite.ding.openapi.response.oapi.message.DingMessageRecallResponse;
import work.gaigeshen.tripartite.ding.openapi.response.oapi.process.*;
import work.gaigeshen.tripartite.ding.openapi.response.oapi.user.DingUserByCodeGetResponse;
import work.gaigeshen.tripartite.ding.openapi.response.oapi.user.DingUserByDepartmentListResponse;
import work.gaigeshen.tripartite.ding.openapi.response.oapi.user.DingUserByMobileGetResponse;

/**
 * 钉钉旧版接口客户端
 *
 * @author gaigeshen
 */
public interface DingOapiClient extends Client<DingConfig> {

    /**
     * 根据手机号查询用户
     *
     * @param parameters 请求参数不能为空
     * @return 响应结果不为空
     * @throws ClientException 执行请求的时候发生异常
     * @see <a href="https://open.dingtalk.com/document/orgapp-server/obtain-the-userid-of-your-mobile-phone-number">接口文档</a>
     */
    default DingUserByMobileGetResponse userGetByMobile(DingUserByMobileGetParameters parameters) throws ClientException {
        return execute(parameters, DingUserByMobileGetResponse.class, "/topapi/v2/user/getbymobile?access_token={access_token}", getAccessTokenValue());
    }

    /**
     * 通过免登码获取用户信息
     *
     * @param parameters 请求参数不能为空
     * @return 响应结果不为空
     * @throws ClientException 执行请求的时候发生异常
     * @see <a href="https://open.dingtalk.com/document/orgapp-server/obtain-the-userid-of-a-user-by-using-the-log-free">接口文档</a>
     */
    default DingUserByCodeGetResponse userGetByCode(DingUserByCodeGetParameters parameters) throws ClientException {
        return execute(parameters, DingUserByCodeGetResponse.class, "/topapi/v2/user/getuserinfo?access_token={access_token}", getAccessTokenValue());
    }

    /**
     * 获取部门用户信息
     *
     * @param parameters 请求参数不能为空
     * @return 响应结果不为空
     * @throws ClientException 执行请求的时候发生异常
     * @see <a href="https://open.dingtalk.com/document/orgapp-server/queries-the-complete-information-of-a-department-user">接口文档</a>
     */
    default DingUserByDepartmentListResponse userListByDepartment(DingUserByDepartmentListParameters parameters) throws ClientException {
        return execute(parameters, DingUserByDepartmentListResponse.class, "/topapi/v2/user/list?access_token={access_token}", getAccessTokenValue());
    }

    /**
     * 获取部门列表
     *
     * @param parameters 请求参数不能为空
     * @return 响应结果不为空
     * @throws ClientException 执行请求的时候发生异常
     * @see <a href="https://open.dingtalk.com/document/orgapp-server/obtain-the-department-list-v2">接口文档</a>
     */
    default DingDepartmentListResponse departmentList(DingDepartmentListParameters parameters) throws ClientException {
        return execute(parameters, DingDepartmentListResponse.class, "/topapi/v2/department/listsub?access_token={access_token}", getAccessTokenValue());
    }

    /**
     * 获取指定用户的所有父部门列表
     *
     * @param parameters 请求参数不能为空
     * @return 响应结果不为空
     * @throws ClientException 执行请求的时候发生异常
     * @see <a href="https://open.dingtalk.com/document/orgapp-server/queries-the-list-of-all-parent-departments-of-a-user">接口文档</a>
     */
    default DingDepartmentParentByUserListResponse departmentParentListByUser(DingDepartmentParentByUserListParameters parameters) throws ClientException {
        return execute(parameters, DingDepartmentParentByUserListResponse.class, "/topapi/v2/department/listparentbyuser?access_token={access_token}", getAccessTokenValue());
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
