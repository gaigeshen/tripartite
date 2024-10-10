package work.gaigeshen.tripartite.core;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.MediaType;
import org.springframework.http.client.*;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.*;
import work.gaigeshen.tripartite.core.header.DefaultHeaders;
import work.gaigeshen.tripartite.core.header.Headers;
import work.gaigeshen.tripartite.core.interceptor.*;
import work.gaigeshen.tripartite.core.parameter.Parameter;
import work.gaigeshen.tripartite.core.parameter.Parameters;
import work.gaigeshen.tripartite.core.parameter.converter.ParametersConversionException;
import work.gaigeshen.tripartite.core.parameter.converter.ParametersConverter;
import work.gaigeshen.tripartite.core.parameter.creator.ParametersCreator;
import work.gaigeshen.tripartite.core.response.consumer.ResponseConsumer;
import work.gaigeshen.tripartite.core.response.converter.ResponseConverter;
import work.gaigeshen.tripartite.core.util.ArgumentValidate;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

import static org.springframework.http.HttpMethod.*;
import static org.springframework.http.MediaType.*;

/**
 * @author gaigeshen
 */
public class RestTemplateWebExecutor implements WebExecutor {

    private static final Logger log = LoggerFactory.getLogger(RestTemplateWebExecutor.class);

    private final RestTemplate restTemplate;

    private ParametersConverter parametersConverter;

    protected RestTemplateWebExecutor(RestTemplate template, boolean disableSslValidation) {
        ArgumentValidate.notNull(template, "restTemplate cannot be null");
        // allows for multiple reads of the response body
        template.setRequestFactory(new BufferingClientHttpRequestFactory(template.getRequestFactory()));
        // log requests and responses
        template.getInterceptors().add(0, new LoggerInterceptor());
        this.restTemplate = template;
        if (disableSslValidation) {
            HttpsURLConnectionConfigurer.defaultDisableSSLValidation();
        }
    }

    public static RestTemplateWebExecutor create(RestTemplate template, boolean disableSslValidation) {
        return new RestTemplateWebExecutor(template, disableSslValidation);
    }

    public static RestTemplateWebExecutor create(RestTemplate template) {
        return new RestTemplateWebExecutor(template, true);
    }

    public static RestTemplateWebExecutor create() {
        return new RestTemplateWebExecutor(new RestTemplate(), true);
    }

    public void setParametersConverter(ParametersConverter converter) {
        ArgumentValidate.notNull(converter, "converter cannot be null");
        this.parametersConverter = converter;
    }

    public void setInterceptors(Interceptor... interceptors) {
        ArgumentValidate.notNull(interceptors, "interceptors cannot be null");
        if (interceptors.length > 0) {
            Interceptors newInterceptors = new Interceptors(interceptors);
            restTemplate.getInterceptors().add(new HttpInterceptorInterceptors(newInterceptors));
        }
    }

    @Override
    public ParametersConverter getParametersConverter() {
        return parametersConverter;
    }

    @Override
    public <T> T execute(String url, Class<T> responseClass, Object... uriVariables) throws WebException {
        return executeInternal(url, createResponseExtractor(responseClass), uriVariables);
    }

    @Override
    public <T> T execute(String url, ResponseConverter<T> converter, Object... uriVariables) throws WebException {
        return executeInternal(url, createResponseExtractor(converter), uriVariables);
    }

    @Override
    public void execute(String url, ResponseConsumer consumer, Object... uriVariables) throws WebException {
        executeInternal(url, createResponseExtractor(consumer), uriVariables);
    }

    @Override
    public <T> T execute(String url, Parameters parameters, Class<T> responseClass, Object... uriVariables) throws WebException {
        return executeInternal(url, parameters, createResponseExtractor(responseClass), uriVariables);
    }

    @Override
    public <T> T execute(String url, Parameters parameters, ResponseConverter<T> converter, Object... uriVariables) throws WebException {
        return executeInternal(url, parameters, createResponseExtractor(converter), uriVariables);
    }

