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
public class HisProcurementPurchaseOrderListResponse extends AbstractHisProcurementResponse {

    private Collection<ListItem> data;

    @Data
    public static class ListItem {

        private String purcPlanId;

        private String planDetlMemo;

        private String itemname;

        private BigDecimal purcCnt;

        private String pacMatl;

        private String purcPlanName;

        private String purcPlanDetId;

        private BigDecimal highSelLmtpric;

        private String prodSpec;

        private String delventpName;

        private String purcType;

        private String dosform;

        private String prodCode;

        private String hiPayPric;

        private String prodName;

        private String addrId;

        private BigDecimal pubonlnPric;

        private String addr;

        private String purcpric;
    }
}
