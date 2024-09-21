package work.gaigeshen.tripartite.qyweixin.openapi.suite;

import lombok.Getter;

import java.util.Objects;

/**
 * 应用票据
 *
 * @author gaigeshen
 */
@Getter
public class SuiteTicket {

    private final String ticket;

    private final String suiteId;

    private SuiteTicket(Builder builder) {
        this.ticket = builder.ticket;
        this.suiteId = builder.suiteId;
    }

    public static Builder builder() {
        return new Builder();
    }

    @Override
    public String toString() {
        return "SuiteTicket: " + suiteId + ", Ticket: " + ticket;
    }

    /**
     * @author gaigeshen
     */
    public static class Builder {

        private String ticket;

        private String suiteId;

        public Builder setTicket(String ticket) {
            this.ticket = ticket;
            return this;
        }

        public Builder setSuiteId(String suiteId) {
            this.suiteId = suiteId;
            return this;
        }

        public SuiteTicket build() {
            if (Objects.isNull(ticket)) {
                throw new IllegalArgumentException("ticket");
            }
            if (Objects.isNull(suiteId)) {
                throw new IllegalArgumentException("suiteId");
            }
            return new SuiteTicket(this);
        }
    }
}
