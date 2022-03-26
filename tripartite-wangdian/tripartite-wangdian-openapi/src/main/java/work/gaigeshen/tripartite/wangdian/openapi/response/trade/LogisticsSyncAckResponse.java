package work.gaigeshen.tripartite.wangdian.openapi.response.trade;

import work.gaigeshen.tripartite.wangdian.openapi.response.AbstractWangdianResponse;

import java.util.Collection;

/**
 *
 * @author gaigeshen
 */
public class LogisticsSyncAckResponse extends AbstractWangdianResponse {

  public Collection<Error> errors;


  public static class Error {

    public Integer rec_id;

    public String error;
  }
}
