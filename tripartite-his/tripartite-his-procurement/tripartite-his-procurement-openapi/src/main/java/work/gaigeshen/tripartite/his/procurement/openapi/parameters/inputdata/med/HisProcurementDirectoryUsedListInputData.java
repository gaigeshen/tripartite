package work.gaigeshen.tripartite.his.procurement.openapi.parameters.inputdata.med;

import work.gaigeshen.tripartite.his.procurement.openapi.parameters.HisProcurementInputData;

/**
 *
 * @author gaigeshen
 */
public class HisProcurementDirectoryUsedListInputData implements HisProcurementInputData {

  private String orgCode;

  private String prodCode;

  private String prodName;

  private String dosformName;

  private String specName;

  private String pacmatl;

  private String prodentpName;

  private String dclaEntpName;

  private String startUpdtTime;

  private String endUpdtTime;

  private Integer current;

  private Integer size;

  public String getOrgCode() {
    return orgCode;
  }

  public void setOrgCode(String orgCode) {
    this.orgCode = orgCode;
  }

  public String getProdCode() {
    return prodCode;
  }

  public void setProdCode(String prodCode) {
    this.prodCode = prodCode;
  }

  public String getProdName() {
    return prodName;
  }

  public void setProdName(String prodName) {
    this.prodName = prodName;
  }

  public String getDosformName() {
    return dosformName;
  }

  public void setDosformName(String dosformName) {
    this.dosformName = dosformName;
  }

  public String getSpecName() {
    return specName;
  }

  public void setSpecName(String specName) {
    this.specName = specName;
  }

  public String getPacmatl() {
    return pacmatl;
  }

  public void setPacmatl(String pacmatl) {
    this.pacmatl = pacmatl;
  }

  public String getProdentpName() {
    return prodentpName;
  }

  public void setProdentpName(String prodentpName) {
    this.prodentpName = prodentpName;
  }

  public String getDclaEntpName() {
    return dclaEntpName;
  }

  public void setDclaEntpName(String dclaEntpName) {
    this.dclaEntpName = dclaEntpName;
  }

  public String getStartUpdtTime() {
    return startUpdtTime;
  }

  public void setStartUpdtTime(String startUpdtTime) {
    this.startUpdtTime = startUpdtTime;
  }

  public String getEndUpdtTime() {
    return endUpdtTime;
  }

  public void setEndUpdtTime(String endUpdtTime) {
    this.endUpdtTime = endUpdtTime;
  }

  public Integer getCurrent() {
    return current;
  }

  public void setCurrent(Integer current) {
    this.current = current;
  }

  public Integer getSize() {
    return size;
  }

  public void setSize(Integer size) {
    this.size = size;
  }
}
