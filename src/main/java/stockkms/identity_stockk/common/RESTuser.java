
package stockkms.identity_stockk.common;


import com.google.gson.Gson;
import java.io.Serializable;

/**
 * This user is meant to be the REST payload.
 * It does not contain the DB primaryKey, password, nor the privateKey.
 * 
 * @author fserna
 */
public class RESTuser implements Serializable {

    private String login;
    private String name;
    
                                    
    public RESTuser(){
        
    }
    
    public RESTuser(String _login, String _name){
        this.login      = _login;
        this.name       = _name;
    }
    
    /**
     * @return the login
     */
    public String getLogin() {
        return login;
    }


    
    /**
     * @return the name
     */
    public String getName() {
        return name;
    }



    public String toJson(){
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    /**
     * @param login the login to set
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

}
