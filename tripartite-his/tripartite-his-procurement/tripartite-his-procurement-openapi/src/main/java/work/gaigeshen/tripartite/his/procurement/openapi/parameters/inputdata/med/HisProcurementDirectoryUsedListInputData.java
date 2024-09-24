package work.gaigeshen.tripartite.his.procurement.openapi.parameters.inputdata.med;

import lombok.Data;
import work.gaigeshen.tripartite.his.procurement.openapi.parameters.HisProcurementInputData;

/**
 * @author gaigeshen
 */
@Data
public class HisProcurementDirectoryUsedListInputData implements HisProcurementInputData {

    private String orgCode;

    private String prodCode;

    private String prodName;

    private String dosformName;

    private String specName;

    private String pacmatl;

    private String prodentpName;

    private String dclaEntpName;

    private String startUpdtTime;

    private String endUpdtTime;

    private Integer current;

    private Integer size;
}
