package eth;

import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.methods.response.*;
import org.web3j.protocol.http.HttpService;

import java.math.BigInteger;
import java.util.List;

/**
 * @Author archer_oneee
 * @Description //TODO None
 * @Date @date 2023-01-12 20:35
 **/
public class Test {
    private Web3j web3j;
    private Credentials credentials;    //第一个账户的私钥
    private String CONTRACT_ADDRESS = "0x187c81F78Faf7EBE68AF673ED2ab35b640dc6bA9";   //部署的合约地址
    // private static MyContract_sol_MyContract contract;
    private BigInteger ethBase = BigInteger.valueOf(10).pow(18);   // 1 eth = 10^18 wei

    public Test(){
        try {
            web3j = Web3j.build(new HttpService("http://localhost:7545"));  //本地的ganache gui
            // credentials = Credentials.create("4e1b6ab1b2a5f477c416eb275ac5aac4e1257002782f0c3e74f3e8747db30f05");  //直接填写第一个账户的私钥
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    // //加载已经部署的合约
    // public void loadContract(){
    //     System.out.println("Going to load smart contract");
    //     try {
    //         contract = MyContract_sol_MyContract.load(
    //                 CONTRACT_ADDRESS, web3j, credentials,
    //                 new BigInteger("22000000000"), new BigInteger("510000"));
    //         System.out.println("Load smart contract done!");
    //
    //
    //     } catch(Exception e){
    //         e.printStackTrace();
    //     }
    // }


    //=====================  测试以太坊环境的方法  =====================
    public void GetClientVersion(){
        try {
            Web3ClientVersion version = web3j.web3ClientVersion().sendAsync().get();
            System.out.println("version : " + version.getWeb3ClientVersion());
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    //获得最新的块数，大整数类型
    public BigInteger getLatestBlockNumber() {
        EthBlockNumber result = new EthBlockNumber();
        try {
            result = this.web3j.ethBlockNumber()
                    .sendAsync()
                    .get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result.getBlockNumber();
    }

    //获得所有以太坊账户地址，这里是10个测试账户
    public List<String> getEthAccounts() {
        EthAccounts result = new EthAccounts();
        try {
            result = this.web3j.ethAccounts()
                    .sendAsync()
                    .get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result.getAccounts();
    }

    //获得交易总数，是大整数类型
    public BigInteger getTransactionCount() {
        EthGetTransactionCount result = new EthGetTransactionCount();
        try {
            result = this.web3j.ethGetTransactionCount(this.CONTRACT_ADDRESS,
                    DefaultBlockParameter.valueOf("latest"))
                    .sendAsync()
                    .get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result.getTransactionCount();
    }

    //获得某个账户余额，大整数类型
    public BigInteger getAccountBalance(String contractAddress) {
        EthGetBalance result = new EthGetBalance();
        try {
            this.web3j.ethGetBalance(contractAddress,
                    DefaultBlockParameter.valueOf("latest"))
                    .sendAsync()
                    .get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result.getBalance();  //报错：org.web3j.exceptions.MessageDecodingException: Value must be in format 0x[1-9]+[0-9]* or 0x0
    }



    public static void main(String args[]){
        Test test = new Test();
        // 测试ganache的功能
        test.GetClientVersion();   //查看测试环境版本
        System.out.println("最新块数：" + test.getLatestBlockNumber());    //查看最新块数
        System.out.println("交易总数：" + test.getTransactionCount());     //查看交易总数
        System.out.println("所有账户地址如下：");
        List<String> accs = test.getEthAccounts();          //打印所有账户
        // for (int i = 0; i < accs.size(); i ++)
        //     System.out.println(accs.get(i));
        // //System.out.println("第10个账户余额" + test.getAccountBalance(accs.get(9)));   //查看第10个账户余额

        // test.loadContract();    //加载已经部署的MyContract合约


    }


}
