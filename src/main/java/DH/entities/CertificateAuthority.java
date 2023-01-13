package DH.entities;

import DH.entities.User;
import org.bouncycastle.math.ec.ECPoint;
import tools.MyECDSA;
import tools.MySha256;
import tools.PointOperation;

import java.math.BigInteger;
import java.security.KeyPair;

/**
 * @Author archer_oneee
 * @Description //TODO None
 * @Date @date 2023-01-12 14:43
 **/
public class CertificateAuthority {
    public KeyPair signKey;
    public PointOperation poi;
    public BigInteger k;

    public CertificateAuthority() {
        poi = new PointOperation();

        k = poi.getRandoms(1)[0];

        this.signKey = MyECDSA.keyGen();

    }

    public void checkS_it(User u){
        String S_it = u.S_it;
        String S_it_ = MySha256.Sha256(u.id+u.y_uit+k);
        assert S_it==S_it_;
    }


    public void Authentication(){

    }

}
