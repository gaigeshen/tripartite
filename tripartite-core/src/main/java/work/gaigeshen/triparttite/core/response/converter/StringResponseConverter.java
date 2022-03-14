package work.gaigeshen.triparttite.core.response.converter;

import work.gaigeshen.triparttite.core.header.Headers;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

/**
 * 响应结果数据转换器，用于转换为字符串
 *
 * @author gaigeshen
 */
public class StringResponseConverter implements ResponseConverter<String> {

  public static final StringResponseConverter INSTANCE = new StringResponseConverter();

  @Override
  public String convert(InputStream inputStream, Headers headers) throws ResponseConvertingException {
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    int len;
    byte[] buffer = new byte[4096];
    try {
      while ((len = inputStream.read(buffer)) != -1) {
        outputStream.write(buffer, 0, len);
      }
    } catch (IOException e) {
      throw new ResponseConvertingException("could not read bytes from input stream", e);
    }
    return outputStream.toString(StandardCharsets.UTF_8);
  }
}
