package com.seslab.interone;

import io.reactivex.Flowable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Bool;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Bytes32;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.BaseEventResponse;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tuples.generated.Tuple4;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/main/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 1.6.0.
 */
@SuppressWarnings("rawtypes")
public class OneM2MStorage_sol_OneM2MStorage extends Contract {
    public static final String BINARY = "6080604052348015600e575f80fd5b506107c28061001c5f395ff3fe608060405234801561000f575f80fd5b506004361061003f575f3560e01c806335f007c6146100435780634ece5b4c1461006f5780638020ccb614610084575b5f80fd5b6100566100513660046104eb565b6100a7565b6040516100669493929190610553565b60405180910390f35b61008261007d366004610599565b610236565b005b610097610092366004610599565b6103ef565b6040519015158152602001610066565b6060805f805f80866040516100bc9190610615565b90815260200160405180910390206040518060800160405290815f820180546100e490610627565b80601f016020809104026020016040519081016040528092919081815260200182805461011090610627565b801561015b5780601f106101325761010080835404028352916020019161015b565b820191905f5260205f20905b81548152906001019060200180831161013e57829003601f168201915b5050505050815260200160018201805461017490610627565b80601f01602080910402602001604051908101604052809291908181526020018280546101a090610627565b80156101eb5780601f106101c2576101008083540402835291602001916101eb565b820191905f5260205f20905b8154815290600101906020018083116101ce57829003601f168201915b505050918352505060028201546020808301919091526003909201546001600160a01b0316604091820152825191830151908301516060909301519199909850919650945092505050565b5f6001600160a01b03165f8360405161024f9190610615565b908152604051908190036020019020600301546001600160a01b0316146102e757336001600160a01b03165f836040516102899190610615565b908152604051908190036020019020600301546001600160a01b0316146102e75760405162461bcd60e51b815260206004820152600e60248201526d139bdd08185d5d1a1bdc9a5e995960921b604482015260640160405180910390fd5b5f82826040516020016102fb92919061065f565b6040516020818303038152906040528051906020012090506040518060800160405280848152602001838152602001828152602001336001600160a01b03168152505f8460405161034c9190610615565b9081526040519081900360200190208151819061036990826106bf565b506020820151600182019061037e90826106bf565b506040828101516002830155606090920151600390910180546001600160a01b0319166001600160a01b039092169190911790555133907f2f3d3652c01aae0ca9855019f280f0531004513edf2e698034650da2072babdc906103e290869061077a565b60405180910390a2505050565b5f80838360405160200161040492919061065f565b6040516020818303038152906040528051906020012090505f808560405161042c9190610615565b908152604051908190036020019020600201549190911491505092915050565b634e487b7160e01b5f52604160045260245ffd5b5f82601f83011261046f575f80fd5b813567ffffffffffffffff8111156104895761048961044c565b604051601f8201601f19908116603f0116810167ffffffffffffffff811182821017156104b8576104b861044c565b6040528181528382016020018510156104cf575f80fd5b816020850160208301375f918101602001919091529392505050565b5f602082840312156104fb575f80fd5b813567ffffffffffffffff811115610511575f80fd5b61051d84828501610460565b949350505050565b5f81518084528060208401602086015e5f602082860101526020601f19601f83011685010191505092915050565b608081525f6105656080830187610525565b82810360208401526105778187610525565b604084019590955250506001600160a01b039190911660609091015292915050565b5f80604083850312156105aa575f80fd5b823567ffffffffffffffff8111156105c0575f80fd5b6105cc85828601610460565b925050602083013567ffffffffffffffff8111156105e8575f80fd5b6105f485828601610460565b9150509250929050565b5f81518060208401855e5f93019283525090919050565b5f61062082846105fe565b9392505050565b600181811c9082168061063b57607f821691505b60208210810361065957634e487b7160e01b5f52602260045260245ffd5b50919050565b5f61051d61066d83866105fe565b846105fe565b601f8211156106ba57805f5260205f20601f840160051c810160208510156106985750805b601f840160051c820191505b818110156106b7575f81556001016106a4565b50505b505050565b815167ffffffffffffffff8111156106d9576106d961044c565b6106ed816106e78454610627565b84610673565b6020601f82116001811461071f575f83156107085750848201515b5f19600385901b1c1916600184901b1784556106b7565b5f84815260208120601f198516915b8281101561074e578785015182556020948501946001909201910161072e565b508482101561076b57868401515f19600387901b60f8161c191681555b50505050600190811b01905550565b602081525f610620602083018461052556fea26469706673582212201166b967f07d32a6904da8f0ca73f2d9f89b208d45df07142f4102823f00e61a64736f6c634300081a0033";

    private static String librariesLinkedBinary;

    public static final String FUNC_RETRIEVEDATA = "retrieveData";

    public static final String FUNC_STOREDATA = "storeData";

    public static final String FUNC_VERIFYDATA = "verifyData";

