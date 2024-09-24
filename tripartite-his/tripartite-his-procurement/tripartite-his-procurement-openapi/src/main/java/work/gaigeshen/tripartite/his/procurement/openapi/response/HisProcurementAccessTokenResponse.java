package work.gaigeshen.tripartite.his.procurement.openapi.response;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author gaigeshen
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class HisProcurementAccessTokenResponse extends AbstractHisProcurementResponse {

    private String accessToken;
}