    @Override
    public void execute(String url, Parameters parameters, ResponseConsumer consumer, Object... uriVariables) throws WebException {
        executeInternal(url, parameters, createResponseExtractor(consumer), uriVariables);
    }

    @Override
    public <T> T executePut(String url, Parameters parameters, Class<T> responseClass, Object... uriVariables) throws WebException {
        return executeInternalPut(url, parameters, createResponseExtractor(responseClass), uriVariables);
    }

    @Override
    public <T> T executePut(String url, Parameters parameters, ResponseConverter<T> converter, Object... uriVariables) throws WebException {
        return executeInternalPut(url, parameters, createResponseExtractor(converter), uriVariables);
    }

    @Override
    public void executePut(String url, Parameters parameters, ResponseConsumer consumer, Object... uriVariables) throws WebException {
        executeInternalPut(url, parameters, createResponseExtractor(consumer), uriVariables);
    }

    @Override
    public <T> T executeDelete(String url, Parameters parameters, Class<T> responseClass, Object... uriVariables) throws WebException {
        return executeInternalDelete(url, parameters, createResponseExtractor(responseClass), uriVariables);
    }

    @Override
    public <T> T executeDelete(String url, Parameters parameters, ResponseConverter<T> converter, Object... uriVariables) throws WebException {
        return executeInternalDelete(url, parameters, createResponseExtractor(converter), uriVariables);
    }

    @Override
    public void executeDelete(String url, Parameters parameters, ResponseConsumer consumer, Object... uriVariables) throws WebException {
        executeInternalDelete(url, parameters, createResponseExtractor(consumer), uriVariables);
    }

    @Override
    public <T> T execute(String url, Object parameters, Class<T> responseClass, Object... uriVariables) throws WebException {
        return execute(url, convertParameters(parameters), responseClass, uriVariables);
    }

    @Override
    public <T> T execute(String url, Object parameters, ResponseConverter<T> converter, Object... uriVariables) throws WebException {
        return execute(url, convertParameters(parameters), converter, uriVariables);
    }

    @Override
    public void execute(String url, Object parameters, ResponseConsumer consumer, Object... uriVariables) throws WebException {
        execute(url, convertParameters(parameters), consumer, uriVariables);
    }

    @Override
    public <T> T executePut(String url, Object parameters, Class<T> responseClass, Object... uriVariables) throws WebException {
        return executePut(url, convertParameters(parameters), responseClass, uriVariables);
    }

    @Override
    public <T> T executePut(String url, Object parameters, ResponseConverter<T> converter, Object... uriVariables) throws WebException {
        return executePut(url, convertParameters(parameters), converter, uriVariables);
    }

    @Override
    public void executePut(String url, Object parameters, ResponseConsumer consumer, Object... uriVariables) throws WebException {
        executePut(url, convertParameters(parameters), consumer, uriVariables);
    }

    @Override
    public <T> T executeDelete(String url, Object parameters, Class<T> responseClass, Object... uriVariables) throws WebException {
        return executeDelete(url, convertParameters(parameters), responseClass, uriVariables);
    }

    @Override
    public <T> T executeDelete(String url, Object parameters, ResponseConverter<T> converter, Object... uriVariables) throws WebException {
        return executeDelete(url, convertParameters(parameters), converter, uriVariables);
    }

    @Override
    public void executeDelete(String url, Object parameters, ResponseConsumer consumer, Object... uriVariables) throws WebException {
        executeDelete(url, convertParameters(parameters), consumer, uriVariables);
    }

    @Override
    public <T> T execute(String url, ParametersCreator creator, Class<T> responseClass, Object... uriVariables) throws WebException {
        ArgumentValidate.notNull(creator, "creator cannot be null");
        return execute(url, creator.create(), responseClass, uriVariables);
    }

    @Override
    public <T> T execute(String url, ParametersCreator creator, ResponseConverter<T> converter, Object... uriVariables) throws WebException {
        ArgumentValidate.notNull(creator, "creator cannot be null");
        return execute(url, creator.create(), converter, uriVariables);
    }

