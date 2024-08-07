package work.gaigeshen.tripartite.his.procurement.openapi.response.mat;

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
public class HisProcurementReturnListResponse extends AbstractHisProcurementResponse {

    private Integer currentPageNumber;

    private Integer totalPageCount;

    private Integer totalRecordCount;

    private Collection<ListItem> dataList;

    @Data
    public static class ListItem {

        private String regcert;

        private String retnCode;

        private String rgtMol;

        private String purcCnt;

        private String medinsRetnRea;

        private String prodentpName;

        private String ordCode;

        private String prodName;

        private String prodMatl;

        private String delventpCode;

        private String manuLotnum;

        private String medinsRetnTime;

        private String mcsRegno;

        private String delventpFailRea;

        private String delventpPassTime;

        private String pacMatl;

        private String prodSpec;

        private BigDecimal retnCnt;

        private BigDecimal retnAmt;

        private String delventpName;

        private String delventpFailTime;

        private String retnChkStas;

        private String prodPac;

        private String returnInvoiceId;

        private String pubonlnPric;

        private String dclaEntpName;

        private String purcpric;
    }
}
