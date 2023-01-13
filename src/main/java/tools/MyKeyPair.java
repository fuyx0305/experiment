package tools;

import org.bouncycastle.math.ec.ECPoint;

import java.math.BigInteger;
import java.security.PublicKey;

/**
 * @Author archer_oneee
 * @Description //TODO None
 * @Date @date 2023-01-12 16:42
 **/
public class MyKeyPair {
    public BigInteger privateKey;
    public ECPoint publicKey;

    public MyKeyPair(BigInteger privateKey, ECPoint publicKey) {
        this.privateKey = privateKey;
        this.publicKey = publicKey;
    }
}
