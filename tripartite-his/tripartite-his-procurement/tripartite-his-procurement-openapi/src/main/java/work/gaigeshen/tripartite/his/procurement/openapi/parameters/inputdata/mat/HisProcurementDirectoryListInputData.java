package work.gaigeshen.tripartite.his.procurement.openapi.parameters.inputdata.mat;

import lombok.Data;
import work.gaigeshen.tripartite.his.procurement.openapi.parameters.HisProcurementInputData;

/**
 * @author gaigeshen
 */
@Data
public class HisProcurementDirectoryListInputData implements HisProcurementInputData {

    private String mcsCode;

    private String mcsInfoId;

    private String prodName;

    private String mcsRegno;

    private String pubonlnId;

    private String strUpTime;

    private String endUpTime;

    private Integer current;

    private Integer size;
}
