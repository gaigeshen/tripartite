package work.gaigeshen.tripartite.ding.openapi.parameters.chat;

import work.gaigeshen.tripartite.core.client.parameters.ClientParameters;
import work.gaigeshen.tripartite.core.parameter.converter.JsonParametersConverter;
import work.gaigeshen.tripartite.core.parameter.converter.Parameters;

import java.util.Collection;

/**
 *
 * @author gaigeshen
 */
@Parameters(
        converter = JsonParametersConverter.class
)
public class DingChatCreateParameters implements ClientParameters {

    public String name;

    public String owner;

    public Collection<String> useridlist;
}
