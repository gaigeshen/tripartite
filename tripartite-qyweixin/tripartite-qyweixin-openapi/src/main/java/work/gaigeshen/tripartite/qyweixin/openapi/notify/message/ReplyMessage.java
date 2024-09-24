package work.gaigeshen.tripartite.qyweixin.openapi.notify.message;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 被动响应包的数据格式
 *
 * @author gaigeshen
 */
@JacksonXmlRootElement(localName = "xml")
@Accessors(chain = true)
@Data
public class ReplyMessage {

    /**
     * 消息签名
     */
    @JacksonXmlProperty(localName = "MsgSignature")
    private String MsgSignature;

    /**
     * 时间戳
     */
    @JacksonXmlProperty(localName = "TimeStamp")
    private String TimeStamp;

    /**
     * 随机值
     */
    @JacksonXmlProperty(localName = "Nonce")
    private String Nonce;

    /**
     * 经过加密的消息结构体
     */
    @JacksonXmlProperty(localName = "Encrypt")
    private String Encrypt;
}
