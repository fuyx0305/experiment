package tools;

import org.bouncycastle.crypto.KeyGenerationParameters;
import org.bouncycastle.math.ec.ECPoint;

import java.math.BigInteger;

/**
 * @Author archer_oneee
 * @Description //TODO None
 * @Date @date 2023-01-12 16:39
 **/
public class RingSignature {
    public BigInteger[] s;
    public ECPoint[] ySet;
    public MyKeyPair keyPair;
    public String []t;
    public PointOperation poi= new PointOperation();
    public void keyGen(){
        BigInteger sk = poi.getRandoms(1)[0];
        ECPoint pk = poi.multiplyG(sk);
        keyPair = new MyKeyPair(sk,pk);
    }

    public void keyGen(BigInteger sk, ECPoint pk){
        ECPoint pk_ = poi.multiplyG(sk);
        assert pk_.equals(pk);
        keyPair = new MyKeyPair(sk,pk);
    }


    public void sign(ECPoint []ySet, String m, int k){

        int n = ySet.length;
        BigInteger r = poi.getRandoms(1)[0];
        BigInteger []s = poi.getRandoms(n);
        String []t = new String[n];


        ySet[k] = keyPair.publicKey;

        String PK = "";
        for (int i = 0; i < n; i++) {
            PK = PK + ySet[i].toString();
        }

        ECPoint g_r = poi.multiplyG(r);

        t[(k+1)%n] = MySha256.Sha256(PK + m + g_r.normalize().toString());


        for (int j = (k+2)%n; j != (k+1)%n; j = (j+1)%n) {
            // System.out.println("j = " + j);
            int index = ((j-1)+n)%n;
            // System.out.println("index = " + index);
            ECPoint g_s_j1 = poi.multiplyG(s[index]);
            BigInteger t_j1 = new BigInteger(t[index],16).mod(poi.q);
            ECPoint y_t_j1 = poi.multiply(ySet[index],t_j1);
            ECPoint gy = poi.add(g_s_j1,y_t_j1);
            // System.out.println("gy = " + gy);
            t[j%n] = MySha256.Sha256(PK+m+gy.normalize().toString());
        }
        BigInteger t_k = new BigInteger(t[k],16).mod(poi.q);

        s[k] = r.subtract(keyPair.privateKey.multiply(t_k).mod(poi.q)).mod(poi.q);
        ECPoint g_s_j1 = poi.multiplyG(s[k]);
        BigInteger t_j1 = new BigInteger(t[k],16).mod(poi.q);
        ECPoint y_t_j1 = poi.multiply(ySet[k],t_j1);
        ECPoint gy = poi.add(g_s_j1,y_t_j1);
        String t_ = MySha256.Sha256(PK+m+gy.normalize().toString());


        this.t = t;
        this.ySet = ySet;
        this.s=s;

    }

    public void sign(ECPoint []ySet, String m){
        
        int n = ySet.length;
        BigInteger r = poi.getRandoms(1)[0];
        BigInteger []s = poi.getRandoms(n);
        String []t = new String[n];
        
        BigInteger k_ = poi.getRandoms(1)[0].mod(new BigInteger(String.valueOf(n)));
        int k = k_.intValue() ;
        // System.out.println("k = " + k);
        ySet[k] = keyPair.publicKey;

        String PK = "";
        for (int i = 0; i < n; i++) {
            PK = PK + ySet[i].toString();
        }

        ECPoint g_r = poi.multiplyG(r);

        t[(k+1)%n] = MySha256.Sha256(PK + m + g_r.normalize().toString());


        for (int j = (k+2)%n; j != (k+1)%n; j = (j+1)%n) {
            // System.out.println("j = " + j);
            int index = ((j-1)+n)%n;
            // System.out.println("index = " + index);
            ECPoint g_s_j1 = poi.multiplyG(s[index]);
            BigInteger t_j1 = new BigInteger(t[index],16).mod(poi.q);
            ECPoint y_t_j1 = poi.multiply(ySet[index],t_j1);
            ECPoint gy = poi.add(g_s_j1,y_t_j1);
            // System.out.println("gy = " + gy);
            t[j%n] = MySha256.Sha256(PK+m+gy.normalize().toString());
        }
        BigInteger t_k = new BigInteger(t[k],16).mod(poi.q);

        s[k] = r.subtract(keyPair.privateKey.multiply(t_k).mod(poi.q)).mod(poi.q);
        ECPoint g_s_j1 = poi.multiplyG(s[k]);
        BigInteger t_j1 = new BigInteger(t[k],16).mod(poi.q);
        ECPoint y_t_j1 = poi.multiply(ySet[k],t_j1);
        ECPoint gy = poi.add(g_s_j1,y_t_j1);
        String t_ = MySha256.Sha256(PK+m+gy.normalize().toString());


        this.t = t;
        this.ySet = ySet;
        this.s=s;

    }

    public boolean verify(String m, ECPoint[]ySet, String t0,BigInteger[]s){
        int n = ySet.length;
        String PK = "";
        for (int i = 0; i < n; i++) {
            PK = PK + ySet[i].toString();
        }

        String[] t = new String[n];
        t[0] = t0;

        for (int j = 1; j != 0; j = (j+1)%n) {
            int index = ((j-1)+n)%n;
            ECPoint g_s_j1 = poi.multiplyG(s[index]);
            BigInteger t_j1 = new BigInteger(t[index],16).mod(poi.q);
            ECPoint y_t_j1 = poi.multiply(ySet[index],t_j1);
            ECPoint gy = poi.add(g_s_j1,y_t_j1);
            t[j] = MySha256.Sha256(PK+m+gy.normalize().toString());
        }
        ECPoint g_s_j1 = poi.multiplyG(s[n-1]);
        BigInteger t_j1 = new BigInteger(t[n-1],16).mod(poi.q);
        ECPoint y_t_j1 = poi.multiply(ySet[n-1],t_j1);
        ECPoint gy = poi.add(g_s_j1,y_t_j1);
        String t0_ = MySha256.Sha256(PK+m+gy.normalize().toString());
        assert t0.equals(t0_);
        return true;
    }

    public String getSigString(){
        String sig = this.t[0]+"==";
        for (int i = 0; i < this.s.length; i++) {
            sig += s[i].toString();
            sig += "==";
        }
        return sig;
    }

    public void desSig(String sigma_it) {
        String split = "==";
        String[] values = sigma_it.split(split);
        this.t[0] = values[0];
        for (int i = 1; i < values.length; i++) {
            this.s[i-1] = new BigInteger(values[i]);
        }

    }

    public ECPoint[] getYSet(int num){
        ECPoint[] ySet = new ECPoint[num];
        BigInteger[] nums = poi.getRandoms(num);
        for (int i = 0; i < nums.length; i++) {
            ySet[i] = poi.multiplyG(nums[i]).normalize();
        }
        return ySet;
    }

    public static void main(String[] args) {
        RingSignature rs = new RingSignature();
        rs.keyGen();
        String m = "abc";
        ECPoint[] ySet = rs.getYSet(5);
        rs.sign(ySet,m);
        rs.verify(m,rs.ySet,rs.t[0],rs.s);

    }


}
