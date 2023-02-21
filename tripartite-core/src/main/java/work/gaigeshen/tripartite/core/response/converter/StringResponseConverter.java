package work.gaigeshen.tripartite.core.response.converter;

import work.gaigeshen.tripartite.core.header.Headers;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

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
        try {
            return outputStream.toString("utf-8");
        } catch (UnsupportedEncodingException e) {
            throw new ResponseConvertingException("encoding invalid", e);
        }
    }
}
