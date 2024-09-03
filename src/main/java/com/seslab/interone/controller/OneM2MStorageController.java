package com.seslab.interone.controller;

import com.seslab.interone.contract.OneM2MStorage;
import com.seslab.interone.vo.response.DataReq;
import io.reactivex.Flowable;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import com.seslab.interone.service.OneM2MStorageService;
import org.web3j.tuples.generated.Tuple4;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/oneM2MStorage")
@Slf4j
@RequiredArgsConstructor
public class OneM2MStorageController {

  private final OneM2MStorageService oneM2MStorageService;

  @PostMapping("/store")
  public TransactionReceipt storeData(@RequestBody DataReq req) throws Exception {
    log.info("Received request to store data with ID: {} and value: {}", req.getDataID(), req.getDataValue());
    return oneM2MStorageService.storeData(req);
  }

  @GetMapping("/retrieve")
  public Map<String, Object> retrieveData(@RequestParam String dataID) throws Exception {
    log.info("Received request to retrieve data with ID: {}", dataID);
    Tuple4<String, String, byte[], String> data = oneM2MStorageService.retrieveData(dataID);
    Map<String, Object> response = new HashMap<>();
    response.put("dataID", data.component1());
    response.put("dataValue", data.component2());
    response.put("hashValue", data.component3());
    response.put("owner", data.component4());
    return response;
  }

  @PostMapping("/verify")
  public Boolean verifyData(@RequestBody DataReq req) throws Exception {
    log.info("Received request to verify data with ID: {} and value: {}", req.getDataID(), req.getDataValue());
    return oneM2MStorageService.verifyData(req);
  }

  @GetMapping("/subscribeDataStored")
  public Flowable<OneM2MStorage.DataStoredEventResponse> subscribeDataStored() {
    log.info("Subscribing to DataStored events");
    return oneM2MStorageService.dataStoredEventFlowable();
  }

  @GetMapping("/subscribeDataVerified")
  public Flowable<OneM2MStorage.DataVerifiedEventResponse> subscribeDataVerified() {
    log.info("Subscribing to DataVerified events");
    return oneM2MStorageService.dataVerifiedEventFlowable();
  }
}