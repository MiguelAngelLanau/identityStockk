package stockkms.identity_stockk.rest;

import fs.stockk.ms.common.RESTuser;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

@Path("user")
@RequestScoped
public class StockkIdentityUserService {
    @Context
    private UriInfo context;
    
    public StockkIdentityUserService(){
        
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("{clearPwd}")
    public void createUser(RESTuser user, @PathParam("clearPwd") String token) {
        
    }
}
