package work.gaigeshen.tripartite.his.procurement.openapi.parameters.inputdata.mat;

import lombok.Data;
import work.gaigeshen.tripartite.his.procurement.openapi.parameters.HisProcurementInputData;

/**
 * @author gaigeshen
 */
@Data
public class HisProcurementStatementCreateInputData implements HisProcurementInputData {

    private String medinsCode;

    private String delvEntpCode;

    private String delvEntpName;
}
