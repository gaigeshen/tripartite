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
public class HisProcurementReturnListResponse extends AbstractHisProcurementResponse {

    private Integer currentPageNumber;

    private Integer totalPageCount;

    private Integer totalRecordCount;

    private Collection<ListItem> dataList;

    @Data
    public static class ListItem {

        private String retnId;

        private String retnCode;

        private String ordCode;

        private String ordDetlId;

        private String shpCode;

        private String shpId;

        private String medinsCode;

        private String medinsName;

        private String delventpCode;

        private String delventpName;

        private String admorgCode;

        private String admorgName;

        private String prodId;

        private String prodName;

        private BigDecimal shpCnt;

        private BigDecimal shpPric;

        private BigDecimal shppCnt;

        private BigDecimal retnCnt;

        private BigDecimal rtnbCnt;

        private String hospListId;

        private String retnChkStas;

        private String medinsRetnRea;

        private String delventpFailRea;

        private String medinsRetnTime;

        private String delventpPassTime;

        private String delventpFailTime;

        private String cnclTime;

        private String manuLotnum;

        private String returnInvoiceId;

        private String pubonlnRsltId;
    }
}
