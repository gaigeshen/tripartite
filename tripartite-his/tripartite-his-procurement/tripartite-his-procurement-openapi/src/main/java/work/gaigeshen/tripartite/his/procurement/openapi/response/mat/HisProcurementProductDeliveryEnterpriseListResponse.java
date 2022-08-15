package work.gaigeshen.tripartite.his.procurement.openapi.response.mat;

import work.gaigeshen.tripartite.his.procurement.openapi.response.AbstractHisProcurementResponse;

import java.util.Collection;

/**
 * @author gaigeshen
 */
public class HisProcurementProductDeliveryEnterpriseListResponse extends AbstractHisProcurementResponse {

  private Collection<ListItem> dataList;

  private Integer currentPageNumber;

  private Integer totalPageCount;

  private Integer totalRecordCount;

  public Collection<ListItem> getDataList() {
    return dataList;
  }

  public void setDataList(Collection<ListItem> dataList) {
    this.dataList = dataList;
  }

  public Integer getCurrentPageNumber() {
    return currentPageNumber;
  }

  public void setCurrentPageNumber(Integer currentPageNumber) {
    this.currentPageNumber = currentPageNumber;
  }

  public Integer getTotalPageCount() {
    return totalPageCount;
  }

  public void setTotalPageCount(Integer totalPageCount) {
    this.totalPageCount = totalPageCount;
  }

  public Integer getTotalRecordCount() {
    return totalRecordCount;
  }

  public void setTotalRecordCount(Integer totalRecordCount) {
    this.totalRecordCount = totalRecordCount;
  }

  public static class ListItem {

    private String delventpName;

    private String admdvsName;

    private String prodCode;

    private String prodEntpName;

    private String delvEntpUscc;

    private String prodEntpUscc;

    private String admdvs;

    public String getDelventpName() {
      return delventpName;
    }

    public void setDelventpName(String delventpName) {
      this.delventpName = delventpName;
    }

    public String getAdmdvsName() {
      return admdvsName;
    }

    public void setAdmdvsName(String admdvsName) {
      this.admdvsName = admdvsName;
    }

    public String getProdCode() {
      return prodCode;
    }

    public void setProdCode(String prodCode) {
      this.prodCode = prodCode;
    }

    public String getProdEntpName() {
      return prodEntpName;
    }

    public void setProdEntpName(String prodEntpName) {
      this.prodEntpName = prodEntpName;
    }

    public String getDelvEntpUscc() {
      return delvEntpUscc;
    }

    public void setDelvEntpUscc(String delvEntpUscc) {
      this.delvEntpUscc = delvEntpUscc;
    }

    public String getProdEntpUscc() {
      return prodEntpUscc;
    }

    public void setProdEntpUscc(String prodEntpUscc) {
      this.prodEntpUscc = prodEntpUscc;
    }

    public String getAdmdvs() {
      return admdvs;
    }

    public void setAdmdvs(String admdvs) {
      this.admdvs = admdvs;
    }
  }
}
