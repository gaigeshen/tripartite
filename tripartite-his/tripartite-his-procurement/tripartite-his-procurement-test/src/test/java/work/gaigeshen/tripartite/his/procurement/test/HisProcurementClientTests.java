package work.gaigeshen.tripartite.his.procurement.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import work.gaigeshen.tripartite.his.procurement.openapi.HisProcurementClient;
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
  private HisProcurementClient hisProcurementClient;

  @Test
  public void getHisProcurementConfigTest() {
    HisProcurementConfig config = hisProcurementClient.getHisProcurementConfig();
    Assertions.assertNotNull(config);
  }

  @Test
  public void listDirectoryTest() {
    HisProcurementDirectoryListInputData inputData = new HisProcurementDirectoryListInputData();
    inputData.current = 1;
    inputData.size = 10;

    HisProcurementDirectoryListResponse response = hisProcurementClient.listDirectory(inputData);
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

    HisProcurementDirectoryAddResponse response = hisProcurementClient.addDirectory(inputData);
    Assertions.assertNotNull(response.dataList);
    Assertions.assertEquals(2, response.dataList.size());
  }

  @Test
  public void listUsedDirectoryTest() {
    HisProcurementDirectoryUsedListInputData inputData = new HisProcurementDirectoryUsedListInputData();
    inputData.current = 1;
    inputData.size = 10;

    HisProcurementDirectoryUsedListResponse response = hisProcurementClient.listUsedDirectory(inputData);
    Assertions.assertNotNull(response.dataList);
    Assertions.assertEquals(1, response.currentPageNumber);
  }

  @Test
  public void createPurchaseOrderTest() {
    HisProcurementConfig config = hisProcurementClient.getHisProcurementConfig();

    HisProcurementPurchaseOrderCreateInputData inputData = new HisProcurementPurchaseOrderCreateInputData();
    inputData.medinsCode = config.getAccount();
    inputData.planSumamt = new BigDecimal("1342.5");
    inputData.addrId = "1554719062308093954";
    inputData.conerName = "测试联系人";
    inputData.conerTel = "测试联系电话";
    inputData.invottl = "测试发票抬头";
    inputData.chkStas = 2;
    inputData.list = new ArrayList<>();

    HisProcurementPurchaseOrderCreateInputData.ListItem listItem1 = new HisProcurementPurchaseOrderCreateInputData.ListItem();
    listItem1.hospListId = "1554711114110402561";
    listItem1.delventpCode = "91330100757215118Y";
    listItem1.delventpName = "杭州艾力康医药科技有限公司";
    listItem1.purcCnt = new BigDecimal("1");
    listItem1.planDetlMemo = "测试备注1";
    listItem1.addrId = "1554719062308093954";
    listItem1.hospPurcDetlId = "20220804003";
    inputData.list.add(listItem1);

    HisProcurementPurchaseOrderCreateInputData.ListItem listItem2 = new HisProcurementPurchaseOrderCreateInputData.ListItem();
    listItem2.hospListId = "1554711114013933569";
    listItem2.delventpCode = "120";
    listItem2.delventpName = "上海聚通医疗器械采购中心";
    listItem2.purcCnt = new BigDecimal("1");
    listItem2.planDetlMemo = "测试备注2";
    listItem2.addrId = "1554719062308093954";
    listItem2.hospPurcDetlId = "20220804004";
    inputData.list.add(listItem2);

    HisProcurementPurchaseOrderCreateResponse response = hisProcurementClient.createPurchaseOrder(inputData);
    Assertions.assertNotNull(response.dataList);
    Assertions.assertEquals(2, response.dataList.size());
  }

  @Test
  public void sendPurchaseOrderTest() {
    HisProcurementConfig config = hisProcurementClient.getHisProcurementConfig();

    HisProcurementPurchaseOrderSendInputData inputData = new HisProcurementPurchaseOrderSendInputData();
    inputData.medinsCode = config.getAccount();
    inputData.purcCode = "1554994418189484034";
    inputData.chkStas = 4;

    HisProcurementPurchaseOrderSendResponse response = hisProcurementClient.sendPurchaseOrder(inputData);
    Assertions.assertNotNull(response.ordDetlIdList);
    Assertions.assertEquals(2, response.ordDetlIdList.size());
  }

  @Test
  public void cancelPurchaseOrderTest() {
    HisProcurementConfig config = hisProcurementClient.getHisProcurementConfig();

    HisProcurementPurchaseOrderCancelInputData inputData = new HisProcurementPurchaseOrderCancelInputData();
    inputData.medinsCode = config.getAccount();
    inputData.purcCode = "1554995965749248001";

    HisProcurementPurchaseOrderCancelResponse response = hisProcurementClient.cancelPurchaseOrder(inputData);
    Assertions.assertNotNull(response.PurcCode);
    Assertions.assertEquals(response.PurcCode, inputData.purcCode);
  }
}