    public static final Event DATASTORED_EVENT = new Event("DataStored", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}, new TypeReference<Address>(true) {}));
    ;

    public static final Event DATAVERIFIED_EVENT = new Event("DataVerified", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}, new TypeReference<Bool>() {}));
    ;

    @Deprecated
    protected OneM2MStorage_sol_OneM2MStorage(String contractAddress, Web3j web3j,
            Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected OneM2MStorage_sol_OneM2MStorage(String contractAddress, Web3j web3j,
            Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected OneM2MStorage_sol_OneM2MStorage(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected OneM2MStorage_sol_OneM2MStorage(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static List<DataStoredEventResponse> getDataStoredEvents(
            TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = staticExtractEventParametersWithLog(DATASTORED_EVENT, transactionReceipt);
        ArrayList<DataStoredEventResponse> responses = new ArrayList<DataStoredEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            DataStoredEventResponse typedResponse = new DataStoredEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.owner = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.dataID = (String) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public static DataStoredEventResponse getDataStoredEventFromLog(Log log) {
        Contract.EventValuesWithLog eventValues = staticExtractEventParametersWithLog(DATASTORED_EVENT, log);
        DataStoredEventResponse typedResponse = new DataStoredEventResponse();
        typedResponse.log = log;
        typedResponse.owner = (String) eventValues.getIndexedValues().get(0).getValue();
        typedResponse.dataID = (String) eventValues.getNonIndexedValues().get(0).getValue();
        return typedResponse;
    }

    public Flowable<DataStoredEventResponse> dataStoredEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getDataStoredEventFromLog(log));
    }

    public Flowable<DataStoredEventResponse> dataStoredEventFlowable(
            DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(DATASTORED_EVENT));
        return dataStoredEventFlowable(filter);
    }

    public static List<DataVerifiedEventResponse> getDataVerifiedEvents(
            TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = staticExtractEventParametersWithLog(DATAVERIFIED_EVENT, transactionReceipt);
        ArrayList<DataVerifiedEventResponse> responses = new ArrayList<DataVerifiedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            DataVerifiedEventResponse typedResponse = new DataVerifiedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.dataID = (String) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.isValid = (Boolean) eventValues.getNonIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public static DataVerifiedEventResponse getDataVerifiedEventFromLog(Log log) {
        Contract.EventValuesWithLog eventValues = staticExtractEventParametersWithLog(DATAVERIFIED_EVENT, log);
        DataVerifiedEventResponse typedResponse = new DataVerifiedEventResponse();
        typedResponse.log = log;
        typedResponse.dataID = (String) eventValues.getNonIndexedValues().get(0).getValue();
        typedResponse.isValid = (Boolean) eventValues.getNonIndexedValues().get(1).getValue();
        return typedResponse;
    }

    public Flowable<DataVerifiedEventResponse> dataVerifiedEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getDataVerifiedEventFromLog(log));
    }

    public Flowable<DataVerifiedEventResponse> dataVerifiedEventFlowable(
            DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(DATAVERIFIED_EVENT));
        return dataVerifiedEventFlowable(filter);
    }

    public RemoteFunctionCall<Tuple4<String, String, byte[], String>> retrieveData(String dataID) {
        final Function function = new Function(FUNC_RETRIEVEDATA, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(dataID)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Bytes32>() {}, new TypeReference<Address>() {}));
        return new RemoteFunctionCall<Tuple4<String, String, byte[], String>>(function,
                new Callable<Tuple4<String, String, byte[], String>>() {
                    @Override
                    public Tuple4<String, String, byte[], String> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple4<String, String, byte[], String>(
                                (String) results.get(0).getValue(), 
                                (String) results.get(1).getValue(), 
                                (byte[]) results.get(2).getValue(), 
                                (String) results.get(3).getValue());
                    }
                });
    }

    public RemoteFunctionCall<TransactionReceipt> storeData(String dataID, String dataValue) {
        final Function function = new Function(
                FUNC_STOREDATA, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(dataID), 
                new org.web3j.abi.datatypes.Utf8String(dataValue)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<Boolean> verifyData(String dataID, String dataValue) {
        final Function function = new Function(FUNC_VERIFYDATA, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(dataID), 
                new org.web3j.abi.datatypes.Utf8String(dataValue)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    @Deprecated
    public static OneM2MStorage_sol_OneM2MStorage load(String contractAddress, Web3j web3j,
            Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new OneM2MStorage_sol_OneM2MStorage(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static OneM2MStorage_sol_OneM2MStorage load(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new OneM2MStorage_sol_OneM2MStorage(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static OneM2MStorage_sol_OneM2MStorage load(String contractAddress, Web3j web3j,
            Credentials credentials, ContractGasProvider contractGasProvider) {
        return new OneM2MStorage_sol_OneM2MStorage(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static OneM2MStorage_sol_OneM2MStorage load(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new OneM2MStorage_sol_OneM2MStorage(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<OneM2MStorage_sol_OneM2MStorage> deploy(Web3j web3j,
            Credentials credentials, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(OneM2MStorage_sol_OneM2MStorage.class, web3j, credentials, contractGasProvider, getDeploymentBinary(), "");
    }

    @Deprecated
    public static RemoteCall<OneM2MStorage_sol_OneM2MStorage> deploy(Web3j web3j,
            Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(OneM2MStorage_sol_OneM2MStorage.class, web3j, credentials, gasPrice, gasLimit, getDeploymentBinary(), "");
    }

    public static RemoteCall<OneM2MStorage_sol_OneM2MStorage> deploy(Web3j web3j,
            TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(OneM2MStorage_sol_OneM2MStorage.class, web3j, transactionManager, contractGasProvider, getDeploymentBinary(), "");
    }

    @Deprecated
    public static RemoteCall<OneM2MStorage_sol_OneM2MStorage> deploy(Web3j web3j,
            TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(OneM2MStorage_sol_OneM2MStorage.class, web3j, transactionManager, gasPrice, gasLimit, getDeploymentBinary(), "");
    }

    public static void linkLibraries(List<Contract.LinkReference> references) {
        librariesLinkedBinary = linkBinaryWithReferences(BINARY, references);
    }

    private static String getDeploymentBinary() {
        if (librariesLinkedBinary != null) {
            return librariesLinkedBinary;
        } else {
            return BINARY;
        }
    }

    public static class DataStoredEventResponse extends BaseEventResponse {
        public String owner;

        public String dataID;
    }

    public static class DataVerifiedEventResponse extends BaseEventResponse {
        public String dataID;

        public Boolean isValid;
    }
}
