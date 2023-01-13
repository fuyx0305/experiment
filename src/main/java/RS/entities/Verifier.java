package RS.entities;

import DH.entities.CertificateAuthority;
import eth.ContractCall;
import org.bouncycastle.math.ec.ECPoint;
import tools.MyECDSA;
import tools.MyKeyPair;
import tools.PointOperation;
import tools.RingSignature;

import java.math.BigInteger;

/**
 * @Author archer_oneee
 * @Description //TODO None
 * @Date @date 2023-01-12 15:26
 **/
public class Verifier {
    public String test;
    public PointOperation poi;
    public MyKeyPair keyPair;

    public Verifier(){
        poi = new PointOperation();
        BigInteger sk = poi.getRandoms();
        keyPair = new MyKeyPair(sk,poi.multiplyG(sk));
    }

    public String getTx_it(String addr_it){
        return null;
    }

    public boolean verify(String m, ECPoint[] ySet, RingSignature sigma) {


        if(sigma.verify(m,ySet,sigma.t[0],sigma.s)){
            return true;
        }
        return false;
    }

    public boolean verify(String m, ECPoint[] ySet, RingSignature sigma, String addr_it, ContractCall contract) {
        String value = contract.getValueByKey(addr_it);
        String split = "--";
        String []messages = value.split(split);
        String y_uit = messages[0];
        assert !y_uit.equals("revoked");
        String attr = messages[1];
        String S_it = messages[2];
        String sigma_it = messages[3];
        sigma.desSig(sigma_it);
        String ca_m = y_uit+attr+S_it;

        sigma.verify(ca_m,sigma.ySet,sigma.t[0],sigma.s);

        if(sigma.verify(m,ySet,sigma.t[0],sigma.s)){
            return true;
        }
        return false;
    }

    // public boolean checkTx_it(User u, CertificateAuthority ca){
    //     String tx_it = new String("af");
    //     String m = u.y_uit.toString()+u.attr+u.S_it;
    //     if (MyECDSA.verify(ca.signKey.getPublic(),m, u.ca_sigma_it)){
    //         return true;
    //     }else {
    //         return false;
    //     }
    // }

}
