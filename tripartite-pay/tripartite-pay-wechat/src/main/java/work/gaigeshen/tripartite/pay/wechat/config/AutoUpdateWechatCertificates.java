package work.gaigeshen.tripartite.pay.wechat.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.cert.X509Certificate;
import java.util.Collection;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * 微信平台证书增强实现带有自动更新功能
 *
 * @author gaigeshen
 */
public class AutoUpdateWechatCertificates extends WechatCertificatesDecorator {

    private static final Logger log = LoggerFactory.getLogger(AutoUpdateWechatCertificates.class);

    private final Timer updateTimer;

    private final AtomicBoolean started;

    /**
     * 创建此微信平台证书
     *
     * @param certificates 需要被包装的微信平台证书不能为空
     */
    public AutoUpdateWechatCertificates(WechatCertificates certificates) {
        super(certificates);
        this.updateTimer = new Timer(true);
        this.started = new AtomicBoolean(false);
    }

    /**
     * 开始更新方法，调用此方法开始执行更新微信平台证书
     *
     * @param fetcher       微信平台证书获取器不能为空
     * @param delaySeconds  更新操作的延迟时间
     * @param periodSeconds 更新操作的间隔时间
     */
    public void startUpdate(CertificatesFetcher fetcher, long delaySeconds, long periodSeconds) {
        if (Objects.isNull(fetcher)) {
            throw new IllegalArgumentException("certificate fetcher cannot be null");
        }
        if (delaySeconds < 0) {
            throw new IllegalArgumentException("delay seconds is invalid");
        }
        if (periodSeconds <= 0) {
            throw new IllegalArgumentException("period seconds is invalid");
        }
        if (started.compareAndSet(false, true)) {
            updateTimer.scheduleAtFixedRate(new UpdateTask(fetcher), delaySeconds * 1000, periodSeconds * 1000);
        }
    }

    /**
     * 调用此方法将终止更新微信平台证书，并且不可再次调用开始更新方法
     */
    public void cancelUpdate() {
        updateTimer.cancel();
    }

    /**
     * 此接口用于获取微信平台证书内容
     *
     * @author gaigeshen
     */
    public interface CertificatesFetcher {
        /**
         * 获取微信平台证书内容
         *
         * @return 微信平台证书内容集合
         */
        Collection<String> fetchCertificates();
    }


    /**
     * 定时器任务
     *
     * @author gaigeshen
     */
    private class UpdateTask extends TimerTask {

        private final CertificatesFetcher fetcher;

        private UpdateTask(CertificatesFetcher fetcher) {
            this.fetcher = fetcher;
        }

        @Override
        public void run() {
            try {
                Collection<String> certificatesContent = fetcher.fetchCertificates();
                if (certificatesContent.isEmpty()) {
                    return;
                }
                for (String certificateContent : certificatesContent) {
                    X509Certificate cert = loadCertificate(certificateContent);
                    String certSN = cert.getSerialNumber().toString(16);

                    log.info("loaded wechat certificate: [{}] from [{}] to [{}]", certSN, cert.getNotBefore(), cert.getNotAfter());
                }
            } catch (Exception e) {
                log.warn("could not fetch or load wechat certificates", e);
            }
        }
    }
}
