package work.gaigeshen.tripartite.his.procurement.openapi.response.med;

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

        private String itemCodg;

        private BigDecimal convrat;

        private String medinsListCodg;

        private String prodId;

        private String hospListId;

        private BigDecimal hospPurcPric;

        private String AddDateTime;

        private String admorgCode;

        private String aprvno;

        private String dosform;

        private String prodName;

        private String medinsName;

        private String delventpCode;

        private String itemname;

        private String drugInfoId;

        private String prodBigPac;

        private String prodSpec;

        private String delventpName;

        private String prodCode;

        private String pubonlnRsltId;

        private String pubonlnStas;

        private String prodPac;

        private String UpdateTime;

        private String pacmatl;

        private BigDecimal pubonlnPric;

        private String hospBidprcuItemId;

        private String prodSoucName;

        private String medinsCode;

        private String admorgName;
    }
}
