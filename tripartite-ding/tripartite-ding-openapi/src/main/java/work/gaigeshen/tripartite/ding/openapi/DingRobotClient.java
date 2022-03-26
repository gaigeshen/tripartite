package work.gaigeshen.tripartite.ding.openapi;

import work.gaigeshen.tripartite.ding.openapi.parameters.message.DingRobotMessageParameters;
import work.gaigeshen.tripartite.ding.openapi.response.message.DingRobotMessageResponse;

/**
 * 钉钉机器人客户端
 *
 * @author gaigeshen
 */
public interface DingRobotClient {

  /**
   * 批量发送单聊消息
   *
   * @param parameters 批量发送单聊消息参数不能为空
   * @return 批量发送单聊消息结果
   * @throws DingClientException 客户端异常
   */
  DingRobotMessageResponse batchSendMessage(DingRobotMessageParameters parameters) throws DingClientException;

}
