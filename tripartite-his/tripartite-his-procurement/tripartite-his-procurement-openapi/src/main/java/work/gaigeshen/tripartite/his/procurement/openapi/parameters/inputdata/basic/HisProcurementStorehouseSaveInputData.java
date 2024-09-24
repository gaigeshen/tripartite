package work.gaigeshen.tripartite.his.procurement.openapi.parameters.inputdata.basic;

import lombok.Data;
import work.gaigeshen.tripartite.his.procurement.openapi.parameters.HisProcurementInputData;

/**
 * @author gaigeshen
 */
@Data
public class HisProcurementStorehouseSaveInputData implements HisProcurementInputData {

    private String addrId;

    private String entpCode;

    private String prov;

    private String city;

    private String coty;

    private String addr;

    private String conerName;

    private String conerTel;

    private String invottl;

    private String stroomName;

    private Integer defFlag;

    private Integer mcsFlag;

}
