const { expect } = require("chai");

describe("OneM2MStorage", function () {
  let OneM2MStorage, oneM2MStorage;
  let owner, addr1;

  beforeEach(async function () {
    // Get the ContractFactory and Signers here.
    OneM2MStorage = await ethers.getContractFactory("OneM2MStorage");
    [owner, addr1] = await ethers.getSigners();

    // Deploy the contract
    oneM2MStorage = await OneM2MStorage.deploy();
  });

  it("Should store data correctly", async function () {
    const dataID = "sensor1";
    const dataValue = "temperature:25C";

    // Store data using storeData function
    await oneM2MStorage.storeData(dataID, dataValue);

    // Retrieve stored data
    const [storedDataID, storedDataValue, , ] = await oneM2MStorage.retrieveData(dataID);

    // Check that the stored data matches
    expect(storedDataID).to.equal(dataID);
    expect(storedDataValue).to.equal(dataValue);
  });

  it("Should emit DataStored event when storing data", async function () {
    const dataID = "sensor2";
    const dataValue = "humidity:60%";

    // Store data using storeData function and expect event
    await expect(oneM2MStorage.storeData(dataID, dataValue))
      .to.emit(oneM2MStorage, "DataStored")
      .withArgs(dataID, owner.address);
  });

  it("Should return the correct data when retrieving", async function () {
    const dataID = "sensor3";
    const dataValue = "pressure:1013hPa";

    // Store data using storeData function
    await oneM2MStorage.storeData(dataID, dataValue);

    // Retrieve stored data
    const [storedDataID, storedDataValue, , ] = await oneM2MStorage.retrieveData(dataID);

    // Check that the stored data matches
    expect(storedDataID).to.equal(dataID);
    expect(storedDataValue).to.equal(dataValue);
  });

  it("Should verify data integrity correctly", async function () {
    const dataID = "sensor4";
    const dataValue = "co2:400ppm";

    // Store data using storeData function
    await oneM2MStorage.storeData(dataID, dataValue);

    // Verify data and assert that the result is true
    const isValid = await oneM2MStorage.verifyData(dataID, dataValue);
    expect(isValid).to.be.true;
  });

  it("Should not allow non-owner to store data with the same dataID", async function () {
    const dataID = "sensor5";
    const dataValue = "co2:400ppm";

    // Store data by the owner
    await oneM2MStorage.storeData(dataID, dataValue);

    // Try to store data with the same dataID from another account
    await expect(
      oneM2MStorage.connect(addr1).storeData(dataID, "differentValue")
    ).to.be.revertedWith("Not authorized");
  });
});
