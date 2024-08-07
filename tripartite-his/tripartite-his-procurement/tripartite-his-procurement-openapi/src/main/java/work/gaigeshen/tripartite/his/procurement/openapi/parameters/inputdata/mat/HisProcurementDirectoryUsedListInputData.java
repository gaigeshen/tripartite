package work.gaigeshen.tripartite.his.procurement.openapi.parameters.inputdata.mat;

import lombok.Data;
import work.gaigeshen.tripartite.his.procurement.openapi.parameters.HisProcurementInputData;

/**
 * @author gaigeshen
 */
@Data
public class HisProcurementDirectoryUsedListInputData implements HisProcurementInputData {

    private String mcsCode;

    private String pubonlnId;

    private String hospListId;

    private String prodName;

    private String mcsRegno;

    private String strUpTime;

    private String endUpTime;

    private Integer current;

    private Integer size;

}
