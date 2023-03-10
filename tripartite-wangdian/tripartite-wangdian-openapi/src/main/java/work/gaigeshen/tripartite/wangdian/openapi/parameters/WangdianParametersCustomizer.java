package work.gaigeshen.tripartite.wangdian.openapi.parameters;

import org.apache.commons.codec.digest.DigestUtils;
import work.gaigeshen.tripartite.core.parameter.Parameter;
import work.gaigeshen.tripartite.core.parameter.Parameters;
import work.gaigeshen.tripartite.core.parameter.converter.ParametersCustomizer;
import work.gaigeshen.tripartite.core.parameter.converter.ParametersCustomizingException;
import work.gaigeshen.tripartite.core.util.TimestampUtils;
import work.gaigeshen.tripartite.wangdian.openapi.parameters.refund.SalesRefundPushParameters;
import work.gaigeshen.tripartite.wangdian.openapi.parameters.trade.TradePushParameters;
import work.gaigeshen.tripartite.wangdian.openapi.config.WangdianConfig;

import java.util.Objects;

/**
 * 旺店通请求参数对象自定义器用于添加公共请求参数
 *
 * @author gaigeshen
 */
public class WangdianParametersCustomizer implements ParametersCustomizer {

    @Override
    public void beforeConvert(Object rawParameters, Object config) throws ParametersCustomizingException {
        if (rawParameters instanceof TradePushParameters) {
            TradePushParameters tradePushParameters = (TradePushParameters) rawParameters;
            if (Objects.isNull(tradePushParameters.shop_no)) {
                tradePushParameters.shop_no = ((WangdianConfig) config).getShopNo();
            }
        }
        if (rawParameters instanceof SalesRefundPushParameters) {
            SalesRefundPushParameters salesRefundPushParameters = (SalesRefundPushParameters) rawParameters;
            if (Objects.nonNull(salesRefundPushParameters.api_refund_list)) {
                for (SalesRefundPushParameters.Refund refund : salesRefundPushParameters.api_refund_list) {
                    if (Objects.isNull(refund.shop_no)) {
                        refund.shop_no = ((WangdianConfig) config).getShopNo();
                    }
                    if (Objects.isNull(refund.platform_id)) {
                        refund.platform_id = 127;
                    }
                }
            }
        }
    }

    @Override
    public void customize(Parameters parameters, Object rawParameters, Object config) throws ParametersCustomizingException {
        WangdianConfig wangdianConfig = (WangdianConfig) config;
        parameters.put("sid", wangdianConfig.getSellerId());
        parameters.put("appkey", wangdianConfig.getAppKey());
        parameters.put("timestamp", TimestampUtils.unixTimestamp());

        StringBuilder builder = new StringBuilder();
        for (Parameter<?> newParameter : parameters) {
            String name = newParameter.getName();
            String value = String.valueOf(newParameter.getValue());
            builder.append(";").append(String.format("%02d", name.length())).append('-').append(name);
            builder.append(':').append(String.format("%04d", value.length())).append('-').append(value);
        }
        builder.append(wangdianConfig.getAppSecret());

        parameters.put("sign", DigestUtils.md5Hex(builder.substring(1)));
    }

    @Override
    public boolean supports(Object config) {
        return config instanceof WangdianConfig;
    }
}