    @Override
    public void execute(String url, ParametersCreator creator, ResponseConsumer consumer, Object... uriVariables) throws WebException {
        ArgumentValidate.notNull(creator, "creator cannot be null");
        execute(url, creator.create(), consumer, uriVariables);
    }

    @Override
    public <T> T executePut(String url, ParametersCreator creator, Class<T> responseClass, Object... uriVariables) throws WebException {
        ArgumentValidate.notNull(creator, "creator cannot be null");
        return executePut(url, creator.create(), responseClass, uriVariables);
    }

    @Override
    public <T> T executePut(String url, ParametersCreator creator, ResponseConverter<T> converter, Object... uriVariables) throws WebException {
        ArgumentValidate.notNull(creator, "creator cannot be null");
        return executePut(url, creator.create(), converter, uriVariables);
    }

    @Override
    public void executePut(String url, ParametersCreator creator, ResponseConsumer consumer, Object... uriVariables) throws WebException {
        ArgumentValidate.notNull(creator, "creator cannot be null");
        executePut(url, creator.create(), consumer, uriVariables);
    }

    @Override
    public <T> T executeDelete(String url, ParametersCreator creator, Class<T> responseClass, Object... uriVariables) throws WebException {
        ArgumentValidate.notNull(creator, "creator cannot be null");
        return executeDelete(url, creator.create(), responseClass, uriVariables);
    }

    @Override
    public <T> T executeDelete(String url, ParametersCreator creator, ResponseConverter<T> converter, Object... uriVariables) throws WebException {
        ArgumentValidate.notNull(creator, "creator cannot be null");
        return executeDelete(url, creator.create(), converter, uriVariables);
    }

    @Override
    public void executeDelete(String url, ParametersCreator creator, ResponseConsumer consumer, Object... uriVariables) throws WebException {
        ArgumentValidate.notNull(creator, "creator cannot be null");
        executeDelete(url, creator.create(), consumer, uriVariables);
    }

    private <T> T executeInternal(String url, ResponseExtractor<T> extractor, Object... uriVariables) throws WebExecutionException {
        ArgumentValidate.notBlank(url, "url cannot be blank");
        try {
            return restTemplate.execute(url, GET, wrapParametersRequestCallback(null), extractor, uriVariables);
        } catch (RestClientException e) {
            throw new WebExecutionException(e.getMessage(), e);
        }
    }

    private <T> T executeInternal(String url, Parameters parameters, ResponseExtractor<T> extractor, Object... uriVariables) throws WebExecutionException {
        ArgumentValidate.notBlank(url, "url cannot be blank");
        ArgumentValidate.notNull(parameters, "parameters cannot be null");
        try {
            return restTemplate.execute(url, POST, wrapParametersRequestCallback(parameters), extractor, uriVariables);
        } catch (RestClientException e) {
            throw new WebExecutionException(e.getMessage(), e);
        }
    }

    private <T> T executeInternalPut(String url, Parameters parameters, ResponseExtractor<T> extractor, Object... uriVariables) throws WebExecutionException {
        ArgumentValidate.notBlank(url, "url cannot be blank");
        ArgumentValidate.notNull(parameters, "parameters cannot be null");
        try {
            return restTemplate.execute(url, PUT, wrapParametersRequestCallback(parameters), extractor, uriVariables);
        } catch (RestClientException e) {
            throw new WebExecutionException(e.getMessage(), e);
        }
    }

    private <T> T executeInternalDelete(String url, Parameters parameters, ResponseExtractor<T> extractor, Object... uriVariables) throws WebExecutionException {
        ArgumentValidate.notBlank(url, "url cannot be blank");
        ArgumentValidate.notNull(parameters, "parameters cannot be null");
        try {
            return restTemplate.execute(url, DELETE, wrapParametersRequestCallback(parameters), extractor, uriVariables);
        } catch (RestClientException e) {
            throw new WebExecutionException(e.getMessage(), e);
        }
    }

