package work.gaigeshen.tripartite.his.procurement.openapi.parameters.inputdata.mat;

import lombok.Data;
import work.gaigeshen.tripartite.his.procurement.openapi.parameters.HisProcurementInputData;

import java.util.Collection;

/**
 * @author gaigeshen
 */
@Data
public class HisProcurementDirectoryAddInputData implements HisProcurementInputData {

    private Collection<ListItem> list;

    @Data
    public static class ListItem {

        private String pubonlnId;
    }
}
