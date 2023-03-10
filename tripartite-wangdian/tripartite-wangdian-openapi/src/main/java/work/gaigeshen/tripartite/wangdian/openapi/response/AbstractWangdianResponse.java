package work.gaigeshen.tripartite.wangdian.openapi.response;

/**
 * 抽象的旺店通响应结果，包含公共的响应数据
 *
 * @author gaigeshen
 */
public abstract class AbstractWangdianResponse implements WangdianResponse {

    public Integer code;

    public String message;
}
