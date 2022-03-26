package work.gaigeshen.tripartite.core.parameter.typed;

/**
 * 字节串类型的单个请求参数
 *
 * @author gaigeshen
 */
public class ByteArrayParameter extends AbstractParameter<byte[]> {

  public ByteArrayParameter(String name, byte[] value) {
    super(name, value);
  }
}
