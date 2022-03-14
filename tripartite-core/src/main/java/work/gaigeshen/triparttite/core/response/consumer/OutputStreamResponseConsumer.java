package work.gaigeshen.triparttite.core.response.consumer;

import work.gaigeshen.triparttite.core.header.Headers;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Objects;

/**
 * 响应结果数据消费器
 *
 * @author gaigeshen
 */
public class OutputStreamResponseConsumer implements ResponseConsumer {

  private final OutputStream outputStream;

  public OutputStreamResponseConsumer(OutputStream outputStream) {
    if (Objects.isNull(outputStream)) {
      throw new IllegalArgumentException("output stream cannot be null");
    }
    this.outputStream = outputStream;
  }

  @Override
  public void consume(InputStream inputStream, Headers headers) throws ResponseConsumingException {
    int len;
    byte[] buffer = new byte[4096];
    try {
      while ((len = inputStream.read(buffer)) != -1) {
        outputStream.write(buffer, 0, len);
      }
    } catch (IOException e) {
      throw new ResponseConsumingException("could not consume to output stream", e);
    }
  }
}
