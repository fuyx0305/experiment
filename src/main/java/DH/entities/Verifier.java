package DH.entities;

import eth.ContractCall;
import tools.MyECDSA;

import java.nio.charset.StandardCharsets;

/**
 * @Author archer_oneee
 * @Description //TODO None
 * @Date @date 2023-01-12 15:26
 **/
public class Verifier {
    public String test;


    public String getTx_it(String addr_it){
        return null;
    }

    public boolean checkTx_it(User u, CertificateAuthority ca, ContractCall contract){
        String tx_it = contract.getValueByKey(u.addr_it);
        String split = "--";
        String[] messages = tx_it.split(split);
        String y_uit = messages[0];
        assert !y_uit.equals("revoke");
        String attr = messages[1];
        String s_it = messages[2];
        String ca_sigma_it = messages[3];
        String m = u.y_uit.normalize().toString()+u.attr+u.S_it;
        String m_ = y_uit + attr + s_it;
        boolean result = m_.equals(m);
        byte[] sigma = ca_sigma_it.getBytes(StandardCharsets.ISO_8859_1);

        if (MyECDSA.verify(ca.signKey.getPublic(),m, ca_sigma_it.getBytes(StandardCharsets.ISO_8859_1))){
            return true;
        }else {
            return false;
        }
    }

}
