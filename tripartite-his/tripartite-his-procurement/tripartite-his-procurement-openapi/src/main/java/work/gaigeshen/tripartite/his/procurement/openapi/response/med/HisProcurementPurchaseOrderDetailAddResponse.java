package work.gaigeshen.tripartite.his.procurement.openapi.response.med;

import lombok.Data;
import lombok.EqualsAndHashCode;
import work.gaigeshen.tripartite.his.procurement.openapi.response.AbstractHisProcurementResponse;

import java.math.BigDecimal;
import java.util.Collection;

/**
 * @author gaigeshen
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class HisProcurementPurchaseOrderDetailAddResponse extends AbstractHisProcurementResponse {

    private String purcCode;

    private BigDecimal planSumamt;

    private Collection<ListItem> dataList;

    @Data
    public static class ListItem {

        private Integer returnCode;

        private String returnMsg;

        private String hospPurcDetlId;

        private String purcPlanDetId;
    }
}