    private Parameters convertParameters(Object parameters) throws ParametersConversionException {
        ArgumentValidate.notNull(parameters, "parameters cannot be null");
        if (Objects.isNull(parametersConverter)) {
            throw new ParametersConversionException("missing parameters converter");
        }
        return parametersConverter.convert(parameters);
    }

    private <T> ResponseExtractor<T> createResponseExtractor(Class<T> responseClass) {
        ArgumentValidate.notNull(responseClass, "responseClass cannot be null");
        return new HttpMessageConverterExtractor<>(responseClass, restTemplate.getMessageConverters());
    }

    private <R> ResponseExtractor<R> createResponseExtractor(ResponseConverter<R> converter) {
        ArgumentValidate.notNull(converter, "converter cannot be null");
        return new ResponseConverterExtractor<>(converter);
    }

    private ResponseExtractor<Void> createResponseExtractor(ResponseConsumer consumer) {
        ArgumentValidate.notNull(consumer, "consumer cannot be null");
        return new ResponseConsumerExtractor(consumer);
    }

    private RequestCallback wrapParametersRequestCallback(Parameters parameters) {
        List<HttpMessageConverter<?>> messageConverters = restTemplate.getMessageConverters();
        if (Objects.isNull(parameters)) {
            return new HttpEntityRequestCallback(messageConverters, null);
        }
        HttpHeaders hHeaders = new HttpHeaders();
        if (parameters.getType() == Parameters.Type.JSON) {
            hHeaders.setContentType(APPLICATION_JSON);
            return new HttpEntityRequestCallback(messageConverters, new HttpEntity<>(extractParameters(parameters), hHeaders));
        } else {
            if (parameters.getType() == Parameters.Type.PARAMETERS) {
                hHeaders.setContentType(APPLICATION_FORM_URLENCODED);
            }
            else if (parameters.getType() == Parameters.Type.MULTIPART_PARAMETERS) {
                hHeaders.setContentType(MULTIPART_FORM_DATA);
            }
            MultiValueMap<String, Object> converted = new LinkedMultiValueMap<>();
            for (Parameter<?> parameter : parameters) {
                if (!(parameter instanceof Parameters)) {
                    converted.put(parameter.getName(), Collections.singletonList(parameter.getValue()));
                }
            }
            return new HttpEntityRequestCallback(messageConverters, new HttpEntity<>(converted, hHeaders));
        }
    }

    private Map<String, Object> extractParameters(Parameters parameters) {
        if (Objects.isNull(parameters)) {
            return null;
        }
        Map<String, Object> extracted = new HashMap<>();
        for (Parameter<?> parameter : parameters) {
            // ParametersParameter
            if (parameter.getValue() instanceof Parameters) {
                extracted.put(parameter.getName(), extractParameters((Parameters) parameter.getValue()));
            }
            // CollectionParameter
            else if (parameter.getValue() instanceof Collection) {
                Collection<Object> theValue = new ArrayList<>();
                for (Object item : ((Collection<?>) parameter.getValue())) {
                    if (item instanceof Boolean || item instanceof String || item instanceof Number || item instanceof byte[]) {
                        theValue.add(item);
                        continue;
                    }
                    theValue.add(extractParameters((Parameters) item));
                }
                extracted.put(parameter.getName(), theValue);
            }
            // other Parameter
            else {
                extracted.put(parameter.getName(), parameter.getValue());
            }
        }
        return extracted;
    }

    /**
     * @author gaigeshen
     */
    private static class ResponseConverterExtractor<R> implements ResponseExtractor<R> {

        private final ResponseConverter<R> converter;

        private ResponseConverterExtractor(ResponseConverter<R> converter) {
            ArgumentValidate.notNull(converter, "converter cannot be null");
            this.converter = converter;
        }

        @Override
        public R extractData(ClientHttpResponse response) throws IOException {
            Headers headers = new DefaultHeaders();
            response.getHeaders().forEach(headers::addValues);
            return converter.convert(response.getBody(), headers);
        }
    }

    /**
     * @author gaigeshen
     */
    private static class ResponseConsumerExtractor implements ResponseExtractor<Void> {

