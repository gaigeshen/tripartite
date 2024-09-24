package work.gaigeshen.tripartite.his.procurement.openapi.parameters.inputdata.med;

import lombok.Data;
import work.gaigeshen.tripartite.his.procurement.openapi.parameters.HisProcurementInputData;

import java.math.BigDecimal;
import java.util.Collection;

/**
 * @author gaigeshen
 */
@Data
public class HisProcurementReturnApplyInputData implements HisProcurementInputData {

    private String medinsCode;

    private String retnRea;

    private Collection<ListItem> list;

    @Data
    public static class ListItem {

        private String shpId;

        private BigDecimal retnCnt;
    }
}
