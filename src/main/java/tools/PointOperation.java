package tools;

import org.bouncycastle.asn1.sec.SECNamedCurves;
import org.bouncycastle.asn1.x9.X9ECParameters;
import org.bouncycastle.crypto.params.ECDomainParameters;
import org.bouncycastle.math.ec.ECPoint;

import java.math.BigInteger;
import java.util.Random;

/**
 * @Author archer_oneee
 * @Description //TODO None
 * @Date @date 2023-01-07 22:44
 **/
public class PointOperation {
    long start ;
    long end ;
    int rounds=10000 ;
    BigInteger num;


    BigInteger []randomNums=new BigInteger[rounds];
    ECPoint G;
    ECPoint P;
    BigInteger q;
    X9ECParameters ecp;
    ECDomainParameters domainParameters;
    Random r;
    public PointOperation(){
        ecp = SECNamedCurves.getByName("secp256k1");
        domainParameters = new ECDomainParameters(ecp.getCurve(), ecp.getG(),ecp.getN(),ecp.getH(),ecp.getSeed());

        G = domainParameters.getG();
        // p = new BigInteger("FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFEFFFFFC2F",16);
        q = ecp.getN();

        r = new Random();
        num = new BigInteger(q.bitLength(),r);
        P = G;

    }


    public ECPoint multiplyG(BigInteger num){
        return  G.multiply(num);
    }

    public ECPoint multiply(ECPoint P,BigInteger num){
        return  P.multiply(num);
    }



    public ECPoint add(ECPoint A, ECPoint B){

        return A.add(B);
    }




    public void inverse(BigInteger num){
        num.modInverse(q);
    }


    public float inversetTest(){
        start =System.currentTimeMillis();
        for (int i = 0; i < rounds; i++) {
            this.inverse(randomNums[i]);
        }
        end =System.currentTimeMillis();
        float cost = end-start;
        float ave = cost/rounds;
        return ave;
    }

    public BigInteger[] getRandoms(int num){
        BigInteger[] results= new BigInteger[num];
        for (int i = 0; i < num; i++) {
            results[i] = new BigInteger(q.bitLength(),r);
        }
        randomNums = results;
        return results;
    }

    public BigInteger getRandoms(){
        return new BigInteger(q.bitLength(),r);
    }

    public static void main(String[] args) {
        PointOperation poi = new PointOperation();
        BigInteger sk = poi.getRandoms(1)[0];
        ECPoint pk = poi.multiplyG(sk);
        ECPoint pk_ = poi.multiply(poi.G,sk);
        ECPoint pk_G = poi.add(pk,poi.G);
        pk_G = pk_G.normalize();

        ECPoint pk_G_ = poi.multiplyG(sk.add(new BigInteger("1")));
        pk_G_ = pk_G_.normalize();
        // boolean result = pk_G.equals(pk_G_);
        // System.out.println("result = " + result);
        int a = 1;
    }

    // public static void main(String[] args) {
    //
    //     PointOperation poi = new PointOperation();
    //     BigInteger[] randomNums = poi.getRandoms(poi.rounds);
    //     int len = poi.p.toByteArray().length;
    //     System.out.println("len = " + len);
    //     poi.G.getAffineXCoord().bitLength();
    //     float inv_test = poi.inversetTest();
    //     System.out.println("inv_test = " + inv_test);
    //     inv_test = poi.inversetTest();
    //     System.out.println("inv_test = " + inv_test);
    //
    //     Float addTest = poi.addTest();
    //     System.out.println("addTest = " + addTest);
    //     addTest = poi.addTest();
    //     System.out.println("addTest = " + addTest);
    //
    //     poi.num= new BigInteger("5");
    //     Float mulTest = poi.multiplyTest();
    //     System.out.println("mulTest = " + mulTest);
    //     mulTest = poi.multiplyTest();
    //     System.out.println("mulTest = " + mulTest);
    //
    //
    //
    //
    // }


}
