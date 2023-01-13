package RS.entities;

import tools.MyECDSA;
import tools.MySha256;
import tools.PointOperation;
import tools.RingSignature;

import java.math.BigInteger;
import java.security.Key;
import java.security.KeyPair;

/**
 * @Author archer_oneee
 * @Description //TODO None
 * @Date @date 2023-01-12 16:37
 **/
public class CertificationAuthority {
    public PointOperation poi;
    public RingSignature rs;
    public BigInteger k;

    public CertificationAuthority() {
        this.poi = new PointOperation();
        this.rs = new RingSignature();
        this.rs.keyGen();
        this.k = poi.getRandoms(1)[0];
    }

    public void checkS_it(User u) {
        String S_it = u.S_it;
        String S_it_ = MySha256.Sha256(u.id+u.y_uit+k);
        assert S_it==S_it_;
    }
}
