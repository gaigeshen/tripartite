package work.gaigeshen.tripartite.core.parameter.converter;

import work.gaigeshen.tripartite.core.parameter.Parameters;

/**
 * 这个请求参数对象自定义器不做任何事情，也是默认配置的
 *
 * @author gaigeshen
 */
public class DefaultParametersCustomizer implements ParametersCustomizer {

    public static final DefaultParametersCustomizer INSTANCE = new DefaultParametersCustomizer();

    @Override
    public void beforeConvert(Object rawParameters, Object config) throws ParametersCustomizingException {

    }

    @Override
    public void customize(Parameters parameters, Object rawParameters, Object config) throws ParametersCustomizingException {

    }

    @Override
    public boolean supports(Object config) {
        return true;
    }
}
