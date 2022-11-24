package work.gaigeshen.tripartite.ding.openapi.client;

import work.gaigeshen.tripartite.ding.openapi.config.DingConfig;

import java.util.Objects;

/**
 *
 * @author gaigeshen
 */
public class DingAuthCorpContexts {

    private static final ThreadLocal<Context> CONTEXT_LOCAL = new ThreadLocal<>();


    public static void setContextLocal(Context context) {
        CONTEXT_LOCAL.set(context);
    }

    public static Context getContextLocal() {
        return CONTEXT_LOCAL.get();
    }

    public static void removeContextLocal() {
        CONTEXT_LOCAL.remove();
    }

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
