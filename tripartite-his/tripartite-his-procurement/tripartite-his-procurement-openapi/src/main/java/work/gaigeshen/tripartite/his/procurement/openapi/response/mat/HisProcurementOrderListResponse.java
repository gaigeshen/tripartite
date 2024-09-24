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
public class HisProcurementOrderListResponse extends AbstractHisProcurementResponse {

    private Collection<ListItem> dataList;

    private Integer currentPageNumber;

    private Integer totalPageCount;

    private Integer totalRecordCount;

    @Data
    public static class ListItem {

        private String planDetlMemo;

        private String regcert;

        private String rgtMol;

        private String addTime;

        private String mcsType;

        private String ordDetlId;

        private String purcCnt;

        private String conerTel;

        private String ordId;

        private String subTime;

        private String prodentpName;

        private String purcPlanCode;

        private String wanTime;

        private String conerName;

        private String disYN;

        private String ordCode;

        private String prodName;

        private String addrId;

        private String disCnt;

        private String prodMatl;

        private String delventpCode;

        private String mcsRegno;

        private String disTime;

        private String discode;

        private String itemname;

        private String pacMatl;

        private String proTypeStock;

        private String prodSpec;

        private String delventpName;

        private String pubonlnRsltId;

        private String prodPac;

        private String pubonlnPric;

        private String qlv;

        private String dclaEntpName;

        private String medinsCode;

        private BigDecimal purcpric;

        private String wanCnt;
    }
}
