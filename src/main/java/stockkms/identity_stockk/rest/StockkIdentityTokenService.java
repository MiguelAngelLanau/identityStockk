package stockkms.identity_stockk.rest;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import stockkms.identity_stockk.IdentityStore;
import sun.security.krb5.KrbException;

@Path("token")
@RequestScoped
public class StockkIdentityTokenService {
    @Context
    private UriInfo context;
    private IdentityStore identityStore;
    
    public StockkIdentityTokenService() throws KrbException{
        identityStore = IdentityStore.getInstance();
    }
    
    @GET
    @Path("{login}/{clearPwd}")
    public String getToken(@PathParam("login") String login, 
                           @PathParam("clearPwd") String clearPwd){
        
        return identityStore.getToken(login, clearPwd);
    }
    
    @DELETE
    @Path("{token}")
    public void cancelToken(@PathParam("token") String token){
        identityStore.cancelToken(token);
    }
}