        private final ResponseConsumer consumer;

        private ResponseConsumerExtractor(ResponseConsumer consumer) {
            ArgumentValidate.notNull(consumer, "consumer cannot be null");
            this.consumer = consumer;
        }

        @Override
        public Void extractData(ClientHttpResponse response) throws IOException {
            Headers headers = new DefaultHeaders();
            response.getHeaders().forEach(headers::addValues);
            consumer.consume(response.getBody(), headers);
            return null;
        }
    }

    /**
     * @author gaigeshen
     */
    private static class HttpInterceptorInterceptors implements ClientHttpRequestInterceptor {

        private final Interceptors interceptors;

        private HttpInterceptorInterceptors(Interceptors interceptors) {
            ArgumentValidate.notNull(interceptors, "interceptors cannot be null");
            this.interceptors = interceptors;
        }

        @Override
        public ClientHttpResponse intercept(HttpRequest httpRequest, byte[] body, ClientHttpRequestExecution execution) throws IOException {
            if (!interceptors.hasInterceptors()) {
                return execution.execute(httpRequest, body);
            }
            HttpRequestRequest request = new HttpRequestRequest(httpRequest, body);
            Interceptor.Response response = interceptors.intercept(request, new HttpRequestExecution(execution));
            if (Objects.isNull(response)) {
                throw new WebExecutionException("response from interceptor cannot be null");
            }
            if (response instanceof HttpResponseResponse) {
                return (HttpResponseResponse) response;
            }
            throw new WebExecutionException("please don't produce response instance by interceptor");
        }
    }

    /**
     * @author gaigeshen
     */
    private static class LoggerInterceptor implements ClientHttpRequestInterceptor {

        private static final Collection<MediaType> LOGGABLE_MEDIA_TYPE = Arrays.asList(APPLICATION_JSON, TEXT_PLAIN, TEXT_XML, TEXT_HTML);

        @Override
        public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
            if (!log.isDebugEnabled()) {
                return execution.execute(request, body);
            }
            log.debug(">>>> URI: [{}] {}", request.getMethod(), request.getURI());
            log.debug(">>>> Headers: {}", request.getHeaders());

            MediaType bodyType = request.getHeaders().getContentType();
            if (isLoggableContent(bodyType)) {
                log.debug(">>>> Body: {}", new String(body, StandardCharsets.UTF_8));
            }

            ClientHttpResponse response = execution.execute(request, body);

            log.debug("<<<< Status: {}", response.getStatusCode());
            log.debug("<<<< Headers: {}", response.getHeaders());

            MediaType responseType = response.getHeaders().getContentType();
            if (Objects.isNull(responseType)) {
                return response;
            }
            if (isLoggableContent(responseType)) {
                log.debug("<<<< Body: {}", getResponseBody(response));
            }
            return response;
        }

        private String getResponseBody(ClientHttpResponse httpResponse) throws IOException {
            List<String> responseBodyLines = IOUtils.readLines(httpResponse.getBody(), StandardCharsets.UTF_8);
            return responseBodyLines.stream().map(StringUtils::trim).collect(Collectors.joining(""));
        }

