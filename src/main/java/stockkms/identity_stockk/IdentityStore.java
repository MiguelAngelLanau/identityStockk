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
import jdk.internal.net.http.common.Pair;
import stockkms.identity_stockk.common.Configs;
import stockkms.identity_stockk.common.Hasher;
import stockkms.identity_stockk.common.RandomString;
import stockkms.identity_stockk.common.User;
import sun.security.krb5.KrbException;

/**
 *
 * @author Miguel Ángel Lanau
 */
public class IdentityStore {
    private static IdentityStore instancia = null;
    private Configs config;
    private Map<String, User> users;
    //private Map<String, Pair<RESTuser, Integer>> restUsers;
    private Map<String, RESTuser> restUsers;
    private Gson gson;
    private final String filePath ="identity_stockk/users.json";

    public IdentityStore() throws KrbException {
        try{
            users = new HashMap();
            restUsers = new HashMap();
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
        //File f = new File(filePath);
        if (! f.exists()){
            File fd = new File(config.getProp(Configs.IDENTITY_DIRECTORY_NAME));
            if (! fd.exists()){
                fd.mkdir();
            }
            save();
        }else{            
            Type type = new TypeToken<Map<String, User>>(){}.getType();
            
            if(gson.fromJson(new FileReader(f), type) != null){
                users = gson.fromJson(new FileReader(f), type);
            }
        }
    }

    private synchronized void save(){
        try {
            FileWriter fw = new FileWriter(config.getProp(Configs.IDENTITY_FILE_PATH));
            //FileWriter fw = new FileWriter(filePath);
            gson.toJson(users, fw);
            fw.flush();
            fw.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    public synchronized void createUser(RESTuser restUser, String clearPwd) {
        String login = restUser.getLogin();
        String name = restUser.getName();
        String password = Hasher.getSHA256(clearPwd);
        
        User user = new User(login, name, password);
        
        users.put(restUser.getLogin(), user);
        //Pair<RESTuser, Integer> tupla = new Pair<RESTuser, Integer>(restUser, 5);
        String token = generateToken();
        restUsers.put(token, restUser);
        save();
    }
    
    public synchronized RESTuser getUser(String token) {
        RESTuser restUser = restUsers.get(token);
        cancelToken(token);
        restUsers.put(generateToken(), restUser);
        return restUser;
    }
    
    public synchronized void changePassword(String token, String clearPwd) {
        RESTuser restUser = restUsers.get(token);
        if (restUser != null){
            User user = users.get(restUser.getLogin());
            user.setPassword(Hasher.getSHA256(clearPwd));
            users.put(restUser.getLogin(), user);
            save();
        }
    }
    
    public synchronized String getToken(String login, String pwd) {
        User user = users.get(login);
        if(user != null){
            if(user.getPassword().equals(Hasher.getSHA256(pwd))){
                String token = generateToken();
                restUsers.put(token, user.toRESTuser());
                return token;
            }else{
                return null;
            }
        }else{
            return null;
        }
    }
    
    public synchronized void cancelToken(String token){
        restUsers.remove(token);
    }
    
    private synchronized String generateToken(){
        return RandomString.getAlphaNumericString(50);
    }
}
