package stockkms.identity_stockk;

import com.google.gson.Gson;
import java.util.HashMap;
import stockkms.identity_stockk.common.RESTuser;
import java.util.Map;
import sun.security.krb5.Config;

/**
 *
 * @author Miguel Ángel Lanau
 */
public class IdentityStore {
    private static IdentityStore instancia = null;
    private Config config;
    private Map<String, RESTuser> users;
    private Gson gson;

    public IdentityStore() {
        users = new HashMap();
    }
    
    public static IdentityStore getInstance(){
        if (instancia == null){
            instancia = new IdentityStore();
        }
        return instancia;
    }
    
    public synchronized void createUser(RESTuser user, String clearPwd) {
        users.put(getToken(user.getLogin(), clearPwd), user);
    }
    
    public synchronized RESTuser getUser(String token) {
        return users.get(token);
    }
    
    public synchronized void changePassword(String token, String clearPwd) {
        RESTuser user = users.get(token);
        if (user != null){
            users.remove(token);
            users.put(getToken(user.getLogin(), clearPwd), user);
        }
    }
    
    public synchronized String getToken(String login, String pwd) {
        return "";
    }
    
    public synchronized void cancelToken(String token){
        users.remove(token);
    }
}
