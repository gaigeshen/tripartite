package work.gaigeshen.tripartite.ding.openapi.robotwebhook;

import java.util.Objects;

/**
 *
 * @author gaigeshen
 */
public class DingRobotWebhook {

  private final String name;

  private final String webhook;

  public DingRobotWebhook(String name, String webhook) {
    if (Objects.isNull(name) || Objects.isNull(webhook)) {
      throw new IllegalArgumentException("name and webhook cannot be null");
    }
    this.name = name;
    this.webhook = webhook;
  }

  public String getName() {
    return name;
  }

  public String getWebhook() {
    return webhook;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    DingRobotWebhook that = (DingRobotWebhook) o;
    return Objects.equals(name, that.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name);
  }

  @Override
  public String toString() {
    return "RobotWebhook: [ " + name + " ] - [ "+ webhook +" ]";
  }
}
