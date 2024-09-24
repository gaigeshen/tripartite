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
public class HisProcurementDirectoryUsedListResponse extends AbstractHisProcurementResponse {

    private Collection<ListItem> dataList;

    private Integer currentPageNumber;

    private Integer totalPageCount;

    private Integer totalRecordCount;

    @Data
    public static class ListItem {

        private String zclassName;

        private String mcsCode;

        private String mcsRegno;

        private String regcert;

        private String minpacuntName;

        private String mcsMol;

        private String prxyEntpCode;

        private String prxyEntpName;

        private String mcsSpec;

        private String hospListId;

        private BigDecimal hospPurcPric;

        private String prodentpCode;

        private String prodentpName;

        private String delventpName;

        private String mcsInfoId;

        private String pubonlnRsltId;

        private String secondDirectory;

        private String primDirectory;

        private String prodName;

        private BigDecimal pubonlnPric;

        private String delventpCode;

        private String updtTime;
    }
}
