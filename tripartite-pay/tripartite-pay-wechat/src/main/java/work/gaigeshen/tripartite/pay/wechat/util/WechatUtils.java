package work.gaigeshen.tripartite.pay.wechat.util;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import work.gaigeshen.tripartite.core.util.TimestampUtils;
import work.gaigeshen.tripartite.pay.wechat.config.WechatConfig;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * 微信工具类
 *
 * @author gaigeshen
 */
public abstract class WechatUtils {

    private WechatUtils() { }

    /**
     * 创建调起小程序支付需要的参数
     *
     * @param config 微信支付配置
     * @param prepayId 预支付交易会话标识
     * @return 调起小程序支付需要的参数
     */
    public static Map<String, String> createJsapiPaymentObject(WechatConfig config, String prepayId) {
        if (Objects.isNull(config) || StringUtils.isBlank(prepayId)) {
            throw new IllegalArgumentException("wechat config and prepay id is required");
        }
        String randomText = RandomStringUtils.randomAlphanumeric(32);
        String timestamp = TimestampUtils.unixTimestamp();

        String signContent = config.getAppId() + "\n" + timestamp + "\n" + randomText + "\n" + "prepay_id=" + prepayId + "\n";

        String signResult = config.getPrivateKey().sign(signContent.getBytes(StandardCharsets.UTF_8));

        Map<String, String> paymentObject = new HashMap<>();
        paymentObject.put("appId", config.getAppId());
        paymentObject.put("partnerId", config.getMerchantId());
        paymentObject.put("timeStamp", timestamp);
        paymentObject.put("nonceStr", randomText);
        paymentObject.put("package", "prepay_id=" + prepayId);
        paymentObject.put("signType", "RSA");
        paymentObject.put("paySign", signResult);
        return paymentObject;
    }
}
