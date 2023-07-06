package work.gaigeshen.tripartite.ding.openapi.client;

import work.gaigeshen.tripartite.core.client.Client;
import work.gaigeshen.tripartite.core.client.ClientException;
import work.gaigeshen.tripartite.ding.openapi.config.DingConfig;
import work.gaigeshen.tripartite.ding.openapi.parameters.api.robot.DingRobotGroupMessageQueryParameters;
import work.gaigeshen.tripartite.ding.openapi.parameters.api.robot.DingRobotGroupMessageRecallParameters;
import work.gaigeshen.tripartite.ding.openapi.parameters.api.robot.DingRobotGroupMessageSendParameters;
import work.gaigeshen.tripartite.ding.openapi.parameters.api.task.DingTaskCreateParameters;
import work.gaigeshen.tripartite.ding.openapi.parameters.api.task.DingTaskDeleteParameters;
import work.gaigeshen.tripartite.ding.openapi.parameters.api.task.DingTaskExecutorStatusUpdateParameters;
import work.gaigeshen.tripartite.ding.openapi.parameters.api.task.DingTaskUpdateParameters;
import work.gaigeshen.tripartite.ding.openapi.response.api.robot.DingRobotGroupMessageQueryResponse;
import work.gaigeshen.tripartite.ding.openapi.response.api.robot.DingRobotGroupMessageRecallResponse;
import work.gaigeshen.tripartite.ding.openapi.response.api.robot.DingRobotGroupMessageSendResponse;
import work.gaigeshen.tripartite.ding.openapi.response.api.task.DingTaskCreateResponse;
import work.gaigeshen.tripartite.ding.openapi.response.api.task.DingTaskDeleteResponse;
import work.gaigeshen.tripartite.ding.openapi.response.api.task.DingTaskExecutorStatusUpdateResponse;
import work.gaigeshen.tripartite.ding.openapi.response.api.task.DingTaskUpdateResponse;

/**
 * 钉钉新版接口客户端
 *
 * @author gaigeshen
 */
public interface DingApiClient extends Client<DingConfig> {

    /**
     * 企业机器人向内部群发消息
     *
     * @param parameters 请求参数不能为空
     * @return 响应结果不为空
     * @throws ClientException 执行请求的时候发生异常
     * @see <a href="https://open.dingtalk.com/document/group/the-robot-sends-a-group-message">接口文档</a>
     */
    default DingRobotGroupMessageSendResponse robotSendGroupMessage(DingRobotGroupMessageSendParameters parameters) throws ClientException {
        return execute(parameters, DingRobotGroupMessageSendResponse.class, "/v1.0/robot/groupMessages/send");
    }

    /**
     * 企业机器人撤回内部群消息
     *
     * @param parameters 请求参数不能为空
     * @return 响应结果不为空
     * @throws ClientException 执行请求的时候发生异常
     * @see <a href="https://open.dingtalk.com/document/group/enterprise-chatbot-withdraws-internal-group-messages">接口文档</a>
     */
    default DingRobotGroupMessageRecallResponse robotRecallGroupMessage(DingRobotGroupMessageRecallParameters parameters) throws ClientException {
        return execute(parameters, DingRobotGroupMessageRecallResponse.class, "/v1.0/robot/groupMessages/recall");
    }

    /**
     * 企业机器人查询内部群聊消息
     *
     * @param parameters 请求参数不能为空
     * @return 响应结果不为空
     * @throws ClientException 执行请求的时候发生异常
     * @see <a href="https://open.dingtalk.com/document/orgapp-server/query-the-read-status-of-a-chatbot-group-chat-message">接口文档</a>
     */
    default DingRobotGroupMessageQueryResponse robotQueryGroupMessage(DingRobotGroupMessageQueryParameters parameters) throws ClientException {
        return execute(parameters, DingRobotGroupMessageQueryResponse.class, "/v1.0/robot/groupMessages/query");
    }

    /**
     * 代办任务创建
     *
     * @param parameters 请求参数不能为空
     * @param unionId 任务创建者的用户标识不能为空
     * @return 响应结果不为空
     * @throws ClientException 执行请求的时候发生异常
     * @see <a href="https://open.dingtalk.com/document/orgapp/add-dingtalk-to-do-task">接口文档</a>
     */
    default DingTaskCreateResponse taskCreate(DingTaskCreateParameters parameters, String unionId) throws ClientException {
        return execute(parameters, DingTaskCreateResponse.class, "/v1.0/todo/users/{unionId}/tasks", unionId);
    }

    /**
     * 代办任务更新
     *
     * @param parameters 请求参数不能为空
     * @param unionId 任务创建者的用户标识不能为空
     * @param taskId 任务标识不能为空
     * @return 响应结果不为空
     * @throws ClientException 执行请求的时候发生异常
     * @see <a href="https://open.dingtalk.com/document/orgapp/updates-dingtalk-to-do-tasks">接口文档</a>
     */
    default DingTaskUpdateResponse taskUpdate(DingTaskUpdateParameters parameters, String unionId, String taskId) throws ClientException {
        return executePut(parameters, DingTaskUpdateResponse.class, "/v1.0/todo/users/{unionId}/tasks/{taskId}", unionId, taskId);
    }

    /**
     * 代办任务删除
     *
     * @param parameters 请求参数不能为空
     * @param unionId 任务创建者的用户标识不能为空
     * @param taskId 任务标识不能为空
     * @return 响应结果不为空
     * @throws ClientException 执行请求的时候发生异常
     * @see <a href="https://open.dingtalk.com/document/orgapp/delete-dingtalk-to-do-tasks">接口文档</a>
     */
    default DingTaskDeleteResponse taskDelete(DingTaskDeleteParameters parameters, String unionId, String taskId) throws ClientException {
        return executeDelete(parameters, DingTaskDeleteResponse.class, "/v1.0/todo/users/{unionId}/tasks/{taskId}", unionId, taskId);
    }

    /**
     * 代办任务执行者状态更新
     *
     * @param parameters 请求参数不能为空
     * @param unionId 任务创建者的用户标识不能为空
     * @param taskId 任务标识不能为空
     * @return 响应结果不为空
     * @throws ClientException 执行请求的时候发生异常
     * @see <a href="https://open.dingtalk.com/document/orgapp/update-dingtalk-to-do-status">接口文档</a>
     */
    default DingTaskExecutorStatusUpdateResponse taskUpdateExecutorStatus(DingTaskExecutorStatusUpdateParameters parameters, String unionId, String taskId) throws ClientException {
        return executePut(parameters, DingTaskExecutorStatusUpdateResponse.class, "/v1.0/todo/users/{unionId}/tasks/{taskId}/executorStatus", unionId, taskId);
    }
}
