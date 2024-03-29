package work.gaigeshen.tripartite.core;

import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

/**
 *
 * @author gaigeshen
 */
public abstract class RestTemplateUtils {

    /**
     * 设置超时时间
     */
    public static void configureTimeout(RestTemplate restTemplate, Integer connectTimeout, Integer readTimeout) {
        if (Objects.isNull(restTemplate)) {
            throw new IllegalArgumentException("restTemplate cannot be null");
        }
        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
        if (Objects.nonNull(connectTimeout)) {
            requestFactory.setConnectTimeout(connectTimeout);
        }
        if (Objects.nonNull(readTimeout)) {
            requestFactory.setReadTimeout(readTimeout);
        }
        restTemplate.setRequestFactory(requestFactory);
    }
}
