
package stockkms.identity_stockk.common;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;


/**
 *
 * @author Félix Serna
 */
public class Hasher {

    public static String getSHA256(byte[] _msg){
        String hash = null;
        byte[] digest = null;
        if (_msg!=null){
            try {
                MessageDigest md = MessageDigest.getInstance("SHA-256");
                md.update(_msg);
                digest = md.digest();
                hash = Base64.getEncoder().encodeToString(digest);
            } catch (NoSuchAlgorithmException ex) {
                ex.printStackTrace();
            }
        } // if !null
        return hash;
    }
    
    public static String getSHA256(String _txt){
        String hash = null;
        byte[] msg = null;
        byte[] digest = null;
        if (_txt!=null){
            msg = _txt.getBytes(StandardCharsets.UTF_16);
            hash = getSHA256(msg);
        } // if !null
        return hash;
    }
    
}
