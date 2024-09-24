package work.gaigeshen.tripartite.his.procurement.openapi.parameters.inputdata.med;

import lombok.Data;
import work.gaigeshen.tripartite.his.procurement.openapi.parameters.HisProcurementInputData;

/**
 * @author gaigeshen
 */
@Data
public class HisProcurementStatementSubmitInputData implements HisProcurementInputData {

    private String medinsCode;

    private String payOrdId;

    private Integer chkStas;
}
