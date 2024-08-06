package work.gaigeshen.tripartite.core.interceptor;

import work.gaigeshen.tripartite.core.WebExecutionException;
import work.gaigeshen.tripartite.core.util.ArgumentValidate;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;


/**
 * @author gaigeshen
 */
public class Interceptors {

    private final List<Interceptor> interceptors = new ArrayList<>();

    public Interceptors(Interceptor... interceptors) {
        ArgumentValidate.notNull(interceptors, "interceptors cannot be null");
        for (Interceptor interceptor : interceptors) {
            if (Objects.isNull(interceptor)) {
                continue;
            }
            this.interceptors.add(interceptor);
        }
    }

    public boolean hasInterceptors() {
        return !interceptors.isEmpty();
    }

    public Interceptor.Response intercept(Interceptor.Request request, Execution execution)
            throws InterceptingException, WebExecutionException {
        ArgumentValidate.notTrue(Objects.isNull(request) || Objects.isNull(execution),
                "request and execution cannot be null");
        return new InternalChain(interceptors, execution).intercept(request);
    }

    /**
     * @author gaigeshen
     */
    private static class InternalChain implements Interceptor.Chain {

        private final Iterator<Interceptor> iterator;

        private final Execution execution;

        private InternalChain(List<Interceptor> interceptors, Execution execution) {
            this.iterator = interceptors.iterator();
            this.execution = execution;
        }

        @Override
        public Interceptor.Response intercept(Interceptor.Request request) throws InterceptingException, WebExecutionException {
            if (iterator.hasNext()) {
                return iterator.next().intercept(request, this);
            }
            return execution.execute(request);
        }
    }
}
