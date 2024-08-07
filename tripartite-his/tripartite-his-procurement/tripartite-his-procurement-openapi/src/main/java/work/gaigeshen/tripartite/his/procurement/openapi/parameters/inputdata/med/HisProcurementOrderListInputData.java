package work.gaigeshen.tripartite.his.procurement.openapi.parameters.inputdata.med;

import lombok.Data;
import work.gaigeshen.tripartite.his.procurement.openapi.parameters.HisProcurementInputData;

import java.util.Collection;

/**
 * @author gaigeshen
 */
@Data
public class HisProcurementOrderListInputData implements HisProcurementInputData {

    private String medinsCode;

    private String purcCode;

    private String delventpCode;

    private String crteTimeUn;

    private String crteTimeEn;

    private Integer current;

    private Integer size;

    private Collection<ListItem> list;

    @Data
    public static class ListItem {

        private String ordDetlId;
    }
}
