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
public class DingTaskExecutorStatusUpdateParameters extends DingApiParameters {

    public Collection<ExecutorStatus> executorStatusList;

    public static class ExecutorStatus {

        public String id;

        public Boolean isDone;
    }
}
