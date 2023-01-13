package RS;


import DH.entities.CertificateAuthority;
import RS.entities.CertificationAuthority;
import RS.entities.User;
import RS.entities.Verifier;
import eth.ContractCall;
import org.bouncycastle.math.ec.ECPoint;
import tools.MyECDSA;
import tools.MySha256;
import tools.PointOperation;
import tools.RingSignature;

import javax.jws.soap.SOAPBinding;
import java.math.BigInteger;

/**
 * @Author archer_oneee
 * @Description //TODO None
 * @Date @date 2023-01-12 16:36
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

    public void CredentialIssue(CertificationAuthority ca, User u){
        BigInteger x_uit = ca.poi.getRandoms(1)[0];
        ECPoint y_uit = ca.poi.multiplyG(x_uit).normalize();

        String S_it = MySha256.Sha256(u.id+y_uit.toString()+ca.k.toString());

        String m = y_uit.toString()+u.attr+S_it;
        ca.rs.sign(ca.rs.getYSet(5), m);
        RingSignature sigma_it = ca.rs;
        String tx_it = y_uit.toString() +"--"+ u.attr +"--"+ S_it +"--"+ sigma_it.getSigString();

        // store in the blockchain
        String addr_it = MySha256.Sha256(y_uit.toString()).substring(0,5);
        System.out.println("addr_it = " + addr_it);
        this.contract.setTxs(addr_it,tx_it);

        u.x_uit = x_uit;
        u.S_it = S_it;
        u.y_uit = y_uit;
        u.addr_it = addr_it;
        u.ca_sigma_it = sigma_it;

    }


    public void Authentication(CertificationAuthority ca, User u, Verifier v){
        int len = 5;
        ECPoint[] ySet = new ECPoint[len];
        ECPoint [] temp = ca.rs.getYSet(len-2);
        for (int i = 0; i < temp.length; i++) {
            ySet[i] = temp[i];
        }

        ySet[len-2] = u.y_uit;
        ySet[len-1] = v.keyPair.publicKey;

        String m = "request";
        u.sign(m,ySet);

        RingSignature sigma = u.rs;
        assert v.verify(m,ySet,sigma,u.addr_it,this.contract);
        // assert
        System.out.println("success");
    }


    public void UpdateCase1(CertificationAuthority ca, User u){
        // revoke tx_it
        String revoke = new String("revoke");

        String m = revoke+u.addr_it;
        ca.rs.sign(ca.rs.getYSet(5), m);
        RingSignature sigma_it = ca.rs;


        String tx_it_ = revoke + u.addr_it + sigma_it.toString();

        // generate new tx_it
        CredentialIssue(ca,u);
    }

    public void UpdateCase2(CertificationAuthority ca, User newU){
        // check S_it
        ca.checkS_it(newU);

        // generate new tx_it
        CredentialIssue(ca,newU);
    }


    public static void main(String[] args) {
        Scheme sch = new Scheme();
        CertificationAuthority ca = new CertificationAuthority();
        User u = new User("test","female",25);
        Verifier v = new Verifier();
        sch.CredentialIssue(ca,u);
        sch.Authentication(ca,u,v);
        sch.UpdateCase1(ca,u);
        u.age = 15;
        sch.UpdateCase2(ca,u);
    }

}


