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
public class HisProcurementShipmentListResponse extends AbstractHisProcurementResponse {

    private Collection<ListItem> dataList;

    private Integer currentPageNumber;

    private Integer totalPageCount;

    private Integer totalRecordCount;

    @Data
    public static class ListItem {

        private String planDetlMemo;

        private BigDecimal shpPric;

        private String outstoTime;

        private BigDecimal shpCnt;

        private String invoicePrimaryIds;

        private String ordDetlId;

        private BigDecimal purcCnt;

        private BigDecimal retnCnt;

        private String cnclTime;

        private String prodId;

        private BigDecimal avlShpCnt;

        private String expyEndtime;

        private String hospListId;

        private String ordId;

        private String admorgCode;

        private String purcPlanCode;

        private String ordCode;

        private String hospItemId;

        private BigDecimal shppCnt;

        private String prodName;

        private String shpStas;

        private String medinsName;

        private String delventpCode;

        private String shpTime;

        private String manuLotnum;

        private String shppTime;

        private String cnclType;

        private String itemname;

        private String shpMemo;

        private BigDecimal purcAmt;

        private BigDecimal rtnbCnt;

        private BigDecimal shpAmt;

        private String shpCode;

        private String invoiceCode;

        private String sendTime;

        private String delventpName;

        private BigDecimal shppAmt;

        private String pubonlnRsltId;

        private String shpId;

        private String delventpCnfmShppTime;

        private String delventpCnfmShppStas;

        private BigDecimal ordSumamt;

        private String invoiceId;

        private String medinsCode;

        private String admorgName;

        private BigDecimal purcpric;
    }
}
