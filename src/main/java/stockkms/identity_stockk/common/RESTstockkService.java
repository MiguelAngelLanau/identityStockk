
package stockkms.identity_stockk.common;


import com.google.gson.Gson;
import java.io.Serializable;


public class RESTstockkService implements Serializable {

    private String name;
    private String uri;
    private StatusCode status = StatusCode.UNDEFINED;
    private Long ms;
    private Long howMsOld;

    public Long getHowMsOld() {
        return howMsOld;
    }
    
    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

 
    /**
     * @return the uri
     */
    public String getUri() {
        return uri;
    }

    /**
     * @param uri the uri to set
     */
    public void setUri(String uri) {
        this.uri = uri;
    }

   

    /**
     * @return the status
     */
    public StatusCode getStatus() {
        return status;
    }

    /**
     * @param _status the status to set
     */
    public void setStatus(StatusCode _status) {
        this.status = _status;
    }

        public Long getMs() {
        return ms;
    }

    public void setMs(Long ms) {
        this.ms = ms;
    }
    
    
    public String toJson(){
        Gson gson = new Gson();
        return gson.toJson(this);
    }
    
}
