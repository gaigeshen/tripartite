package work.gaigeshen.tripartite.his.procurement.openapi;

import work.gaigeshen.tripartite.core.WebException;
import work.gaigeshen.tripartite.core.WebExecutor;
import work.gaigeshen.tripartite.his.procurement.openapi.config.HisProcurementConfig;
import work.gaigeshen.tripartite.his.procurement.openapi.parameters.HisProcurementParameters;
import work.gaigeshen.tripartite.his.procurement.openapi.response.AbstractHisProcurementResponse;
import work.gaigeshen.tripartite.his.procurement.openapi.response.HisProcurementResponse;

import java.util.Objects;

/**
 * @author gaigeshen
 */
public abstract class HisProcurementAbstractClient implements HisProcurementBasicClient {

    private final HisProcurementConfig config;

    private final WebExecutor executor;

    protected HisProcurementAbstractClient(HisProcurementConfig config, WebExecutor executor) {
        if (Objects.isNull(config)) {
            throw new IllegalArgumentException("config cannot be null");
        }
        if (Objects.isNull(executor)) {
            throw new IllegalArgumentException("executor cannot be null");
        }
        this.config = config;
        this.executor = executor;
    }

    @Override
    public HisProcurementConfig getHisProcurementConfig() {
        return config;
    }

    @Override
    public <R extends HisProcurementResponse> R execute(HisProcurementParameters parameters, Class<R> responseClass, String uri)
            throws HisProcurementClientException {
        if (Objects.isNull(parameters)) {
            throw new IllegalArgumentException("parameters cannot be null");
        }
        if (Objects.isNull(responseClass)) {
            throw new IllegalArgumentException("response class cannot be null");
        }
        try {
            R response = executor.execute(config.getServerHost() + uri, parameters, responseClass);
            return validateResponse(response);
        } catch (WebException e) {
            throw new HisProcurementClientException(e.getMessage(), e);
        }
    }

    protected <R extends HisProcurementResponse> R validateResponse(R response) throws HisProcurementClientException {
        if (Objects.isNull(response)) {
            throw new HisProcurementClientException("could not validate null response");
        }
        if (response instanceof AbstractHisProcurementResponse) {
            AbstractHisProcurementResponse abstractResponse = (AbstractHisProcurementResponse) response;
            if (!Objects.equals(abstractResponse.getReturnCode(), 0)) {
                throw new HisProcurementClientException("[ " + abstractResponse.getReturnCode() + " ] " + abstractResponse.getReturnMsg());
            }
        }
        return response;
    }
}
