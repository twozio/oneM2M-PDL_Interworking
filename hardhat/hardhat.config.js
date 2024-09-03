require("@nomicfoundation/hardhat-toolbox");

const { vars } = require("hardhat/config");

/** @type import('hardhat/config').HardhatUserConfig */
const BAOBAB_PRIVATE_KEY = vars.get("BAOBAB_PRIVATE_KEY");

module.exports = {
  solidity: "0.8.24",
  networks: {
    baobab: {
      url: "https://api.baobab.klaytn.net:8651",
      accounts: [BAOBAB_PRIVATE_KEY],
    },
  },
};
