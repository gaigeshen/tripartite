package work.gaigeshen.tripartite.core.interceptor;

import work.gaigeshen.tripartite.core.WebExecutionException;
import work.gaigeshen.tripartite.core.header.Headers;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.Objects;

/**
 * 拦截器可以用于修改请求头或者响应头或者响应内容数据
 *
 * @author gaigeshen
 */
public interface Interceptor {
    /**
     * 拦截方法，不要在此方法内创建新的响应内容，应该直接调用拦截器链的拦截方法获取响应内容实例
     *
     * @param request 请求内容不为空
     * @param chain 拦截器链不为空，调用拦截器的拦截方法将继续下个拦截器的调用
     * @return 响应内容不能为空
     * @throws InterceptingException 拦截处理的时候发生异常
     * @throws WebExecutionException 执行请求的时候发生异常
     */
    Response intercept(Request request, Chain chain) throws InterceptingException, WebExecutionException;

    /**
     * 拦截器链
     *
     * @author gaigeshen
     */
    interface Chain {
        /**
         * 调用此方法将继续下个拦截器
         *
         * @param request 请求内容不能为空，请直接使用拦截器传递的实例
         * @return 响应内容不会为空
         * @throws InterceptingException 拦截处理的时候发生异常
         * @throws WebExecutionException 执行请求的时候发生异常
         */
        Response intercept(Request request) throws InterceptingException, WebExecutionException;
    }

    /**
     * 请求内容
     *
     * @author gaigeshen
     */
    interface Request {
        /**
         * 请求地址不会为空
         *
         * @return 请求地址
         */
        String url();

        /**
         * 请求方式不会为空
         *
         * @return 请求方式
         */
        String method();

        /**
         * 请求头不会为空
         *
         * @return 请求头
         */
        Headers headers();

        /**
         * 请求内容数据不会为空
         *
         * @return 请求内容数据
         */
        byte[] bodyBytes();
    }

    /**
     * 响应内容
     *
     * @author gaigeshen
     */
    interface Response {
        /**
         * 响应头不会为空
         *
         * @return 响应头
         */
        Headers headers();

        /**
         * 调用此缓冲方法将更改响应内容数据，传入空对象不生效
         *
         * @param bodyBytes 响应内容数据将会变更为此数据且可重复读取
         */
        void buffered(byte[] bodyBytes);

        /**
         * 返回响应内容数据流
         *
         * @return 响应内容数据流不会为空
         * @throws IOException 发生异常
         */
        InputStream bodyStream() throws IOException;

        /**
         * 返回响应内容数据字节串
         *
         * @return 响应内容数据字节串不会为空
         * @throws IOException 发生异常
         */
        default byte[] bodyBytes() throws IOException {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            InputStream in = bodyStream();
            int len;
            byte[] buffer = new byte[4096];
            while ((len = in.read(buffer)) != -1) {
                out.write(buffer, 0, len);
            }
            return out.toByteArray();
        }

        /**
         * 返回响应内容数据字符串
         *
         * @param charset 字符集不能为空
         * @return 响应内容数据字符串不会为空
         * @throws IOException 发生异常
         */
        default String bodyString(Charset charset) throws IOException {
            if (Objects.isNull(charset)) {
                throw new IllegalArgumentException("charset cannot be null");
            }
            return new String(bodyBytes(), charset);
        }
    }
}
