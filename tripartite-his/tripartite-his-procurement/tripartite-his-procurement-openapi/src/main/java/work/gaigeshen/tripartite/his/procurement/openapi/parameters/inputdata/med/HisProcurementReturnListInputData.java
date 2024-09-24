package work.gaigeshen.tripartite.his.procurement.openapi.parameters.inputdata.med;

import lombok.Data;
import work.gaigeshen.tripartite.his.procurement.openapi.parameters.HisProcurementInputData;

/**
 * @author gaigeshen
 */
@Data
public class HisProcurementReturnListInputData implements HisProcurementInputData {

    private String medinsCode;

    private String delventpCode;

    private String startMedinsRetnTime;

    private String endMedinsRetnTime;

    private Integer retnChkStas;

    private Integer current;

    private Integer size;
}
