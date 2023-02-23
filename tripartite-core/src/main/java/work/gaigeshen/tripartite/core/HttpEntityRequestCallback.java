package work.gaigeshen.tripartite.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.http.converter.GenericHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.client.RestClientException;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author gaigeshen
 */
class HttpEntityRequestCallback extends AcceptHeaderRequestCallback {

    private final Logger log = LoggerFactory.getLogger(HttpEntityRequestCallback.class);

    private final HttpEntity<?> requestEntity;

    public HttpEntityRequestCallback(List<HttpMessageConverter<?>> httpMessageConverters, Object requestBody) {
        this(httpMessageConverters, requestBody, null);
    }

    public HttpEntityRequestCallback(List<HttpMessageConverter<?>> httpMessageConverters, Object requestBody, Type responseType) {
        super(httpMessageConverters, responseType);
        if (requestBody instanceof HttpEntity) {
            this.requestEntity = (HttpEntity<?>) requestBody;
        } else if (requestBody != null) {
            this.requestEntity = new HttpEntity<>(requestBody);
        } else {
            this.requestEntity = HttpEntity.EMPTY;
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public void doWithRequest(ClientHttpRequest httpRequest) throws IOException {
        super.doWithRequest(httpRequest);
        Object requestBody = requestEntity.getBody();
        if (requestBody == null) {
            HttpHeaders httpHeaders = httpRequest.getHeaders();
            HttpHeaders requestHeaders = requestEntity.getHeaders();
            if (!requestHeaders.isEmpty()) {
                requestHeaders.forEach((key, values) -> httpHeaders.put(key, new ArrayList<>(values)));
            }
            if (httpHeaders.getContentLength() < 0) {
                httpHeaders.setContentLength(0L);
            }
        } else {
            Class<?> requestBodyClass = requestBody.getClass();
            Type requestBodyType = (requestEntity instanceof RequestEntity
                    ? ((RequestEntity<?>) requestEntity).getType() : requestBodyClass);
            HttpHeaders httpHeaders = httpRequest.getHeaders();
            HttpHeaders requestHeaders = requestEntity.getHeaders();
            MediaType requestContentType = requestHeaders.getContentType();
            for (HttpMessageConverter<?> messageConverter : getHttpMessageConverters()) {
                if (messageConverter instanceof GenericHttpMessageConverter) {
                    GenericHttpMessageConverter<Object> genericConverter =
                            (GenericHttpMessageConverter<Object>) messageConverter;
                    if (genericConverter.canWrite(requestBodyType, requestBodyClass, requestContentType)) {
                        if (!requestHeaders.isEmpty()) {
                            requestHeaders.forEach((key, values) -> httpHeaders.put(key, new ArrayList<>(values)));
                        }
                        logBody(requestBody, requestContentType, genericConverter);
                        genericConverter.write(requestBody, requestBodyType, requestContentType, httpRequest);
                        return;
                    }
                } else if (messageConverter.canWrite(requestBodyClass, requestContentType)) {
                    if (!requestHeaders.isEmpty()) {
                        requestHeaders.forEach((key, values) -> httpHeaders.put(key, new ArrayList<>(values)));
                    }
                    logBody(requestBody, requestContentType, messageConverter);
                    ((HttpMessageConverter<Object>) messageConverter).write(
                            requestBody, requestContentType, httpRequest);
                    return;
                }
            }
            String message = "No HttpMessageConverter for " + requestBodyClass.getName();
            if (Objects.nonNull(requestContentType)) {
                message += " and content type \"" + requestContentType + "\"";
            }
            throw new RestClientException(message);
        }
    }

    private void logBody(Object body, MediaType mediaType, HttpMessageConverter<?> converter) {
        if (mediaType != null) {
            log.debug("Writing [" + body + "] as \"" + mediaType + "\"");
        } else {
            log.debug("Writing [" + body + "] with " + converter.getClass().getName());
        }
    }
}
