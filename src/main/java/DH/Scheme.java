package DH;

import DH.entities.CertificateAuthority;
import DH.entities.User;
import DH.entities.Verifier;
import RS.entities.CertificationAuthority;
import eth.ContractCall;
import org.bouncycastle.math.ec.ECPoint;
import org.web3j.crypto.CipherException;
import tools.MyECDSA;
import tools.MySha256;
import tools.PointOperation;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

/**
 * @Author archer_oneee
 * @Description //TODO None
 * @Date @date 2023-01-12 15:28
 **/
public class Scheme {

    PointOperation poi ;
    ContractCall contract;

    public Scheme(){
        poi = new PointOperation();
        try {
            contract = new ContractCall();
        }catch (Exception e){
            e.printStackTrace();
        }
    }



    public void CredentialIssue(CertificateAuthority ca, User u){
        BigInteger x_uit = ca.poi.getRandoms(1)[0];
        ECPoint y_uit = ca.poi.multiplyG(x_uit);

        String S_it = MySha256.Sha256(u.id+y_uit.normalize().toString()+ca.k.toString());

        String m = y_uit.normalize().toString()+u.attr+S_it;
        byte[] sigma_it = MyECDSA.sign(m,ca.signKey.getPrivate());
        String sigma_it_str=new String(sigma_it, StandardCharsets.ISO_8859_1);
        String tx_it = y_uit.normalize().toString() +"--"+ u.attr +"--"+ S_it +"--"+ sigma_it_str;

        String addr_it = MySha256.Sha256(y_uit.normalize().toString()).substring(0,5);
        System.out.println("addr_it = " + addr_it);
        this.contract.setTxs(addr_it,tx_it);
        // store in the blockchain

        u.x_uit = x_uit;
        u.S_it = S_it;
        u.y_uit = y_uit;
        u.addr_it = addr_it;
        u.ca_sigma_it = sigma_it;

    }

    public boolean Authentication(User u,Verifier v, CertificateAuthority ca){
        String tx_it = v.getTx_it(u.addr_it);
        if (!v.checkTx_it(u,ca, contract)){
            return false;
        }
        BigInteger u_jt = poi.getRandoms(1)[0];
        ECPoint v_jt = poi.multiplyG(u_jt);

        ECPoint R_t = u.computeRt(v_jt);
        ECPoint R_t_ = u.y_uit.multiply(u_jt);
        assert R_t==R_t_;

        return true;
    }

    public void UpdateCase1(CertificateAuthority ca, User u){
        // revoke tx_it
        String revoke = new String("revoke");
        byte[] sigma_it_= MyECDSA.sign(revoke+u.addr_it,ca.signKey.getPrivate());
        String tx_it_ = new String("revoke") + u.addr_it + sigma_it_.toString();

        // generate new tx_it
        CredentialIssue(ca,u);
    }

    public void UpdateCase2(CertificateAuthority ca, User newU){
        // check S_it
        ca.checkS_it(newU);

        // generate new tx_it
        CredentialIssue(ca,newU);
    }

    public static void main(String[] args) {
        CertificateAuthority ca = new CertificateAuthority();
        User u = new User("test","male",20);
        Verifier v = new Verifier();
        Scheme sch = new Scheme();
        sch.CredentialIssue(ca, u);
        sch.Authentication(u,v,ca);
        sch.UpdateCase1(ca,u);
        sch.UpdateCase2(ca,u);

    }

}
