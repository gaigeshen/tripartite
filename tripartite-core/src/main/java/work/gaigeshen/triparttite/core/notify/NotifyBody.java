package work.gaigeshen.triparttite.core.notify;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

/**
 * 异步通知数据体，与异步通知参数同样都属于异步通知数据
 *
 * @author gaigeshen
 */
public interface NotifyBody extends NotifyContent {
  /**
   * 返回数据体字节串
   *
   * @return 数据体字节串不为空
   */
  byte[] getBody();

  /**
   * 将数据体转为指定字符集的字符串
   *
   * @param charset 字符集不能为空
   * @return 数据体字符串不为空
   */
  default String getBodyAsString(Charset charset) {
    if (Objects.isNull(charset)) {
      throw new IllegalArgumentException("charset cannot be null");
    }
    return new String(getBody(), charset);
  }

  /**
   * 将数据体转为默认字符集的字符串
   *
   * @return 数据体字符串不为空
   */
  default String getBodyAsString() {
    return getBodyAsString(StandardCharsets.UTF_8);
  }
}
