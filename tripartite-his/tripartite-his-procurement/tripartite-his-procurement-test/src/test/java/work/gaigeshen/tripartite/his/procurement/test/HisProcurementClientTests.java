package work.gaigeshen.tripartite.his.procurement.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import work.gaigeshen.tripartite.his.procurement.openapi.HisProcurementClient;
import work.gaigeshen.tripartite.his.procurement.openapi.HisProcurementClients;
import work.gaigeshen.tripartite.his.procurement.openapi.config.HisProcurementConfig;
import work.gaigeshen.tripartite.his.procurement.openapi.parameters.inputdata.*;
import work.gaigeshen.tripartite.his.procurement.openapi.response.*;

import java.math.BigDecimal;
import java.util.ArrayList;

/**
 *
 * @author gaigeshen
 */
@SpringBootTest
public class HisProcurementClientTests {

  @Autowired
  private HisProcurementClients hisProcurementClients;

  @Test
  public void getHisProcurementConfigTest() {
    HisProcurementConfig config = hisProcurementClients.getClient().getHisProcurementConfig();
    Assertions.assertNotNull(config);
  }

  @Test
  public void listDirectoryTest() {
    HisProcurementDirectoryListInputData inputData = new HisProcurementDirectoryListInputData();
    inputData.current = 1;
    inputData.size = 10;

    HisProcurementDirectoryListResponse response = hisProcurementClients.getClient().listDirectory(inputData);
    Assertions.assertNotNull(response.dataList);
    Assertions.assertEquals(1, response.currentPageNumber);
  }

  @Test
  public void addDirectoryTest() {
    HisProcurementDirectoryAddInputData inputData = new HisProcurementDirectoryAddInputData();
    inputData.list = new ArrayList<>();

    HisProcurementDirectoryAddInputData.ListItem listItem1 = new HisProcurementDirectoryAddInputData.ListItem();
    listItem1.pubonlnId = "100000000000000003";
    inputData.list.add(listItem1);

    HisProcurementDirectoryAddInputData.ListItem listItem2 = new HisProcurementDirectoryAddInputData.ListItem();
    listItem2.pubonlnId = "100000000000000004";
    inputData.list.add(listItem2);

    HisProcurementDirectoryAddResponse response = hisProcurementClients.getClient().addDirectory(inputData);
    Assertions.assertNotNull(response.dataList);
    Assertions.assertEquals(2, response.dataList.size());
  }

  @Test
  public void listUsedDirectoryTest() {
    HisProcurementDirectoryUsedListInputData inputData = new HisProcurementDirectoryUsedListInputData();
    inputData.mcsCode = "C02064106501000100550000003";
    inputData.hospListId = "1554991252811169793";
    inputData.current = 1;
    inputData.size = 10;

    HisProcurementDirectoryUsedListResponse response = hisProcurementClients.getClient().listUsedDirectory(inputData);
    Assertions.assertNotNull(response.dataList);
    Assertions.assertEquals(1, response.currentPageNumber);
  }

  @Test
  public void createPurchaseOrderTest() {
    HisProcurementClient client = hisProcurementClients.getClient();
    HisProcurementConfig config = client.getHisProcurementConfig();

    HisProcurementPurchaseOrderCreateInputData inputData = new HisProcurementPurchaseOrderCreateInputData();
    inputData.medinsCode = config.getAccount();
    inputData.planSumamt = new BigDecimal("2320");
    inputData.addrId = "1554719062308093954";
    inputData.conerName = "测试联系人";
    inputData.conerTel = "测试联系电话";
    inputData.invottl = "测试发票抬头";
    inputData.chkStas = 2;
    inputData.list = new ArrayList<>();

    HisProcurementPurchaseOrderCreateInputData.ListItem listItem1 = new HisProcurementPurchaseOrderCreateInputData.ListItem();
    listItem1.hospListId = "1554991252811169793";
    listItem1.delventpCode = "PS123456789";
    listItem1.delventpName = "配送测试企业";
    listItem1.purcCnt = new BigDecimal("1");
    listItem1.planDetlMemo = "测试备注1";
    listItem1.addrId = "1554719062308093954";
    listItem1.hospPurcDetlId = "20220804003";
    inputData.list.add(listItem1);

    HisProcurementPurchaseOrderCreateInputData.ListItem listItem2 = new HisProcurementPurchaseOrderCreateInputData.ListItem();
    listItem2.hospListId = "1554991252689534977";
    listItem2.delventpCode = "PS123456789";
    listItem2.delventpName = "配送测试企业";
    listItem2.purcCnt = new BigDecimal("1");
    listItem2.planDetlMemo = "测试备注2";
    listItem2.addrId = "1554719062308093954";
    listItem2.hospPurcDetlId = "20220804004";
    inputData.list.add(listItem2);

    HisProcurementPurchaseOrderCreateResponse response = client.createPurchaseOrder(inputData);
    Assertions.assertNotNull(response.dataList);
    Assertions.assertEquals(2, response.dataList.size());
  }

