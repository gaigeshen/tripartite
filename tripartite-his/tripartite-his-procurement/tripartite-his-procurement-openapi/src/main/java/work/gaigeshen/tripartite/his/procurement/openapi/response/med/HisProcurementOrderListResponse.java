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
public class HisProcurementOrderListResponse extends AbstractHisProcurementResponse {

    private Collection<ListItem> dataList;

    private Integer currentPageNumber;

    private Integer totalPageCount;

    private Integer totalRecordCount;

    @Data
    public static class ListItem {

        private String shppEndTime;

        private String ordDetlId;

        private BigDecimal purcCnt;

        private String memo;

        private String cnclTime;

        private String hospListId;

        private String conerTel;

        private String ordId;

        private String stroomName;

        private String purcPlanCode;

        private String conerName;

        private String ordCode;

        private String shppStas;

        private String invottl;

        private String prodName;

        private String shpStas;

        private String medinsName;

        private String delventpCode;

        private String addr;

        private String shpTime;

        private String itemname;

        private String responseTime;

        private String ordDetlStas;

        private String responseStatus;

        private String sendTime;

        private String delventpName;

        private String delventpCnfmShppTime;

        private String shpEndTime;

        private BigDecimal ordSumamt;

        private String docmker;

        private String medinsCode;

        private BigDecimal purcpric;

        private String responseRemake;
    }
}
