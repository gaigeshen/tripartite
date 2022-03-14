package work.gaigeshen.triparttite.ding.openapi;

import work.gaigeshen.triparttite.ding.openapi.parameters.process.DingProcessInstanceCreateParameters;
import work.gaigeshen.triparttite.ding.openapi.parameters.process.DingProcessInstanceGetParameters;
import work.gaigeshen.triparttite.ding.openapi.parameters.process.DingProcessInstanceTerminateParameters;
import work.gaigeshen.triparttite.ding.openapi.response.process.DingProcessInstanceCreateResponse;
import work.gaigeshen.triparttite.ding.openapi.response.process.DingProcessInstanceGetResponse;
import work.gaigeshen.triparttite.ding.openapi.response.process.DingProcessInstanceTerminateResponse;

/**
 * 钉钉工作流客户端
 *
 * @author gaigeshen
 */
public interface DingProcessClient {

  /**
   * 发起审批实例
   *
   * @param parameters 发起审批实例参数不能为空
   * @return 审批实例发起结果
   * @throws DingClientException 客户端异常
   */
  DingProcessInstanceCreateResponse createProcessInstance(DingProcessInstanceCreateParameters parameters) throws DingClientException;

  /**
   * 获取审批实例
   *
   * @param parameters 获取审批实例参数不能为空
   * @return 审批实例获取结果
   * @throws DingClientException 客户端异常
   */
  DingProcessInstanceGetResponse getProcessInstance(DingProcessInstanceGetParameters parameters) throws DingClientException;

  /**
   * 撤销审批实例
   *
   * @param parameters 撤销审批实例参数不能为空
   * @return 审批实例撤销结果
   * @throws DingClientException 客户端异常
   */
  DingProcessInstanceTerminateResponse terminateProcessInstance(DingProcessInstanceTerminateParameters parameters) throws DingClientException;

}
