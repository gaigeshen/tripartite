package work.gaigeshen.tripartite.his.procurement.openapi.parameters.inputdata.mat;

import lombok.Data;
import work.gaigeshen.tripartite.his.procurement.openapi.parameters.HisProcurementInputData;

import java.math.BigDecimal;
import java.util.Collection;

/**
 * @author gaigeshen
 */
@Data
public class HisProcurementPurchaseOrderDetailAddInputData implements HisProcurementInputData {

    private String medinsCode;

    private String purcCode;

    private Integer chkStas;

    private Integer addorDelStas;

    private Collection<ListItem> list;

    @Data
    public static class ListItem {

        private String hospListId;

        private String delventpCode;

        private String delventpName;

        private BigDecimal purcCnt;

        private String planDetlMemo;

        private String addrId;

        private String hospPurcDetlId;

        private String purcPlanDetId;

    }
}
