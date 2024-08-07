package work.gaigeshen.tripartite.his.procurement.openapi.response.basic;

import lombok.Data;
import lombok.EqualsAndHashCode;
import work.gaigeshen.tripartite.his.procurement.openapi.response.AbstractHisProcurementResponse;

import java.util.Collection;

/**
 * @author gaigeshen
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class HisProcurementStorehouseListResponse extends AbstractHisProcurementResponse {

    private Integer currentPageNumber;

    private Integer totalPageCount;

    private Integer totalRecordCount;

    private Collection<ListItem> dataList;

    @Data
    public static class ListItem {

        private String conerName;

        private String entpCode;

        private String mcsFlag;

        private String defFlag;

        private String prov;

        private String city;

        private String coty;

        private String invottl;

        private String addrId;

        private String addr;

        private String conerTel;

        private String stroomName;
    }
}
