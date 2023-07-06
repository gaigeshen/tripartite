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
public class DingTaskCreateParameters extends DingApiParameters {

    public String sourceId;

    public String subject;

    public String description;

    public Long dueTime;

    public Long priority;

    public Collection<String> executorIds;

    public Collection<String> participantIds;

    public DetailUrl detailUrl;

    public static class DetailUrl {

        public String appUrl;

        public String pcUrl;
    }
}
