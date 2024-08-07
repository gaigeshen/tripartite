package work.gaigeshen.tripartite.his.procurement.openapi.parameters.inputdata.mat;

import lombok.Data;
import work.gaigeshen.tripartite.his.procurement.openapi.parameters.HisProcurementInputData;

/**
 * @author gaigeshen
 */
@Data
public class HisProcurementOrderListInputData implements HisProcurementInputData {

    private String medinsCode;

    private String ordId;

    private String ordDetlId;

    private Integer current;

    private Integer size;
}
