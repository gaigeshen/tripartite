package work.gaigeshen.tripartite.his.procurement.openapi.response.mat;

import lombok.Data;
import lombok.EqualsAndHashCode;
import work.gaigeshen.tripartite.his.procurement.openapi.response.AbstractHisProcurementResponse;

import java.util.Collection;

/**
 * @author gaigeshen
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class HisProcurementStatementDetailAddResponse extends AbstractHisProcurementResponse {

    private Collection<ListItem> dataList;

    @Data
    public static class ListItem {

        private Integer returnCode;

        private String returnMsg;

        private String payyDetlId;
    }
}
