package work.gaigeshen.tripartite.core.parameter.converter;

import work.gaigeshen.tripartite.core.parameter.Parameters;

/**
 * 指定类型的请求参数转换器
 *
 * @author gaigeshen
 */
public class ParametersParametersConverter extends AbstractParametersConverter {

    public static final ParametersParametersConverter INSTANCE = new ParametersParametersConverter();

    @Override
    protected Parameters initParameters(Object parameters) {
        return Parameters.parameters();
    }
}
