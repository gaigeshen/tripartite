package work.gaigeshen.tripartite.core.response.consumer;

import work.gaigeshen.tripartite.core.header.Headers;
import work.gaigeshen.tripartite.core.util.ArgumentValidate;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * 响应结果数据消费器
 *
 * @author gaigeshen
 */
public class OutputStreamResponseConsumer implements ResponseConsumer {

    private final OutputStream outputStream;

    public OutputStreamResponseConsumer(OutputStream outputStream) {
        ArgumentValidate.notNull(outputStream, "outputStream cannot be null");
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
