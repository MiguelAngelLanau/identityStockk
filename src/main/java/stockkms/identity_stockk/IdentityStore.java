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
import stockkms.identity_stockk.common.Pair;
import java.util.Map;
import java.util.concurrent.TimeUnit;
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
    private static final int MAX_TOKEN_TIME = 5;
    private static IdentityStore instancia = null;
    private Configs config;
    private Map<String, User> users;
    private Map<String, Pair<RESTuser, Long>> restUsers;
    private Gson gson;

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
            
            gson.toJson(users, fw);
            fw.flush();
            fw.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    public synchronized void createUser(RESTuser restUser, String clearPwd) {
        Pair<RESTuser, Long> tupla;
        
        String login = restUser.getLogin();
        String name = restUser.getName();
        String password = Hasher.getSHA256(clearPwd);
        
        User user = new User(login, name, password);
        
        users.put(restUser.getLogin(), user);
        String token = generateToken();
        tupla = new Pair<>(restUser, System.currentTimeMillis());
        restUsers.put(token, tupla);
        save();
    }
    
    public synchronized RESTuser getUser(String token) {
        Pair<RESTuser, Long> tupla;
        RESTuser restUser;
        Long lastMs;
        
        tupla = restUsers.get(token);
        
        if(tupla != null){
            restUser = tupla.first;
            lastMs = tupla.second;
            
            if(TimeUnit.MILLISECONDS.toMinutes(
                    System.currentTimeMillis() - lastMs) < MAX_TOKEN_TIME){
                
                tupla = new Pair<>(restUser, System.currentTimeMillis());
                restUsers.put(token, tupla);
            
                return restUser;
            }else{
                cancelToken(token);
                return null;
            }
        }else{
            return null;
        }
    }
    
    public synchronized void changePassword(String token, String clearPwd) {
        Pair<RESTuser, Long> tupla;
        
        tupla = restUsers.get(token);
        if (tupla != null){
            User user = users.get(tupla.first.getLogin());
            user.setPassword(Hasher.getSHA256(clearPwd));
            users.put(tupla.first.getLogin(), user);
            save();
        }
    }
    
    public synchronized String getToken(String login, String pwd) {
        User user;
        Pair<RESTuser, Long> tupla;
        
        user = users.get(login);
        if(user != null){
            if(user.getPassword().equals(Hasher.getSHA256(pwd))){
                String token = generateToken();
                tupla = new Pair<>(user.toRESTuser(), System.currentTimeMillis());
                restUsers.put(token, tupla);
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
