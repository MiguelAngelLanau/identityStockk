package stockkms.identity_stockk;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;
import stockkms.identity_stockk.common.RESTuser;
import java.util.Map;
import stockkms.identity_stockk.common.Configs;
import sun.security.krb5.Config;
import sun.security.krb5.KrbException;

/**
 *
 * @author Miguel Ángel Lanau
 */
public class IdentityStore {
    private static IdentityStore instancia = null;
    private Configs config;
    private Map<String, RESTuser> users;
    private Gson gson;
    //private final String filePath ="identity_stockk/users.json";

    public IdentityStore() throws KrbException {
        try{
            users = new HashMap();
            config = Configs.getInstance();
            gson = new Gson();
            
            load();        
        }catch (FileNotFoundException ex){
            System.err.println(ex);
        }
    }
    
    public static IdentityStore getInstance() throws KrbException{
        if (instancia == null){
            instancia = new IdentityStore();
        }
        return instancia;
    }
    
    private void load() throws FileNotFoundException{
        File f = new File(config.getProp(Configs.IDENTITY_FILE_PATH));
        if (! f.exists()){
            File fd = new File(config.getProp(Configs.IDENTITY_DIRECTORY_NAME));
            if (! fd.exists()){
                fd.mkdir();
            }
            save();
        }else{
            Type type = new TypeToken<Map<String, RESTuser>>(){}.getType();
            
            users = gson.fromJson(new FileReader(f), type);
        }
    }

    private synchronized void save(){
        try {
            FileWriter fw = new FileWriter(config.getProp(Configs.IDENTITY_FILE_PATH));          
            gson.toJson(users, fw);
            fw.flush();
            fw.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
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
