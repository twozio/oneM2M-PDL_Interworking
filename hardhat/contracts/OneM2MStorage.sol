// SPDX-License-Identifier: MIT
pragma solidity ^0.8.0;

contract OneM2MStorage {
    struct OneM2MData {
        string dataID;
        string dataValue;
        bytes32 hashValue; // Hash of the data for integrity verification
        address owner;
    }

    mapping(string => OneM2MData) private dataStore;

    event DataStored(string dataID, address indexed owner);
    event DataVerified(string dataID, bool isValid);

    modifier onlyOwner(string memory dataID) {
        require(dataStore[dataID].owner == msg.sender, "Not authorized");
        _;
    }

    function storeData(string memory dataID, string memory dataValue) public {
        if (dataStore[dataID].owner != address(0)) {
            require(dataStore[dataID].owner == msg.sender, "Not authorized");
        }

        // Calculate the hash of the data
        bytes32 hashValue = keccak256(abi.encodePacked(dataID, dataValue));

        // Store the data
        dataStore[dataID] = OneM2MData({
            dataID: dataID,
            dataValue: dataValue,
            hashValue: hashValue,
            owner: msg.sender
        });

        emit DataStored(dataID, msg.sender);
    }

    function retrieveData(string memory dataID) public view returns (string memory, string memory, bytes32, address) {
        OneM2MData memory storedData = dataStore[dataID];
        return (storedData.dataID, storedData.dataValue, storedData.hashValue, storedData.owner);
    }

    function verifyData(string memory dataID, string memory dataValue) public view returns (bool) {
        bytes32 hashValue = keccak256(abi.encodePacked(dataID, dataValue));
        bool isValid = (hashValue == dataStore[dataID].hashValue);
        return isValid;
    }
}
