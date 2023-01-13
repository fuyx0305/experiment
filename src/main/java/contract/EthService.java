package eth;

import org.web3j.protocol.Web3j;
import org.web3j.protocol.admin.Admin;
import org.web3j.protocol.admin.methods.response.PersonalUnlockAccount;
import org.web3j.protocol.core.Request;
import org.web3j.protocol.core.methods.response.EthBlockNumber;
import org.web3j.protocol.geth.Geth;
import org.web3j.protocol.http.HttpService;

import java.io.IOException;
import java.math.BigInteger;

/**
 * @Author archer_oneee
 * @Description //TODO None
 * @Date @date 2023-01-12 20:24
 **/
public class EthService {
    private static final String URL = "http://127.0.0.1:8545/";

    /**
     * 初始化web3j普通api调用
     *
     * @return web3j
     */
    public static Web3j initWeb3j() {
        return Web3j.build(getService());
    }

    /**
     * 初始化personal级别的操作对象
     * @return Geth
     */
    public static Geth initGeth(){
        return Geth.build(getService());
    }

    /**
     * 初始化admin级别操作的对象
     * @return Admin
     */
    public static Admin initAdmin(){
        return Admin.build(getService());
    }

    /**
     * 通过http连接到geth节点
     * @return
     */
    private static HttpService getService(){
        return new HttpService(URL);
    }


    public static BigInteger getCurrentBlockNumber() throws IOException {
        Web3j web3j = initWeb3j();
        Request<?, EthBlockNumber> request = web3j.ethBlockNumber();
        return request.send().getBlockNumber();
    }


    public static Boolean unlockAccount(String address, String password, BigInteger duration) throws IOException {
        Admin admin = initAdmin();
        Request<?, PersonalUnlockAccount> request = admin.personalUnlockAccount(address, password, duration);
        PersonalUnlockAccount account = request.send();
        return account.accountUnlocked();

    }

    public static void test(){
        Web3j web3j = initWeb3j();

    }

    public static void main(String[] args) throws IOException {
        EthService eth = new EthService();
        
        BigInteger height = EthService.getCurrentBlockNumber();
        System.out.println("height = " + height);
    }
}
