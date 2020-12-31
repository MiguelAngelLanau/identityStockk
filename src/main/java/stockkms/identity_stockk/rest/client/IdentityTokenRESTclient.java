package stockkms.identity_stockk.rest.client;

import javax.ws.rs.ClientErrorException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;
import stockkms.identity_stockk.common.RESTstockkService;

/**
 * Jersey REST client generated for REST resource:TokenRESTService [token]<br>
 * USAGE:
 * <pre>
 *        TokenRESTclient client = new TokenRESTclient();
 *        Object response = client.XXX(...);
 *        // do whatever with response
 *        client.close();
 * </pre>
 *
 * @author fsern
 */
public class IdentityTokenRESTclient {

    private WebTarget webTarget;
    private Client client;
    private String BASE_URI = "";

    public IdentityTokenRESTclient() {
        ServicesDirectoryRESTclient directoryRESTclient = new ServicesDirectoryRESTclient();
        RESTstockkService identityService = directoryRESTclient.getService("stockk_IDENTITY");
        directoryRESTclient.close();
        if (identityService!=null){
            BASE_URI = identityService.getUri();
        }
        client = javax.ws.rs.client.ClientBuilder.newClient();
        webTarget = client.target(BASE_URI).path("token");        
    }

    public String getToken(String _login, String _password) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("{0}/{1}", new Object[]{_login, _password}));
        //return resource.get(String.class);
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(String.class);
    }

    public void cancelToken(String token) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("{0}", new Object[]{token})).request().delete();
    }

    public void close() {
        client.close();
    }
    
}
