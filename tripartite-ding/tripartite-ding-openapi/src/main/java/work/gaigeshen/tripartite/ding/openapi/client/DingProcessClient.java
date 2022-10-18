package work.gaigeshen.tripartite.ding.openapi.client;

import work.gaigeshen.tripartite.core.client.Client;
import work.gaigeshen.tripartite.core.client.ClientException;
import work.gaigeshen.tripartite.ding.openapi.config.DingConfig;
import work.gaigeshen.tripartite.ding.openapi.parameters.process.*;
import work.gaigeshen.tripartite.ding.openapi.response.process.*;

/**
 * @author gaigeshen
 */
public interface DingProcessClient extends Client<DingConfig> {

    DingProcessCodeGetResponse processGetCode(DingProcessCodeGetParameters parameters) throws ClientException;

    DingProcessSaveResponse processSave(DingProcessSaveParameters parameters) throws ClientException;

    DingProcessDeleteResponse processDelete(DingProcessDeleteParameters parameters) throws ClientException;

    DingProcessWorkRecordCreateResponse processCreateWorkRecord(DingProcessWorkRecordCreateParameters parameters) throws ClientException;

    DingProcessWorkRecordUpdateResponse processUpdateWorkRecord(DingProcessWorkRecordUpdateParameters parameters) throws ClientException;

    DingProcessWorkRecordBatchUpdateResponse processBatchUpdateWorkRecord(DingProcessWorkRecordBatchUpdateParameters parameters) throws ClientException;

    DingProcessWorkRecordTaskCreateResponse processCreateWorkRecordTask(DingProcessWorkRecordTaskCreateParameters parameters) throws ClientException;

    DingProcessWorkRecordTaskUpdateResponse processUpdateWorkRecordTask(DingProcessWorkRecordTaskUpdateParameters parameters) throws ClientException;

    DingProcessWorkRecordTaskQueryResponse processQueryWorkRecordTask(DingProcessWorkRecordTaskQueryParameters parameters) throws ClientException;

    DingProcessWorkRecordTaskCancelResponse processCancelWorkRecordTask(DingProcessWorkRecordTaskCancelParameters parameters) throws ClientException;
}
