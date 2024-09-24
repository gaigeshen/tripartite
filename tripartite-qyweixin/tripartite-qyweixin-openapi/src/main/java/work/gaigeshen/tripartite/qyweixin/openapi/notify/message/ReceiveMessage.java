package work.gaigeshen.tripartite.qyweixin.openapi.notify.message;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 接收到的业务数据格式
 *
 * @author gaigeshen
 */
@JacksonXmlRootElement(localName = "xml")
@Accessors(chain = true)
@Data
public class ReceiveMessage {

    /**
     * 企业标识或者代开发应用模板标识
     */
    @JacksonXmlProperty(localName = "ToUserName")
    private String ToUserName;

    /**
     * 应用标识可能为空
     */
    @JacksonXmlProperty(localName = "AgentID")
    private String AgentID;

    /**
     * 消息结构体加密后的字符串
     */
    @JacksonXmlProperty(localName = "Encrypt")
    private String Encrypt;
}
