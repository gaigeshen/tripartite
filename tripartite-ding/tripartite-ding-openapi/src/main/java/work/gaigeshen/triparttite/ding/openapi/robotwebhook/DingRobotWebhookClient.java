package work.gaigeshen.triparttite.ding.openapi.robotwebhook;

import work.gaigeshen.triparttite.core.RestTemplateWebExecutor;
import work.gaigeshen.triparttite.core.WebException;
import work.gaigeshen.triparttite.core.WebExecutor;
import work.gaigeshen.triparttite.core.parameter.converter.ParametersConverter;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * 自定义机器人客户端
 *
 * @author gaigeshen
 */
public class DingRobotWebhookClient {

  private final Set<DingRobotWebhook> robotWebhooks;

  private final WebExecutor webExecutor;

  public DingRobotWebhookClient(Set<DingRobotWebhook> robotWebhooks) {
    if (Objects.isNull(robotWebhooks)) {
      throw new IllegalArgumentException("robot webhooks cannot be null");
    }
    this.robotWebhooks = new HashSet<>(robotWebhooks);

    this.webExecutor = RestTemplateWebExecutor.create();
  }

  public DingRobotWebhook getRobotWebhook(String webhookName) {
    for (DingRobotWebhook robotWebhook : robotWebhooks) {
      if (Objects.equals(robotWebhook.getName(), webhookName)) {
        return robotWebhook;
      }
    }
    return null;
  }

  /**
   * 调用此方法将执行自定义机器人的接口调用，参数和返回的结果都是默认实现的
   *
   * @param webhookName 自定义机器人的可读名称不能为空
   * @param parameters 参数不可为空
   * @return 自定义机器人接口调用结果不会为空，除非发生异常
   * @throws WebException 执行接口调用时发生异常
   */
  public DefaultDingRobotWebhookResponse execute(String webhookName, DefaultDingRobotWebhookParameters parameters) throws WebException {
    if (Objects.isNull(webhookName) || Objects.isNull(parameters)) {
      throw new IllegalArgumentException("webhook name and parameters cannot be null");
    }
    return execute(webhookName, parameters, DefaultDingRobotWebhookResponse.class);
  }

  /**
   * 调用此方法将执行自定义机器人的接口调用
   *
   * @param webhookName 自定义机器人的可读名称不能为空
   * @param parameters 参数不可为空
   * @param responseClass 返回的类型不可为空
   * @param <R> 返回的类型
   * @return 自定义机器人接口调用结果不会为空，除非发生异常
   * @throws WebException 执行接口调用时发生异常
   */
  public <R extends DingRobotWebhookResponse> R execute(String webhookName, DingRobotWebhookParameters parameters, Class<R> responseClass) throws WebException {
    if (Objects.isNull(webhookName) || Objects.isNull(parameters)) {
      throw new IllegalArgumentException("webhook name and parameters cannot be null");
    }
    if (Objects.isNull(responseClass)) {
      throw new IllegalArgumentException("response class cannot be null");
    }
    DingRobotWebhook robotWebhook = getRobotWebhook(webhookName);
    if (Objects.isNull(robotWebhook)) {
      throw new IllegalArgumentException("could not find robot webhook: " + webhookName);
    }
    return webExecutor.execute(robotWebhook.getWebhook(), ParametersConverter.convertJson(parameters), responseClass);
  }
}