  @Test
  public void sendPurchaseOrderTest() {
    HisProcurementClient client = hisProcurementClients.getClient();
    HisProcurementConfig config = client.getHisProcurementConfig();

    HisProcurementPurchaseOrderSendInputData inputData = new HisProcurementPurchaseOrderSendInputData();
    inputData.medinsCode = config.getAccount();
    inputData.purcCode = "1555104239597301761";
    inputData.chkStas = 4;

    HisProcurementPurchaseOrderSendResponse response = client.sendPurchaseOrder(inputData);
    Assertions.assertNotNull(response.ordDetlIdList);
    Assertions.assertEquals(2, response.ordDetlIdList.size());
  }

  @Test
  public void cancelPurchaseOrderTest() {
    HisProcurementClient client = hisProcurementClients.getClient();
    HisProcurementConfig config = client.getHisProcurementConfig();

    HisProcurementPurchaseOrderCancelInputData inputData = new HisProcurementPurchaseOrderCancelInputData();
    inputData.medinsCode = config.getAccount();
    inputData.purcCode = "1554995965749248001";

    HisProcurementPurchaseOrderCancelResponse response = client.cancelPurchaseOrder(inputData);
    Assertions.assertNotNull(response.PurcCode);
    Assertions.assertEquals(response.PurcCode, inputData.purcCode);
  }

  @Test
  public void listPurchaseOrderTest() {
    HisProcurementClient client = hisProcurementClients.getClient();
    HisProcurementConfig config = client.getHisProcurementConfig();

    HisProcurementPurchaseOrderListInputData inputData = new HisProcurementPurchaseOrderListInputData();
    inputData.medinsCode = config.getAccount();
    inputData.ordId = "1555104313891008514";
    inputData.current = 1;
    inputData.size = 10;

    HisProcurementPurchaseOrderListResponse response = client.listPurchaseOrder(inputData);
    Assertions.assertNotNull(response.dataList);
    Assertions.assertEquals(1, response.currentPageNumber);
  }

  @Test
  public void takeDeliveryTest() {
    HisProcurementClient client = hisProcurementClients.getClient();
    HisProcurementConfig config = client.getHisProcurementConfig();

    HisProcurementTakeDeliveryInputData inputData = new HisProcurementTakeDeliveryInputData();
    inputData.medinsCode = config.getAccount();
    inputData.shpCode = "I_S2332022080400304";
    inputData.shppCnt = new BigDecimal("1");

    HisProcurementTakeDeliveryResponse response = client.takeDelivery(inputData);
    Assertions.assertNotNull(response.shpCode);
    Assertions.assertEquals(inputData.shpCode, response.shpCode);
  }

  @Test
  public void applyReturnTest() {
    HisProcurementClient client = hisProcurementClients.getClient();
    HisProcurementConfig config = client.getHisProcurementConfig();

    HisProcurementReturnApplyInputData inputData = new HisProcurementReturnApplyInputData();
    inputData.medinsCode = config.getAccount();
    inputData.shpCode = "I_S2332022080400303";
    inputData.retnCnt = new BigDecimal("1");
    inputData.medinsRetnRea = "这是退货原因";

    HisProcurementReturnApplyResponse response = client.applyReturn(inputData);
    Assertions.assertNotNull(response.retnCode);
  }

  @Test
  public void listReturnTest() {
    HisProcurementClient client = hisProcurementClients.getClient();
    HisProcurementConfig config = client.getHisProcurementConfig();

    HisProcurementReturnListInputData inputData = new HisProcurementReturnListInputData();
    inputData.medinsCode = config.getAccount();
    inputData.retnCode = "I_R2332022080400001";
    inputData.strUpTime = "2022-08-04 00:00:00";
    inputData.endUpTime = "2022-08-04 23:59:59";
    inputData.current = 1;
    inputData.size = 10;

    HisProcurementReturnListResponse response = client.listReturn(inputData);
    Assertions.assertNotNull(response.dataList);
    Assertions.assertEquals(1, response.currentPageNumber);
  }

  @Test
  public void listProductDeliveryEnterpriseTest() {
    HisProcurementClient client = hisProcurementClients.getClient();
    HisProcurementConfig config = client.getHisProcurementConfig();

    HisProcurementProductDeliveryEnterpriseListInputData inputData = new HisProcurementProductDeliveryEnterpriseListInputData();
    inputData.medinsCode = config.getAccount();
    inputData.hospListId = "1554991252811169793";
    inputData.current = 1;
    inputData.size = 10;

    HisProcurementProductDeliveryEnterpriseListResponse response = client.listProductDeliveryEnterprise(inputData);
    Assertions.assertNotNull(response.dataList);
    Assertions.assertEquals(inputData.current, response.currentPageNumber);
  }

