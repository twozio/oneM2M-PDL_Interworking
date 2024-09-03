package com.seslab.interone.config;

import com.seslab.interone.contract.OneM2MStorage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.ECKeyPair;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.Contract;
import org.web3j.tx.gas.StaticGasProvider;

import java.math.BigInteger;

@Configuration
@RequiredArgsConstructor
public class Web3jConfig {

  @Value("${baobab.api_url}")
  private String API_URL;

  @Value("${baobab.contract.address}")
  private String CONTRACT_ADDRESS;

  @Value("${baobab.private_key}")
  private String PRIVATE_KEY;

  @Bean
  public Web3j web3j() {
    return Web3j.build(new HttpService(API_URL));
  }

  @Bean
  public Credentials credentials() {
    BigInteger privateKeyInBT = new BigInteger(PRIVATE_KEY, 16);
    return Credentials.create(ECKeyPair.create(privateKeyInBT));
  }

  @Bean
  public OneM2MStorage oneM2MStorage() {
    BigInteger gasPrice = BigInteger.valueOf(25000000000L);
    BigInteger gasLimit = Contract.GAS_LIMIT;
    StaticGasProvider gasProvider = new StaticGasProvider(gasPrice, gasLimit);

    return OneM2MStorage.load(CONTRACT_ADDRESS, web3j(), credentials(), gasProvider);
  }

  public Web3jConfig(String privateKey) {
    this.PRIVATE_KEY = privateKey; // 복호화 실행
    oneM2MStorage();
  }
}
