package work.gaigeshen.tripartite.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.http.converter.GenericHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.client.RequestCallback;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author gaigeshen
 */
class AcceptHeaderRequestCallback implements RequestCallback {

    private final Logger log = LoggerFactory.getLogger(AcceptHeaderRequestCallback.class);

    private final List<HttpMessageConverter<?>> httpMessageConverters;

    private final Type responseType;

    public AcceptHeaderRequestCallback(List<HttpMessageConverter<?>> httpMessageConverters, Type responseType) {
        this.httpMessageConverters = httpMessageConverters;
        this.responseType = responseType;
    }

  @Override
  public void doWithRequest(ClientHttpRequest request) throws IOException {
      if (Objects.nonNull(responseType)) {
          List<MediaType> supportedMediaTypes = httpMessageConverters.stream().filter(
                  converter -> canReadResponse(responseType, converter)).flatMap(
                          converter -> getSupportedMediaTypes(responseType, converter))
                  .distinct().sorted(MediaType.SPECIFICITY_COMPARATOR)
                  .collect(Collectors.toList());
          log.debug("Accept={}", supportedMediaTypes);
          request.getHeaders().setAccept(supportedMediaTypes);
      }
  }

    protected List<HttpMessageConverter<?>> getHttpMessageConverters() {
        return httpMessageConverters;
    }

    private boolean canReadResponse(Type responseType, HttpMessageConverter<?> converter) {
        Class<?> responseClass = (responseType instanceof Class ? (Class<?>) responseType : null);
        if (responseClass != null) {
            return converter.canRead(responseClass, null);
        }
        else if (converter instanceof GenericHttpMessageConverter) {
            GenericHttpMessageConverter<?> genericConverter = (GenericHttpMessageConverter<?>) converter;
            return genericConverter.canRead(responseType, null, null);
        }
        return false;
    }

    private Stream<MediaType> getSupportedMediaTypes(Type type, HttpMessageConverter<?> converter) {
        Type rawType = (type instanceof ParameterizedType ? ((ParameterizedType) type).getRawType() : type);
        Class<?> clazz = (rawType instanceof Class ? (Class<?>) rawType : null);
        List<MediaType> mediaTypes;
        if (Objects.isNull(clazz)) {
            mediaTypes = converter.getSupportedMediaTypes();
        } else {
            mediaTypes = converter.canRead(clazz, null) || converter.canWrite(clazz, null)
                    ? converter.getSupportedMediaTypes()
                    : Collections.emptyList();
        }
        return mediaTypes.stream().map(mediaType -> {
            if (Objects.nonNull(mediaType.getCharset())) {
                return new MediaType(mediaType.getType(), mediaType.getSubtype());
            }
            return mediaType;
        });
    }
}