  @Test
  public void setProductDeliveryEnterpriseTest() {
    HisProcurementClient client = hisProcurementClients.getClient();
    HisProcurementConfig config = client.getHisProcurementConfig();

    HisProcurementProductDeliveryEnterpriseSetInputData inputData = new HisProcurementProductDeliveryEnterpriseSetInputData();
    inputData.medinsCode = config.getAccount();
    inputData.list = new ArrayList<>();

    HisProcurementProductDeliveryEnterpriseSetInputData.ListItem listItem1 = new HisProcurementProductDeliveryEnterpriseSetInputData.ListItem();
    listItem1.hospListId = "1554991252811169793";
    listItem1.delvEntpUscc = "PS123456789";
    listItem1.delvEntpName = "配送测试企业";
    inputData.list.add(listItem1);

    HisProcurementProductDeliveryEnterpriseSetResponse response = client.setProductDeliveryEnterprise(inputData);
    Assertions.assertNotNull(response.dataList);
    Assertions.assertEquals(1, response.dataList.size());
    Assertions.assertEquals(listItem1.hospListId, response.dataList.iterator().next().hospListId);
  }

  @Test
  public void createStatementTest() {
    HisProcurementClient client = hisProcurementClients.getClient();
    HisProcurementConfig config = client.getHisProcurementConfig();

    HisProcurementStatementCreateInputData inputData = new HisProcurementStatementCreateInputData();
    inputData.medinsCode = config.getAccount();
    inputData.delvEntpCode = "PS123456789";
    inputData.delvEntpName = "配送测试企业";

    HisProcurementStatementCreateResponse response = client.createStatement(inputData);
    Assertions.assertNotNull(response.pryOrdId);
  }

  @Test
  public void addStatementDetailTest() {
    HisProcurementClient client = hisProcurementClients.getClient();
    HisProcurementConfig config = client.getHisProcurementConfig();

    HisProcurementStatementDetailAddInputData inputData = new HisProcurementStatementDetailAddInputData();
    inputData.medinsCode = config.getAccount();
    inputData.payOrdId = "20220804165336";
    inputData.list = new ArrayList<>();

    HisProcurementStatementDetailAddInputData.ListItem listItem1 = new HisProcurementStatementDetailAddInputData.ListItem();
    listItem1.payyDetlId = "I_S2332022080400303";
    listItem1.stogTag = "1";
    inputData.list.add(listItem1);

    HisProcurementStatementDetailAddInputData.ListItem listItem2 = new HisProcurementStatementDetailAddInputData.ListItem();
    listItem2.payyDetlId = "I_S2332022080400304";
    listItem2.stogTag = "1";
    inputData.list.add(listItem2);

    HisProcurementStatementDetailAddResponse response = client.addStatementDetail(inputData);
    Assertions.assertNotNull(response.dataList);
    Assertions.assertEquals(2, response.dataList.size());
    Assertions.assertTrue(response.dataList.contains(listItem1.payyDetlId));
    Assertions.assertTrue(response.dataList.contains(listItem2.payyDetlId));
  }

  @Test
  public void submitStatementTest() {
    HisProcurementClient client = hisProcurementClients.getClient();
    HisProcurementConfig config = client.getHisProcurementConfig();

    HisProcurementStatementSubmitInputData inputData = new HisProcurementStatementSubmitInputData();
    inputData.medinsCode = config.getAccount();
    inputData.payOrdId = "20220804152607";

    HisProcurementStatementSubmitResponse response = client.submitStatement(inputData);
    Assertions.assertNotNull(response);
  }

  @Test
  public void examineStatementTest() {
    HisProcurementClient client = hisProcurementClients.getClient();
    HisProcurementConfig config = client.getHisProcurementConfig();

    HisProcurementStatementExamineInputData inputData = new HisProcurementStatementExamineInputData();
    inputData.medinsCode = config.getAccount();
    inputData.payOrdId = "20220804152607";
    inputData.chkStas = 2;

    HisProcurementStatementExamineResponse response = client.examineStatement(inputData);
    Assertions.assertNotNull(response);
  }

  @Test
  public void listStorehouseTest() {
    HisProcurementClient client = hisProcurementClients.getClient();
    HisProcurementConfig config = client.getHisProcurementConfig();

    HisProcurementStorehouseListInputData inputData = new HisProcurementStorehouseListInputData();
    inputData.orgCode = config.getAccount();
    inputData.current = 1;
    inputData.size = 10;

    HisProcurementStorehouseListResponse response = client.listStorehouse(inputData);
    Assertions.assertNotNull(response.dataList);
    Assertions.assertEquals(1, response.currentPageNumber);
  }

  @Test
  public void saveStorehouseTest() {
    HisProcurementClient client = hisProcurementClients.getClient();
    HisProcurementConfig config = client.getHisProcurementConfig();

    HisProcurementStorehouseSaveInputData inputData = new HisProcurementStorehouseSaveInputData();
    inputData.addrId = "1554719062308093954";
    inputData.entpCode = config.getAccount();
    inputData.prov = "浙江省";
    inputData.city = "杭州市";
    inputData.coty = "余杭区";
    inputData.addr = "五常街道100号";
    inputData.conerName = "李四";
    inputData.conerTel = "13888888888";
    inputData.invottl = "张三的发票抬头";
    inputData.stroomName = "五常街道上面的库房地址";
    inputData.defFlag = 1;
    inputData.mcsFlag = 1;

    HisProcurementStorehouseSaveResponse response = client.saveStorehouse(inputData);
    Assertions.assertNotNull(response.addrId);
    Assertions.assertEquals(inputData.addrId, response.addrId);
  }
}
