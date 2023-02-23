package work.gaigeshen.tripartite.core.parameter.creator;

import work.gaigeshen.tripartite.core.parameter.Parameters;

/**
 * 请求参数创建器
 *
 * @author gaigeshen
 */
public interface ParametersCreator {

    /**
     * 创建请求参数
     *
     * @return 返回的请求参数不可为空
     * @throws ParametersCreationException 创建请求参数的时候发生异常
     */
    Parameters create() throws ParametersCreationException;

}
