package work.gaigeshen.tripartite.ding.openapi.config;

import java.util.Objects;

/**
 * 钉钉授权企业上下文信息保存，采用当前线程变量的形式
 *
 * @author gaigeshen
 */
public class DingAuthCorpContexts {

    private static final ThreadLocal<Context> CONTEXT_LOCAL = new ThreadLocal<>();

    /**
     * 设置当前的授权企业上下文信息
     *
     * @param context 当前的授权企业上下文信息
     */
    public static void setContextLocal(Context context) {
        CONTEXT_LOCAL.set(context);
    }

    /**
     * 获取当前的授权企业上下文信息
     *
     * @return 当前的授权企业上下文信息
     */
    public static Context getContextLocal() {
        return CONTEXT_LOCAL.get();
    }

    /**
     * 移除当前的授权企业上下文信息，在不需要的时候进行移除操作
     */
    public static void removeContextLocal() {
        CONTEXT_LOCAL.remove();
    }

    /**
     * 钉钉授权企业上下文信息
     *
     * @author gaigeshen
     */
    public static class Context extends DingConfig {

        private final String authCorpId;

        public Context(DingConfig dingConfig, String authCorpId) {
            super(dingConfig);
            this.authCorpId = authCorpId;
        }

        public String getAuthCorpId() {
            return authCorpId;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            if (!super.equals(o)) return false;
            Context context = (Context) o;
            return Objects.equals(authCorpId, context.authCorpId);
        }

        @Override
        public int hashCode() {
            return Objects.hash(super.hashCode(), authCorpId);
        }

        @Override
        public String toString() {
            return "Context{" +
                    "authCorpId='" + authCorpId + '\'' +
                    '}';
        }
    }
}
