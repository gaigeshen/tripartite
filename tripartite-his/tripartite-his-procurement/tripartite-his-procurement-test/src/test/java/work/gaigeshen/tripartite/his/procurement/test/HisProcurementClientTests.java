package work.gaigeshen.tripartite.his.procurement.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import work.gaigeshen.tripartite.his.procurement.openapi.HisProcurementClient;
import work.gaigeshen.tripartite.his.procurement.openapi.config.HisProcurementConfig;
import work.gaigeshen.tripartite.his.procurement.openapi.parameters.inputdata.HisProcurementDirectoryAddInputData;
import work.gaigeshen.tripartite.his.procurement.openapi.parameters.inputdata.HisProcurementDirectoryListInputData;
import work.gaigeshen.tripartite.his.procurement.openapi.response.HisProcurementDirectoryAddResponse;
import work.gaigeshen.tripartite.his.procurement.openapi.response.HisProcurementDirectoryListResponse;

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
    listItem1.pubonlnId = "xxx";
    inputData.list.add(listItem1);

    HisProcurementDirectoryAddInputData.ListItem listItem2 = new HisProcurementDirectoryAddInputData.ListItem();
    listItem2.pubonlnId = "yyy";
    inputData.list.add(listItem2);

    HisProcurementDirectoryAddResponse response = hisProcurementClient.addDirectory(inputData);
    Assertions.assertNotNull(response.dataList);
    Assertions.assertEquals(2, response.dataList.size());
  }
}
