package work.gaigeshen.tripartite.his.procurement.openapi.parameters.inputdata;

import work.gaigeshen.tripartite.his.procurement.openapi.parameters.HisProcurementInputData;

/**
 *
 * @author gaigeshen
 */
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

  public String getAddrId() {
    return addrId;
  }

  public void setAddrId(String addrId) {
    this.addrId = addrId;
  }

  public String getEntpCode() {
    return entpCode;
  }

  public void setEntpCode(String entpCode) {
    this.entpCode = entpCode;
  }

  public String getProv() {
    return prov;
  }

  public void setProv(String prov) {
    this.prov = prov;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getCoty() {
    return coty;
  }

  public void setCoty(String coty) {
    this.coty = coty;
  }

  public String getAddr() {
    return addr;
  }

  public void setAddr(String addr) {
    this.addr = addr;
  }

  public String getConerName() {
    return conerName;
  }

  public void setConerName(String conerName) {
    this.conerName = conerName;
  }

  public String getConerTel() {
    return conerTel;
  }

  public void setConerTel(String conerTel) {
    this.conerTel = conerTel;
  }

  public String getInvottl() {
    return invottl;
  }

  public void setInvottl(String invottl) {
    this.invottl = invottl;
  }

  public String getStroomName() {
    return stroomName;
  }

  public void setStroomName(String stroomName) {
    this.stroomName = stroomName;
  }

  public Integer getDefFlag() {
    return defFlag;
  }

  public void setDefFlag(Integer defFlag) {
    this.defFlag = defFlag;
  }

  public Integer getMcsFlag() {
    return mcsFlag;
  }

  public void setMcsFlag(Integer mcsFlag) {
    this.mcsFlag = mcsFlag;
  }
}
