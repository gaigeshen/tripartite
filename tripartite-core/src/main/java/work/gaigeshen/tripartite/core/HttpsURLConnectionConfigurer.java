package work.gaigeshen.tripartite.core;

import javax.net.ssl.*;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;

/**
 *
 * @author gaigeshen
 */
public abstract class HttpsURLConnectionConfigurer {

  public static void defaultDisableSSLValidation() {
    try {
      SSLContext sslContext = SSLContext.getInstance("TLS");
      sslContext.init(null, new TrustManager[] { DisableValidationTrustManager.INSTANCE }, new SecureRandom());
      HttpsURLConnection.setDefaultSSLSocketFactory(sslContext.getSocketFactory());
    } catch (Exception ignored) {
    }
    HttpsURLConnection.setDefaultHostnameVerifier(TrustAllHostnamesHostnameVerifier.INSTANCE);
  }

  /**
   *
   * @author gaigeshen
   */
  public static class DisableValidationTrustManager implements X509TrustManager {

    public static final DisableValidationTrustManager INSTANCE = new DisableValidationTrustManager();

    @Override
    public void checkClientTrusted(X509Certificate[] x509Certificates, String s) {

    }

    @Override
    public void checkServerTrusted(X509Certificate[] x509Certificates, String s) {

    }

    @Override
    public X509Certificate[] getAcceptedIssuers() {
      return new X509Certificate[0];
    }
  }

  /**
   *
   * @author gaigeshen
   */
  public static class TrustAllHostnamesHostnameVerifier implements HostnameVerifier {

    public static final TrustAllHostnamesHostnameVerifier INSTANCE = new TrustAllHostnamesHostnameVerifier();

    @Override
    public boolean verify(String s, SSLSession sslSession) {
      return true;
    }
  }
}
