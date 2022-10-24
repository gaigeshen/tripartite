package work.gaigeshen.tripartite.ding.openapi.parameters.oapi.chat;

import work.gaigeshen.tripartite.core.parameter.converter.JsonParametersConverter;
import work.gaigeshen.tripartite.core.parameter.converter.Parameters;
import work.gaigeshen.tripartite.ding.openapi.parameters.DingOapiParameters;

import java.util.Collection;

/**
 *
 * @author gaigeshen
 */
@Parameters(
        converter = JsonParametersConverter.class
)
public class DingChatCreateParameters extends DingOapiParameters {

    public String name;

    public String owner;

    public Collection<String> useridlist;
}
