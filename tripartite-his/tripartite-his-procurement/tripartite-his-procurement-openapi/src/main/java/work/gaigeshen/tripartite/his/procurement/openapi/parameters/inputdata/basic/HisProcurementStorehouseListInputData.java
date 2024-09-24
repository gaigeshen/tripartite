package work.gaigeshen.tripartite.his.procurement.openapi.parameters.inputdata.basic;

import lombok.Data;
import work.gaigeshen.tripartite.his.procurement.openapi.parameters.HisProcurementInputData;

/**
 * @author gaigeshen
 */
@Data
public class HisProcurementStorehouseListInputData implements HisProcurementInputData {

    private String orgCode;

    private Integer mcsFlag;

    private Integer current;

    private Integer size;

}
