package work.gaigeshen.tripartite.his.procurement.openapi.response;

/**
 * @author gaigeshen
 */
public class HisProcurementAccessTokenResponse extends AbstractHisProcurementResponse {

    private String accessToken;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}
