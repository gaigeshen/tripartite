package work.gaigeshen.tripartite.his.procurement.openapi.parameters.inputdata.med;

import lombok.Data;
import work.gaigeshen.tripartite.his.procurement.openapi.parameters.HisProcurementInputData;

import java.math.BigDecimal;
import java.util.Collection;

/**
 * @author gaigeshen
 */
@Data
public class HisProcurementPurchaseOrderCreateInputData implements HisProcurementInputData {

    private String medinsCode;

    private BigDecimal planSumamt;

    private String addrId;

    private String conerName;

    private String conerTel;

    private String invottl;

    private Integer chkStas;

    private Collection<ListItem> list;

    @Data
    public static class ListItem {

        private String hospListId;

        private String delventpCode;

        private String delventpName;

        private BigDecimal purcCnt;

        private BigDecimal hospPurcPric;

        private String planDetlMemo;

        private String addrId;

        private String hospPurcDetlId;
    }
}
