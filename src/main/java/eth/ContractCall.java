package eth;

import org.web3j.crypto.CipherException;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.admin.Admin;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.RawTransactionManager;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;
import org.web3j.tx.gas.StaticGasProvider;

import java.io.IOException;
import java.math.BigInteger;

/**
 * @Author archer_oneee
 * @Description //TODO None
 * @Date @date 2023-01-13 08:11
 **/
public class ContractCall {
    //RPC访问地址,由节点设置,请替换成自己节点的rpc地址
    private  final String RPC_URL = "http://127.0.0.1:8545";
    //Web3j客户端
    private  final Web3j web3j = Web3j.build(new HttpService(RPC_URL));
    //Admin管理端

    private  final Admin adminWeb3j = Admin.build(new HttpService(RPC_URL));
    //有余额的钱包地址

    private  final String address = "0x0633cdde3Aeb18Ca46cDFc1E4e6DB11F063a91c4";
    private  final String password = "abc123";
    //钱包地址解锁密码
    private  final String to = "0x1ef3c8b97c47ba09d01be4a0cf633d9f08ef4cd1";
    //转出的钱包地址
    private  final String filePath = "D:\\geth\\db1\\keystore";
    //有余额的钱包地址私钥文件路径
    private  final long chainId = 8434L;
    //创世区块json文件中的chainId
    private  final BigInteger gasPrice = new BigInteger("5000");
    // 燃料消耗
    private  final BigInteger gasLimit = new BigInteger("3000000");
    //消耗限制
    private  final String contractAddress = "0x2c3851f305Eb356544d8D2283321E81Cc116ac01";
    //合约地址,部署合约成功后会返回该地址


    String fileName = "UTC--2022-02-23T02-48-13.380412700Z--0edbb47293907cdc5229f853c194b696134b8816";
    Credentials credentials = WalletUtils.loadCredentials(password, filePath + "\\" + fileName);


    ContractGasProvider contractGasProvider = new StaticGasProvider(gasPrice, gasLimit);
    TransactionManager transactionManager = new RawTransactionManager(web3j, credentials, chainId);
    Demo contract = Demo.load(contractAddress, web3j, transactionManager, contractGasProvider);

    public ContractCall() throws IOException, CipherException {
    }


    public void setTxs(String key, String value){
        try{
            TransactionReceipt result = contract.setTxs(key,value).send();

        }catch (Exception e){
            e.printStackTrace();
        }
        // System.out.println(result.getTransactionHash());//打印交易hash
    }

    public String getValueByKey(String key)  {
        try{
            String value = contract.txs(key).send();
            return value;
        }catch (Exception e){
            e.printStackTrace();
        }
            return null;
    }


}
