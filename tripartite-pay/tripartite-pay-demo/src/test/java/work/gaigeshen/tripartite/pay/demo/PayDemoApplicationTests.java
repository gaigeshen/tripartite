package work.gaigeshen.tripartite.pay.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import work.gaigeshen.tripartite.pay.alipay.AlipayClients;
import work.gaigeshen.tripartite.pay.wechat.WechatClients;

/**
 *
 * @author gaigeshen
 */
@SpringBootTest
public class PayDemoApplicationTests {

  @Autowired
  private AlipayClients alipayClients;

  @Autowired
  private WechatClients wechatClients;
}
