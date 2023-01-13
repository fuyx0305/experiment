package RS.entities;

import org.bouncycastle.math.ec.ECPoint;
import tools.MySha256;
import tools.RingSignature;

import java.math.BigInteger;

/**
 * @Author archer_oneee
 * @Description //TODO None
 * @Date @date 2023-01-12 14:37
 **/
public class User {
    public String name;
    public String gender;
    public int age;
    public String attr;
    public String id;
    public String addr_it;
    public RingSignature ca_sigma_it;
    public BigInteger x_uit;
    public ECPoint y_uit;
    public String S_it;
    public RingSignature rs;


    public User(String name, String gender, int age) {
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.attr = name+"||"+gender+"||"+String.valueOf(age);
        this.id = MySha256.Sha256(attr+"salt");
        this.rs = new RingSignature();
    }

    public ECPoint computeRt(ECPoint v_it){
        return v_it.multiply(x_uit);
    }

    public void sign(String m, ECPoint[] ySet){
        this.rs.keyGen(this.x_uit,this.y_uit);
        this.rs.sign(ySet,m,ySet.length-2);
    }
}
