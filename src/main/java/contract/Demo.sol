pragma solidity >=0.8.0 <0.9.0;

contract Demo {
    mapping(string => string) public txs;

    function setTxs(string memory key, string memory value)public{
        txs[key] = value;
    }

    function getValueByKey(string memory key) public returns (string memory){
        return txs[key];
    }

}