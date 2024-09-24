package work.gaigeshen.tripartite.his.procurement.openapi.response.mat;

import lombok.Data;
import lombok.EqualsAndHashCode;
import work.gaigeshen.tripartite.his.procurement.openapi.response.AbstractHisProcurementResponse;

import java.math.BigDecimal;
import java.util.Collection;

/**
 * @author gaigeshen
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class HisProcurementDirectoryListResponse extends AbstractHisProcurementResponse {

    private Collection<ListItem> dataList;

    private Integer currentPageNumber;

    private Integer totalPageCount;

    private Integer totalRecordCount;

    @Data
    public static class ListItem {

        private String mcsCode;

        private String prodName;

        private String mcsRegno;

        private String mcsRegcertName;

        private String prodSpec;

        private String mcsMol;

        private String zclassCode;

        private String zclassName;

        private String primDirectory;

        private String secondDirectory;

        private String prodentpCode;

        private String prxyEntpCode;

        private String prodentpName;

        private BigDecimal pubonlnPric;

        private String mcsType;

        private String origin;

        private String unt;

        private String pubonlnType;

        private String tenditmId;

        private String tenditmName;

        private String pubonlnId;

        private String mcsInfoId;
    }
}
