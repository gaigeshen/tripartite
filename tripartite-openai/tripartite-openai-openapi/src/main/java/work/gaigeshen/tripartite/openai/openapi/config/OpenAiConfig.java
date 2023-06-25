package work.gaigeshen.tripartite.openai.openapi.config;

import work.gaigeshen.tripartite.core.client.config.Config;

import java.util.Objects;

public class OpenAiConfig implements Config {

    private final String serverHost;

    private final String apiKey;

    private final String organization;

    public OpenAiConfig(String serverHost, String apiKey, String organization) {
        this.serverHost = serverHost;
        this.apiKey = apiKey;
        this.organization = organization;
    }

    public String getServerHost() {
        return serverHost;
    }

    public String getApiKey() {
        return apiKey;
    }

    public String getOrganization() {
        return organization;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        OpenAiConfig that = (OpenAiConfig) o;
        return Objects.equals(apiKey, that.apiKey)
                && Objects.equals(organization, that.organization);
    }

    @Override
    public int hashCode() {
        return Objects.hash(apiKey, organization);
    }

    @Override
    public String toString() {
        return "OpenAiConfig: " + apiKey + "/" + organization;
    }
}
