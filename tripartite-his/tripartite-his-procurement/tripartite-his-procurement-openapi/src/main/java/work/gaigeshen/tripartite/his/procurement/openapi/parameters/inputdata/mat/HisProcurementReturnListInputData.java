package work.gaigeshen.tripartite.his.procurement.openapi.parameters.inputdata.mat;

import lombok.Data;
import work.gaigeshen.tripartite.his.procurement.openapi.parameters.HisProcurementInputData;

/**
 * @author gaigeshen
 */
@Data
public class HisProcurementReturnListInputData implements HisProcurementInputData {

    private String medinsCode;

    private String retnCode;

    private String strUpTime;

    private String endUpTime;

    private Integer current;

    private Integer size;
}
