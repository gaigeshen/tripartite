package work.gaigeshen.tripartite.core.client.config;

/**
 * 配置信息
 *
 * @author gaigeshen
 */
public interface Config {

    /**
     * 返回服务器的地址字符串，该地址应该包含协议、域名和端口信息
     *
     * @return 服务器的地址字符串
     */
    String getServerHost();

}
