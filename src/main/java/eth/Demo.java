package eth;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 1.4.2.
 */
@SuppressWarnings("rawtypes")
public class Demo extends Contract {
    public static final String BINARY = "{\r\n"
            + "\t\"functionDebugData\": {},\r\n"
            + "\t\"generatedSources\": [],\r\n"
            + "\t\"linkReferences\": {},\r\n"
            + "\t\"object\": \"608060405234801561001057600080fd5b506106b1806100206000396000f3fe608060405234801561001057600080fd5b50600436106100415760003560e01c80630ad557bf146100465780631258a93a1461006257806336d7b23a14610092575b600080fd5b610060600480360381019061005b91906103bb565b6100c2565b005b61007c60048036038101906100779190610372565b6100f9565b60405161008991906104b4565b60405180910390f35b6100ac60048036038101906100a79190610372565b6101a9565b6040516100b991906104b4565b60405180910390f35b806000836040516100d3919061049d565b908152602001604051809103902090805190602001906100f492919061025f565b505050565b606060008260405161010b919061049d565b9081526020016040518091039020805461012490610595565b80601f016020809104026020016040519081016040528092919081815260200182805461015090610595565b801561019d5780601f106101725761010080835404028352916020019161019d565b820191906000526020600020905b81548152906001019060200180831161018057829003601f168201915b50505050509050919050565b60008180516020810182018051848252602083016020850120818352809550505050505060009150905080546101de90610595565b80601f016020809104026020016040519081016040528092919081815260200182805461020a90610595565b80156102575780601f1061022c57610100808354040283529160200191610257565b820191906000526020600020905b81548152906001019060200180831161023a57829003601f168201915b505050505081565b82805461026b90610595565b90600052602060002090601f01602090048101928261028d57600085556102d4565b82601f106102a657805160ff19168380011785556102d4565b828001600101855582156102d4579182015b828111156102d35782518255916020019190600101906102b8565b5b5090506102e191906102e5565b5090565b5b808211156102fe5760008160009055506001016102e6565b5090565b6000610315610310846104fb565b6104d6565b9050828152602081018484840111156103315761033061065b565b5b61033c848285610553565b509392505050565b600082601f83011261035957610358610656565b5b8135610369848260208601610302565b91505092915050565b60006020828403121561038857610387610665565b5b600082013567ffffffffffffffff8111156103a6576103a5610660565b5b6103b284828501610344565b91505092915050565b600080604083850312156103d2576103d1610665565b5b600083013567ffffffffffffffff8111156103f0576103ef610660565b5b6103fc85828601610344565b925050602083013567ffffffffffffffff81111561041d5761041c610660565b5b61042985828601610344565b9150509250929050565b600061043e8261052c565b6104488185610537565b9350610458818560208601610562565b6104618161066a565b840191505092915050565b60006104778261052c565b6104818185610548565b9350610491818560208601610562565b80840191505092915050565b60006104a9828461046c565b915081905092915050565b600060208201905081810360008301526104ce8184610433565b905092915050565b60006104e06104f1565b90506104ec82826105c7565b919050565b6000604051905090565b600067ffffffffffffffff82111561051657610515610627565b5b61051f8261066a565b9050602081019050919050565b600081519050919050565b600082825260208201905092915050565b600081905092915050565b82818337600083830152505050565b60005b83811015610580578082015181840152602081019050610565565b8381111561058f576000848401525b50505050565b600060028204905060018216806105ad57607f821691505b602082108114156105c1576105c06105f8565b5b50919050565b6105d08261066a565b810181811067ffffffffffffffff821117156105ef576105ee610627565b5b80604052505050565b7f4e487b7100000000000000000000000000000000000000000000000000000000600052602260045260246000fd5b7f4e487b7100000000000000000000000000000000000000000000000000000000600052604160045260246000fd5b600080fd5b600080fd5b600080fd5b600080fd5b6000601f19601f830116905091905056fea264697066735822122032f11061dd8b9a4b7973bd5501980050c025014c885d319dfee178dc9ddff1c064736f6c63430008070033\",\r\n"
            + "\t\"opcodes\": \"PUSH1 0x80 PUSH1 0x40 MSTORE CALLVALUE DUP1 ISZERO PUSH2 0x10 JUMPI PUSH1 0x0 DUP1 REVERT JUMPDEST POP PUSH2 0x6B1 DUP1 PUSH2 0x20 PUSH1 0x0 CODECOPY PUSH1 0x0 RETURN INVALID PUSH1 0x80 PUSH1 0x40 MSTORE CALLVALUE DUP1 ISZERO PUSH2 0x10 JUMPI PUSH1 0x0 DUP1 REVERT JUMPDEST POP PUSH1 0x4 CALLDATASIZE LT PUSH2 0x41 JUMPI PUSH1 0x0 CALLDATALOAD PUSH1 0xE0 SHR DUP1 PUSH4 0xAD557BF EQ PUSH2 0x46 JUMPI DUP1 PUSH4 0x1258A93A EQ PUSH2 0x62 JUMPI DUP1 PUSH4 0x36D7B23A EQ PUSH2 0x92 JUMPI JUMPDEST PUSH1 0x0 DUP1 REVERT JUMPDEST PUSH2 0x60 PUSH1 0x4 DUP1 CALLDATASIZE SUB DUP2 ADD SWAP1 PUSH2 0x5B SWAP2 SWAP1 PUSH2 0x3BB JUMP JUMPDEST PUSH2 0xC2 JUMP JUMPDEST STOP JUMPDEST PUSH2 0x7C PUSH1 0x4 DUP1 CALLDATASIZE SUB DUP2 ADD SWAP1 PUSH2 0x77 SWAP2 SWAP1 PUSH2 0x372 JUMP JUMPDEST PUSH2 0xF9 JUMP JUMPDEST PUSH1 0x40 MLOAD PUSH2 0x89 SWAP2 SWAP1 PUSH2 0x4B4 JUMP JUMPDEST PUSH1 0x40 MLOAD DUP1 SWAP2 SUB SWAP1 RETURN JUMPDEST PUSH2 0xAC PUSH1 0x4 DUP1 CALLDATASIZE SUB DUP2 ADD SWAP1 PUSH2 0xA7 SWAP2 SWAP1 PUSH2 0x372 JUMP JUMPDEST PUSH2 0x1A9 JUMP JUMPDEST PUSH1 0x40 MLOAD PUSH2 0xB9 SWAP2 SWAP1 PUSH2 0x4B4 JUMP JUMPDEST PUSH1 0x40 MLOAD DUP1 SWAP2 SUB SWAP1 RETURN JUMPDEST DUP1 PUSH1 0x0 DUP4 PUSH1 0x40 MLOAD PUSH2 0xD3 SWAP2 SWAP1 PUSH2 0x49D JUMP JUMPDEST SWAP1 DUP2 MSTORE PUSH1 0x20 ADD PUSH1 0x40 MLOAD DUP1 SWAP2 SUB SWAP1 KECCAK256 SWAP1 DUP1 MLOAD SWAP1 PUSH1 0x20 ADD SWAP1 PUSH2 0xF4 SWAP3 SWAP2 SWAP1 PUSH2 0x25F JUMP JUMPDEST POP POP POP JUMP JUMPDEST PUSH1 0x60 PUSH1 0x0 DUP3 PUSH1 0x40 MLOAD PUSH2 0x10B SWAP2 SWAP1 PUSH2 0x49D JUMP JUMPDEST SWAP1 DUP2 MSTORE PUSH1 0x20 ADD PUSH1 0x40 MLOAD DUP1 SWAP2 SUB SWAP1 KECCAK256 DUP1 SLOAD PUSH2 0x124 SWAP1 PUSH2 0x595 JUMP JUMPDEST DUP1 PUSH1 0x1F ADD PUSH1 0x20 DUP1 SWAP2 DIV MUL PUSH1 0x20 ADD PUSH1 0x40 MLOAD SWAP1 DUP2 ADD PUSH1 0x40 MSTORE DUP1 SWAP3 SWAP2 SWAP1 DUP2 DUP2 MSTORE PUSH1 0x20 ADD DUP3 DUP1 SLOAD PUSH2 0x150 SWAP1 PUSH2 0x595 JUMP JUMPDEST DUP1 ISZERO PUSH2 0x19D JUMPI DUP1 PUSH1 0x1F LT PUSH2 0x172 JUMPI PUSH2 0x100 DUP1 DUP4 SLOAD DIV MUL DUP4 MSTORE SWAP2 PUSH1 0x20 ADD SWAP2 PUSH2 0x19D JUMP JUMPDEST DUP3 ADD SWAP2 SWAP1 PUSH1 0x0 MSTORE PUSH1 0x20 PUSH1 0x0 KECCAK256 SWAP1 JUMPDEST DUP2 SLOAD DUP2 MSTORE SWAP1 PUSH1 0x1 ADD SWAP1 PUSH1 0x20 ADD DUP1 DUP4 GT PUSH2 0x180 JUMPI DUP3 SWAP1 SUB PUSH1 0x1F AND DUP3 ADD SWAP2 JUMPDEST POP POP POP POP POP SWAP1 POP SWAP2 SWAP1 POP JUMP JUMPDEST PUSH1 0x0 DUP2 DUP1 MLOAD PUSH1 0x20 DUP2 ADD DUP3 ADD DUP1 MLOAD DUP5 DUP3 MSTORE PUSH1 0x20 DUP4 ADD PUSH1 0x20 DUP6 ADD KECCAK256 DUP2 DUP4 MSTORE DUP1 SWAP6 POP POP POP POP POP POP PUSH1 0x0 SWAP2 POP SWAP1 POP DUP1 SLOAD PUSH2 0x1DE SWAP1 PUSH2 0x595 JUMP JUMPDEST DUP1 PUSH1 0x1F ADD PUSH1 0x20 DUP1 SWAP2 DIV MUL PUSH1 0x20 ADD PUSH1 0x40 MLOAD SWAP1 DUP2 ADD PUSH1 0x40 MSTORE DUP1 SWAP3 SWAP2 SWAP1 DUP2 DUP2 MSTORE PUSH1 0x20 ADD DUP3 DUP1 SLOAD PUSH2 0x20A SWAP1 PUSH2 0x595 JUMP JUMPDEST DUP1 ISZERO PUSH2 0x257 JUMPI DUP1 PUSH1 0x1F LT PUSH2 0x22C JUMPI PUSH2 0x100 DUP1 DUP4 SLOAD DIV MUL DUP4 MSTORE SWAP2 PUSH1 0x20 ADD SWAP2 PUSH2 0x257 JUMP JUMPDEST DUP3 ADD SWAP2 SWAP1 PUSH1 0x0 MSTORE PUSH1 0x20 PUSH1 0x0 KECCAK256 SWAP1 JUMPDEST DUP2 SLOAD DUP2 MSTORE SWAP1 PUSH1 0x1 ADD SWAP1 PUSH1 0x20 ADD DUP1 DUP4 GT PUSH2 0x23A JUMPI DUP3 SWAP1 SUB PUSH1 0x1F AND DUP3 ADD SWAP2 JUMPDEST POP POP POP POP POP DUP2 JUMP JUMPDEST DUP3 DUP1 SLOAD PUSH2 0x26B SWAP1 PUSH2 0x595 JUMP JUMPDEST SWAP1 PUSH1 0x0 MSTORE PUSH1 0x20 PUSH1 0x0 KECCAK256 SWAP1 PUSH1 0x1F ADD PUSH1 0x20 SWAP1 DIV DUP2 ADD SWAP3 DUP3 PUSH2 0x28D JUMPI PUSH1 0x0 DUP6 SSTORE PUSH2 0x2D4 JUMP JUMPDEST DUP3 PUSH1 0x1F LT PUSH2 0x2A6 JUMPI DUP1 MLOAD PUSH1 0xFF NOT AND DUP4 DUP1 ADD OR DUP6 SSTORE PUSH2 0x2D4 JUMP JUMPDEST DUP3 DUP1 ADD PUSH1 0x1 ADD DUP6 SSTORE DUP3 ISZERO PUSH2 0x2D4 JUMPI SWAP2 DUP3 ADD JUMPDEST DUP3 DUP2 GT ISZERO PUSH2 0x2D3 JUMPI DUP3 MLOAD DUP3 SSTORE SWAP2 PUSH1 0x20 ADD SWAP2 SWAP1 PUSH1 0x1 ADD SWAP1 PUSH2 0x2B8 JUMP JUMPDEST JUMPDEST POP SWAP1 POP PUSH2 0x2E1 SWAP2 SWAP1 PUSH2 0x2E5 JUMP JUMPDEST POP SWAP1 JUMP JUMPDEST JUMPDEST DUP1 DUP3 GT ISZERO PUSH2 0x2FE JUMPI PUSH1 0x0 DUP2 PUSH1 0x0 SWAP1 SSTORE POP PUSH1 0x1 ADD PUSH2 0x2E6 JUMP JUMPDEST POP SWAP1 JUMP JUMPDEST PUSH1 0x0 PUSH2 0x315 PUSH2 0x310 DUP5 PUSH2 0x4FB JUMP JUMPDEST PUSH2 0x4D6 JUMP JUMPDEST SWAP1 POP DUP3 DUP2 MSTORE PUSH1 0x20 DUP2 ADD DUP5 DUP5 DUP5 ADD GT ISZERO PUSH2 0x331 JUMPI PUSH2 0x330 PUSH2 0x65B JUMP JUMPDEST JUMPDEST PUSH2 0x33C DUP5 DUP3 DUP6 PUSH2 0x553 JUMP JUMPDEST POP SWAP4 SWAP3 POP POP POP JUMP JUMPDEST PUSH1 0x0 DUP3 PUSH1 0x1F DUP4 ADD SLT PUSH2 0x359 JUMPI PUSH2 0x358 PUSH2 0x656 JUMP JUMPDEST JUMPDEST DUP2 CALLDATALOAD PUSH2 0x369 DUP5 DUP3 PUSH1 0x20 DUP7 ADD PUSH2 0x302 JUMP JUMPDEST SWAP2 POP POP SWAP3 SWAP2 POP POP JUMP JUMPDEST PUSH1 0x0 PUSH1 0x20 DUP3 DUP5 SUB SLT ISZERO PUSH2 0x388 JUMPI PUSH2 0x387 PUSH2 0x665 JUMP JUMPDEST JUMPDEST PUSH1 0x0 DUP3 ADD CALLDATALOAD PUSH8 0xFFFFFFFFFFFFFFFF DUP2 GT ISZERO PUSH2 0x3A6 JUMPI PUSH2 0x3A5 PUSH2 0x660 JUMP JUMPDEST JUMPDEST PUSH2 0x3B2 DUP5 DUP3 DUP6 ADD PUSH2 0x344 JUMP JUMPDEST SWAP2 POP POP SWAP3 SWAP2 POP POP JUMP JUMPDEST PUSH1 0x0 DUP1 PUSH1 0x40 DUP4 DUP6 SUB SLT ISZERO PUSH2 0x3D2 JUMPI PUSH2 0x3D1 PUSH2 0x665 JUMP JUMPDEST JUMPDEST PUSH1 0x0 DUP4 ADD CALLDATALOAD PUSH8 0xFFFFFFFFFFFFFFFF DUP2 GT ISZERO PUSH2 0x3F0 JUMPI PUSH2 0x3EF PUSH2 0x660 JUMP JUMPDEST JUMPDEST PUSH2 0x3FC DUP6 DUP3 DUP7 ADD PUSH2 0x344 JUMP JUMPDEST SWAP3 POP POP PUSH1 0x20 DUP4 ADD CALLDATALOAD PUSH8 0xFFFFFFFFFFFFFFFF DUP2 GT ISZERO PUSH2 0x41D JUMPI PUSH2 0x41C PUSH2 0x660 JUMP JUMPDEST JUMPDEST PUSH2 0x429 DUP6 DUP3 DUP7 ADD PUSH2 0x344 JUMP JUMPDEST SWAP2 POP POP SWAP3 POP SWAP3 SWAP1 POP JUMP JUMPDEST PUSH1 0x0 PUSH2 0x43E DUP3 PUSH2 0x52C JUMP JUMPDEST PUSH2 0x448 DUP2 DUP6 PUSH2 0x537 JUMP JUMPDEST SWAP4 POP PUSH2 0x458 DUP2 DUP6 PUSH1 0x20 DUP7 ADD PUSH2 0x562 JUMP JUMPDEST PUSH2 0x461 DUP2 PUSH2 0x66A JUMP JUMPDEST DUP5 ADD SWAP2 POP POP SWAP3 SWAP2 POP POP JUMP JUMPDEST PUSH1 0x0 PUSH2 0x477 DUP3 PUSH2 0x52C JUMP JUMPDEST PUSH2 0x481 DUP2 DUP6 PUSH2 0x548 JUMP JUMPDEST SWAP4 POP PUSH2 0x491 DUP2 DUP6 PUSH1 0x20 DUP7 ADD PUSH2 0x562 JUMP JUMPDEST DUP1 DUP5 ADD SWAP2 POP POP SWAP3 SWAP2 POP POP JUMP JUMPDEST PUSH1 0x0 PUSH2 0x4A9 DUP3 DUP5 PUSH2 0x46C JUMP JUMPDEST SWAP2 POP DUP2 SWAP1 POP SWAP3 SWAP2 POP POP JUMP JUMPDEST PUSH1 0x0 PUSH1 0x20 DUP3 ADD SWAP1 POP DUP2 DUP2 SUB PUSH1 0x0 DUP4 ADD MSTORE PUSH2 0x4CE DUP2 DUP5 PUSH2 0x433 JUMP JUMPDEST SWAP1 POP SWAP3 SWAP2 POP POP JUMP JUMPDEST PUSH1 0x0 PUSH2 0x4E0 PUSH2 0x4F1 JUMP JUMPDEST SWAP1 POP PUSH2 0x4EC DUP3 DUP3 PUSH2 0x5C7 JUMP JUMPDEST SWAP2 SWAP1 POP JUMP JUMPDEST PUSH1 0x0 PUSH1 0x40 MLOAD SWAP1 POP SWAP1 JUMP JUMPDEST PUSH1 0x0 PUSH8 0xFFFFFFFFFFFFFFFF DUP3 GT ISZERO PUSH2 0x516 JUMPI PUSH2 0x515 PUSH2 0x627 JUMP JUMPDEST JUMPDEST PUSH2 0x51F DUP3 PUSH2 0x66A JUMP JUMPDEST SWAP1 POP PUSH1 0x20 DUP2 ADD SWAP1 POP SWAP2 SWAP1 POP JUMP JUMPDEST PUSH1 0x0 DUP2 MLOAD SWAP1 POP SWAP2 SWAP1 POP JUMP JUMPDEST PUSH1 0x0 DUP3 DUP3 MSTORE PUSH1 0x20 DUP3 ADD SWAP1 POP SWAP3 SWAP2 POP POP JUMP JUMPDEST PUSH1 0x0 DUP2 SWAP1 POP SWAP3 SWAP2 POP POP JUMP JUMPDEST DUP3 DUP2 DUP4 CALLDATACOPY PUSH1 0x0 DUP4 DUP4 ADD MSTORE POP POP POP JUMP JUMPDEST PUSH1 0x0 JUMPDEST DUP4 DUP2 LT ISZERO PUSH2 0x580 JUMPI DUP1 DUP3 ADD MLOAD DUP2 DUP5 ADD MSTORE PUSH1 0x20 DUP2 ADD SWAP1 POP PUSH2 0x565 JUMP JUMPDEST DUP4 DUP2 GT ISZERO PUSH2 0x58F JUMPI PUSH1 0x0 DUP5 DUP5 ADD MSTORE JUMPDEST POP POP POP POP JUMP JUMPDEST PUSH1 0x0 PUSH1 0x2 DUP3 DIV SWAP1 POP PUSH1 0x1 DUP3 AND DUP1 PUSH2 0x5AD JUMPI PUSH1 0x7F DUP3 AND SWAP2 POP JUMPDEST PUSH1 0x20 DUP3 LT DUP2 EQ ISZERO PUSH2 0x5C1 JUMPI PUSH2 0x5C0 PUSH2 0x5F8 JUMP JUMPDEST JUMPDEST POP SWAP2 SWAP1 POP JUMP JUMPDEST PUSH2 0x5D0 DUP3 PUSH2 0x66A JUMP JUMPDEST DUP2 ADD DUP2 DUP2 LT PUSH8 0xFFFFFFFFFFFFFFFF DUP3 GT OR ISZERO PUSH2 0x5EF JUMPI PUSH2 0x5EE PUSH2 0x627 JUMP JUMPDEST JUMPDEST DUP1 PUSH1 0x40 MSTORE POP POP POP JUMP JUMPDEST PUSH32 0x4E487B7100000000000000000000000000000000000000000000000000000000 PUSH1 0x0 MSTORE PUSH1 0x22 PUSH1 0x4 MSTORE PUSH1 0x24 PUSH1 0x0 REVERT JUMPDEST PUSH32 0x4E487B7100000000000000000000000000000000000000000000000000000000 PUSH1 0x0 MSTORE PUSH1 0x41 PUSH1 0x4 MSTORE PUSH1 0x24 PUSH1 0x0 REVERT JUMPDEST PUSH1 0x0 DUP1 REVERT JUMPDEST PUSH1 0x0 DUP1 REVERT JUMPDEST PUSH1 0x0 DUP1 REVERT JUMPDEST PUSH1 0x0 DUP1 REVERT JUMPDEST PUSH1 0x0 PUSH1 0x1F NOT PUSH1 0x1F DUP4 ADD AND SWAP1 POP SWAP2 SWAP1 POP JUMP INVALID LOG2 PUSH5 0x6970667358 0x22 SLT KECCAK256 ORIGIN CALL LT PUSH2 0xDD8B SWAP11 0x4B PUSH26 0x73BD5501980050C025014C885D319DFEE178DC9DDFF1C064736F PUSH13 0x63430008070033000000000000 \",\r\n"
            + "\t\"sourceMap\": \"35:281:0:-:0;;;;;;;;;;;;;;;;;;;\"\r\n"
            + "}";

    public static final String FUNC_GETVALUEBYKEY = "getValueByKey";

    public static final String FUNC_SETTXS = "setTxs";

    public static final String FUNC_TXS = "txs";

    @Deprecated
    protected Demo(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected Demo(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected Demo(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected Demo(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public RemoteFunctionCall<TransactionReceipt> getValueByKey(String key) {
        final Function function = new Function(
                FUNC_GETVALUEBYKEY, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(key)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> setTxs(String key, String value) {
        final Function function = new Function(
                FUNC_SETTXS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(key), 
                new org.web3j.abi.datatypes.Utf8String(value)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<String> txs(String param0) {
        final Function function = new Function(FUNC_TXS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    @Deprecated
    public static Demo load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new Demo(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static Demo load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new Demo(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static Demo load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new Demo(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static Demo load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new Demo(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<Demo> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(Demo.class, web3j, credentials, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<Demo> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(Demo.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    public static RemoteCall<Demo> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(Demo.class, web3j, transactionManager, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<Demo> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(Demo.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }
}
