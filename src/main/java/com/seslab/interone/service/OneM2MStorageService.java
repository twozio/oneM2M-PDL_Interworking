package com.seslab.interone.service;

import com.seslab.interone.contract.OneM2MStorage;
import com.seslab.interone.vo.response.DataReq;
import io.reactivex.Flowable;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tuples.generated.Tuple4;

@Slf4j
@Service
@RequiredArgsConstructor
public class OneM2MStorageService {

  private final OneM2MStorage oneM2MStorage;

  /**
   * Store data in the smart contract.
   *
   * @param req The ID of the data and value of the data.
   * @return The transaction receipt.
   * @throws Exception if the transaction fails.
   */
  public TransactionReceipt storeData(DataReq req) throws Exception {
    log.info("Storing data with ID: {} and value: {}", req.getDataID(), req.getDataValue());
    return oneM2MStorage.storeData(req.getDataID(), req.getDataValue()).send();
  }

  /**
   * Retrieve data from the smart contract.
   *
   * @param dataID The ID of the data to retrieve.
   * @return A tuple containing dataID, dataValue, hashValue, and owner.
   * @throws Exception if the retrieval fails.
   */
  public Tuple4<String, String, byte[], String> retrieveData(String dataID) throws Exception {
    log.info("Retrieving data with ID: {}", dataID);
    return oneM2MStorage.retrieveData(dataID).send();
  }

  /**
   * Verify the integrity of the data stored in the smart contract.
   *
   * @param req The ID of the data and value of the data to verify.
   * @return True if the data is valid, false otherwise.
   * @throws Exception if the verification fails.
   */
  public Boolean verifyData(DataReq req) throws Exception {
    log.info("Verifying data with ID: {} and value: {}", req.getDataID(), req.getDataValue());
    return oneM2MStorage.verifyData(req.getDataID(), req.getDataValue()).send();
  }

  /**
   * Listen for DataStored events.
   *
   * @return A Flowable stream of DataStoredEventResponse.
   */
  public Flowable<OneM2MStorage.DataStoredEventResponse> dataStoredEventFlowable() {
    log.info("Subscribing to DataStored events");
    return oneM2MStorage.dataStoredEventFlowable(
      DefaultBlockParameter.valueOf("earliest"),
      DefaultBlockParameter.valueOf("latest")
                                                );
  }

  /**
   * Listen for DataVerified events.
   *
   * @return A Flowable stream of DataVerifiedEventResponse.
   */
  public Flowable<OneM2MStorage.DataVerifiedEventResponse> dataVerifiedEventFlowable() {
    log.info("Subscribing to DataVerified events");
    return oneM2MStorage.dataVerifiedEventFlowable(
      DefaultBlockParameter.valueOf("earliest"),
      DefaultBlockParameter.valueOf("latest"));
  }
}
