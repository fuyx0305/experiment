package eth;

import RS.Scheme;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.RawTransaction;
import org.web3j.crypto.TransactionEncoder;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.admin.Admin;
import org.web3j.protocol.admin.methods.response.PersonalUnlockAccount;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.Request;
import org.web3j.protocol.core.methods.response.EthGetBalance;
import org.web3j.protocol.core.methods.response.EthGetTransactionCount;
import org.web3j.protocol.core.methods.response.EthSendTransaction;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.RawTransactionManager;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;
import org.web3j.tx.gas.StaticGasProvider;
import org.web3j.utils.Convert;
import org.web3j.utils.Numeric;

import java.math.BigInteger;

/**
 * @Author archer_oneee
 * @Description //TODO None
 * @Date @date 2023-01-12 22:10
 **/
public class Web3jDemo {
    //RPC访问地址,由节点设置,请替换成自己节点的rpc地址
    private static final String RPC_URL = "http://127.0.0.1:8545";
    //Web3j客户端
    private static final Web3j web3j = Web3j.build(new HttpService(RPC_URL));
    private static final Admin adminWeb3j = Admin.build(new HttpService(RPC_URL));//Admin管理端
    private static final String address = "0x0633cdde3Aeb18Ca46cDFc1E4e6DB11F063a91c4";//有余额的钱包地址
    private static final String password = "abc123";//钱包地址解锁密码
    private static final String to = "0x1ef3c8b97c47ba09d01be4a0cf633d9f08ef4cd1";//转出的钱包地址
    private static final String filePath = "D:\\geth\\db1\\keystore";//有余额的钱包地址私钥文件路径
    private static final long chainId = 8434L;//创世区块json文件中的chainId
    private static final BigInteger gasPrice = new BigInteger("5000");//燃料消耗
    private static final BigInteger gasLimit = new BigInteger("3000000");//消耗限制
    private static final String contractAddress = "0x1e8d663765Fde2688811171D9b81B850eEb9Dd0c";//合约地址,部署合约成功后会返回该地址

    //测试主函数
    public static void main(String[] args) throws Exception {
//        getAge(to);
//        sendTransaction();
        excuteContract();
    }

    // 查询钱包余额
    public static void getAge(String address) throws Exception {
        // 这里要填写真实的钱包地址
        EthGetBalance ethGetBalance = web3j
                .ethGetBalance(address, DefaultBlockParameterName.LATEST).send();
        if (ethGetBalance != null) {
            System.out.println("余额:" + Convert.fromWei(ethGetBalance.getBalance().toString(), Convert.Unit.ETHER));
        }
    }

    // 发送转账交易
    public static void sendTransaction() throws Exception {
        PersonalUnlockAccount personalUnlockAccount = adminWeb3j.personalUnlockAccount(address, password).send();
        if (personalUnlockAccount.accountUnlocked()) {
            BigInteger gasPrice = web3j.ethGasPrice().sendAsync().get().getGasPrice();
            System.out.println(gasPrice);
            // send a transaction
            EthGetTransactionCount ethGetTransactionCount = web3j.ethGetTransactionCount(address, DefaultBlockParameterName.LATEST).sendAsync().get();
            BigInteger nonce = ethGetTransactionCount.getTransactionCount();
            RawTransaction rawTransaction = RawTransaction.createEtherTransaction(nonce, new BigInteger("5000"), new BigInteger("300000"), to, new BigInteger("3"));
            // 对交易进行签名和编码：
            // String fileName = WalletUtils.generateNewWalletFile(password, new File(filePath));
            String fileName = "UTC--2021-10-12T07-07-33.666057600Z--ffd4a30c08d9f16f095f6315e14bd034f7d04fb0";
            Credentials credentials = WalletUtils.loadCredentials(password, filePath + "\\" + fileName);
            byte[] signedMessage = TransactionEncoder.signMessage(rawTransaction, chainId, credentials);
            String hexValue = Numeric.toHexString(signedMessage);
            EthSendTransaction ethSendTransaction = web3j.ethSendRawTransaction(hexValue).sendAsync().get();
            String transactionHash = ethSendTransaction.getTransactionHash();
            System.out.println("transactionHash:" + transactionHash);
        }
    }

    // 执行合约方法
    public static void excuteContract() throws Exception {
        // 私钥文件名
        String fileName = "UTC--2023-01-12T14-34-53.775532400Z--0633cdde3aeb18ca46cdfc1e4e6db11f063a91c4";
        Credentials credentials = WalletUtils.loadCredentials(password, filePath + "\\" + fileName);
        ContractGasProvider contractGasProvider = new StaticGasProvider(gasPrice, gasLimit);
        TransactionManager transactionManager = new RawTransactionManager(web3j, credentials, chainId);
        Demo contract = Demo.load(contractAddress, web3j, transactionManager, contractGasProvider);

        // System.out.println(contract.isValid());//验证合约是否有效,返回true代表有效

        // TransactionReceipt result = contract.setTxs("a","jxf").send();
        // System.out.println(result.getTransactionHash());//打印交易hash

        String value = contract.txs("a").send();
        System.out.println("value = " + value);
        // TransactionReceipt result =contract.getValueByKey("a").send();
        // Request recipt= web3j.
        // System.out.println(result);//打印返回数据
    }


    public void setTxs(String key, String value){

    }
}
