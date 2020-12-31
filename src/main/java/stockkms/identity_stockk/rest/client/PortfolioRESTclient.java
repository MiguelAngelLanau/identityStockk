package stockkms.identity_stockk.rest.client;

import javax.ws.rs.ClientErrorException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;
import stockkms.identity_stockk.common.RESTstockkService;

/**
 * Jersey REST client generated for REST resource:PorfolioRESTService
 * [portfolio]<br>
 * USAGE:
 * <pre>
 *        PortfolioRESTclient client = new PortfolioRESTclient();
 *        Object response = client.XXX(...);
 *        // do whatever with response
 *        client.close();
 * </pre>
 *
 * @author fsern
 */
public class PortfolioRESTclient {

    private WebTarget webTarget;
    private Client client;
    private String BASE_URI = "";

    public PortfolioRESTclient() {
        ServicesDirectoryRESTclient directoryRESTclient = new ServicesDirectoryRESTclient();
        RESTstockkService portfolioService = directoryRESTclient.getService("stockk_PORTFOLIO");
        directoryRESTclient.close();
        if (portfolioService!=null){
            BASE_URI = portfolioService.getUri();
        }
        client = javax.ws.rs.client.ClientBuilder.newClient();
        webTarget = client.target(BASE_URI).path("portfolio");
    }

    
    
    
    public void deletePortfolio(String _token) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("{0}", new Object[]{_token})).request().delete();
    }
//    public void deletePortfolio(Object requestEntity, String token) throws ClientErrorException {
//        webTarget.path(java.text.MessageFormat.format("{0}", new Object[]{token})).request(javax.ws.rs.core.MediaType.APPLICATION_JSON).delete(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON));
//    }

    public <T> T getPortfolio(Class<T> responseType, String _token) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("{0}", new Object[]{_token}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public void sellStock(Object requestEntity, String _token) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("{0}", new Object[]{_token})).request(javax.ws.rs.core.MediaType.APPLICATION_JSON).put(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON));
    }

    public void buyStock(Object requestEntity, String _token) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("{0}", new Object[]{_token})).request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON));
    }

    public void close() {
        client.close();
    }
    
}
