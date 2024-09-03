const { buildModule } = require("@nomicfoundation/hardhat-ignition/modules");

const OneM2MStorageModule = buildModule("OneM2MStorageModule", (m) => {
  const oneM2MStorage = m.contract("OneM2MStorage", []);
  return { oneM2MStorage };
});

module.exports = OneM2MStorageModule;
