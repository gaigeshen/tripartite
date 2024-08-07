package work.gaigeshen.tripartite.his.procurement.openapi.parameters.inputdata.mat;

import lombok.Data;
import work.gaigeshen.tripartite.his.procurement.openapi.parameters.HisProcurementInputData;

import java.math.BigDecimal;

/**
 * @author gaigeshen
 */
@Data
public class HisProcurementReturnApplyInputData implements HisProcurementInputData {

    private String medinsCode;

    private String shpCode;

    private BigDecimal retnCnt;

    private String medinsRetnRea;
}
