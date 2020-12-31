package stockkms.identity_stockk.rest.client;

import javax.ws.rs.ClientErrorException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;
import stockkms.identity_stockk.common.RESTstockkService;

/**
 * Jersey REST client generated for REST resource:ServicesDirectoryRESTservice
 * [directory]<br>
 * USAGE:
 * <pre>
 *        DirectoryRESTclient client = new DirectoryRESTclient();
 *        Object response = client.XXX(...);
 *        // do whatever with response
 *        client.close();
 * </pre>
 *
 * @author fsernafortea
 */
public class ServicesDirectoryRESTclient {
    private WebTarget webTarget;
    private Client client;
    private String BASE_URI = "http://155.210.71.100:8090/stockk-servicesDirectory/rest"; // <== o la que sea en su momento
    
    
    public ServicesDirectoryRESTclient() {
        client = javax.ws.rs.client.ClientBuilder.newClient();
        webTarget = client.target(BASE_URI).path("service");
    }

    
    public RESTstockkService getService(String _serviceName) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(_serviceName);
//System.out.println("stockk-UI ----"+resource.getUri().toString());        
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(RESTstockkService.class);
    }

    public RESTstockkService getServices() throws ClientErrorException {
        WebTarget resource = webTarget;
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(RESTstockkService.class);
    }

    public String registerService(RESTstockkService _service) {
        String answer = null;
        try{
            WebTarget resource = webTarget;
            answer = resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(_service, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
        }catch (ClientErrorException cee){
//System.out.println("ERROR EXCEPTION stockk-UI ----");      
        }
        return answer;
    }

    public void close() {
        client.close();
    }
    
}