        private boolean isLoggableContent(MediaType contentType) {
            for (MediaType loggableMediaType : LOGGABLE_MEDIA_TYPE) {
                if (loggableMediaType.includes(contentType)) {
                    return true;
                }
            }
            return false;
        }
    }

    /**
     * @author gaigeshen
     */
    private static class HttpRequestExecution implements Execution {

        private final ClientHttpRequestExecution execution;

        private HttpRequestExecution(ClientHttpRequestExecution execution) {
            ArgumentValidate.notNull(execution, "http request execution cannot be null");
            this.execution = execution;
        }

        @Override
        public Interceptor.Response execute(Interceptor.Request request) throws WebExecutionException {
            ArgumentValidate.notTrue(!(request instanceof HttpRequestRequest), "can only support HttpRequestRequest");
            HttpRequest httpRequest = ((HttpRequestRequest) request).httpRequest;
            try {
                ClientHttpResponse httpResponse = execution.execute(httpRequest, request.bodyBytes());
                return new HttpResponseResponse(httpResponse);
            } catch (IOException e) {
                throw new WebExecutionException("could not execute: " + request, e);
            }
        }
    }

    /**
     * @author gaigeshen
     */
    private static class HttpRequestRequest implements Interceptor.Request {

        private final HttpRequest httpRequest;

        private final byte[] bodyBytes;

        private HttpRequestRequest(HttpRequest httpRequest, byte[] bodyBytes) {
            ArgumentValidate.notNull(httpRequest, "httpRequest cannot be null");
            ArgumentValidate.notNull(bodyBytes, "bodyBytes cannot be null");
            this.httpRequest = httpRequest;
            this.bodyBytes = bodyBytes;
        }

        @Override
        public String url() {
            return httpRequest.getURI().toString();
        }

        @Override
        public String method() {
            return httpRequest.getMethod().name();
        }

        @Override
        public Headers headers() {
            return new HttpHeadersHeaders(httpRequest.getHeaders());
        }

        @Override
        public byte[] bodyBytes() {
            return bodyBytes;
        }

        @Override
        public String toString() {
            return httpRequest.toString();
        }
    }

    /**
     * @author gaigeshen
     */
    private static class HttpResponseResponse extends AbstractClientHttpResponse implements Interceptor.Response {

        private final ClientHttpResponse httpResponse;

        private byte[] buffer;

        private HttpResponseResponse(ClientHttpResponse httpResponse) {
            ArgumentValidate.notNull(httpResponse, "httpResponse cannot be null");
            this.httpResponse = httpResponse;
        }

        @Override
        public Headers headers() {
            return new HttpHeadersHeaders(httpResponse.getHeaders());
        }

        @Override
        public void changeBody(byte[] bodyBytes) {
            buffer = bodyBytes;
        }

        @Override
        public InputStream bodyStream() throws IOException {
            if (Objects.isNull(buffer)) {
                return httpResponse.getBody();
            }
            return new ByteArrayInputStream(buffer);
        }

        @Override
        public int getRawStatusCode() throws IOException {
            return httpResponse.getRawStatusCode();
        }

        @Override
        public String getStatusText() throws IOException {
            return httpResponse.getStatusText();
        }

        @Override
        public void close() {
            httpResponse.close();
        }

        @Override
        public InputStream getBody() throws IOException {
            if (Objects.isNull(buffer)) {
                return httpResponse.getBody();
            }
            return new ByteArrayInputStream(buffer);
        }

        @Override
        public HttpHeaders getHeaders() {
            return httpResponse.getHeaders();
        }

        @Override
        public String toString() {
            return getClass() + " include " + httpResponse;
        }
    }

    /**
     * @author gaigeshen
     */
    private static class HttpHeadersHeaders implements Headers {

        private final HttpHeaders httpHeaders;

        private HttpHeadersHeaders(HttpHeaders httpHeaders) {
            ArgumentValidate.notNull(httpHeaders, "httpHeaders cannot be null");
            this.httpHeaders = httpHeaders;
        }

        @Override
        public void addValue(String name, String value) {
            addValues(name, Collections.singletonList(value));
        }

        @Override
        public void addValues(String name, List<String> values) {
            for (String value : values) {
                httpHeaders.add(name, value);
            }
        }

        @Override
        public void putValue(String name, String value) {
            putValues(name, Collections.singletonList(value));
        }

        @Override
        public void putValues(String name, List<String> values) {
            httpHeaders.put(name, values);
        }

        @Override
        public List<String> getValues(String name) {
            return httpHeaders.get(name);
        }

        @Override
        public String getValue(String name) {
            return httpHeaders.getFirst(name);
        }

        @Override
        public boolean contains(String name) {
            return httpHeaders.containsKey(name);
        }

        @Override
        public void remove(String name) {
            httpHeaders.remove(name);
        }

        @Override
        public String toString() {
            return httpHeaders.toString();
        }
    }
}
