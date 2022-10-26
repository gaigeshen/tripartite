package work.gaigeshen.tripartite.core.client.config;

/**
 * 此接口用于从配置信息中解析特定的内容
 *
 * @author gaigeshen
 */
public interface ConfigResolver<C extends Config, R> {

    /**
     * 从配置信息中解析特定的内容
     *
     * @param config 配置信息
     * @return 解析的特定内容
     * @throws ConfigException 无法解析抛出配置信息异常
     */
    R resolve(C config) throws ConfigException;
}
