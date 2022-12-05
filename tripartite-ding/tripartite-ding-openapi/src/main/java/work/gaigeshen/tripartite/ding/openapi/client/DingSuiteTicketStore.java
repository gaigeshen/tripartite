package work.gaigeshen.tripartite.ding.openapi.client;

/**
 * 钉钉套件票据存储器
 *
 * @author gaigeshen
 */
public interface DingSuiteTicketStore {

    /**
     * 创建默认的套件票据存储器
     *
     * @return 默认的套件票据存储器
     */
    static DingSuiteTicketStore create() {
        return new DefaultDingSuiteTicketStore();
    }

    /**
     * 设置套件票据
     *
     * @param suiteId 套件编号
     * @param ticket 套件票据
     */
    void setTicket(String suiteId, String ticket);

    /**
     * 获取套件票据
     *
     * @param suiteId 套件编号
     * @return 套件票据可能为空
     */
    String getTicket(String suiteId);
}
