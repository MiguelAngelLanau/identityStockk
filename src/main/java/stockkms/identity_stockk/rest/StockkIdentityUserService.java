package stockkms.identity_stockk.rest;

import stockkms.identity_stockk.common.RESTuser;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;
import stockkms.identity_stockk.IdentityStore;
import sun.security.krb5.KrbException;

@Path("user")
@RequestScoped
public class StockkIdentityUserService {
    @Context
    private UriInfo context;
    private IdentityStore identityStore;
    
    public StockkIdentityUserService() throws KrbException{
        identityStore = IdentityStore.getInstance();
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("{clearPwd}")
    public void createUser(RESTuser user, @PathParam("clearPwd") String clearPwd){
        identityStore.createUser(user, clearPwd);
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{token}")
    public RESTuser getUser(@PathParam("token") String token) {        
        return identityStore.getUser(token);
    }
    
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("{token}/{newClearPwd}")
    public void changePassword(@PathParam("token") String token, 
                               @PathParam("newClearPwd") String clearPwd) {
        identityStore.changePassword(token, clearPwd);
    }
}
