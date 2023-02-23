package work.gaigeshen.tripartite.core.parameter.typed;

/**
 * 字节串类型的单个请求参数
 *
 * @author gaigeshen
 */
public class StringParameter extends AbstractParameter<String> {

    public StringParameter(String name, String value) {
        super(name, value);
    }
}
