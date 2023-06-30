package work.gaigeshen.tripartite.ding.openapi.parameters.api.task;

import work.gaigeshen.tripartite.core.parameter.converter.JsonParametersConverter;
import work.gaigeshen.tripartite.core.parameter.converter.Parameters;
import work.gaigeshen.tripartite.ding.openapi.parameters.DingApiParameters;

import java.util.Collection;

/**
 * @author gaigeshen
 */
@Parameters(
        converter = JsonParametersConverter.class
)
public class DingTaskUpdateParameters extends DingApiParameters {

    public String subject;

    public String description;

    public Long dueTime;

    public Boolean done;

    public Collection<String> executorIds;

    public Collection<String> participantIds;
}
