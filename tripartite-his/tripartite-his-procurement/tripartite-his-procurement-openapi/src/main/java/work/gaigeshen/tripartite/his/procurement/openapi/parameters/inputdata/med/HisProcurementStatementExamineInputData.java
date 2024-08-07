package work.gaigeshen.tripartite.his.procurement.openapi.parameters.inputdata.med;

import lombok.Data;
import work.gaigeshen.tripartite.his.procurement.openapi.parameters.HisProcurementInputData;

/**
 * @author gaigeshen
 */
@Data
public class HisProcurementStatementExamineInputData implements HisProcurementInputData {

    private String medinsCode;

    private String payOrdId;

    private String chkOpen;

    private Integer chkStas;
}
