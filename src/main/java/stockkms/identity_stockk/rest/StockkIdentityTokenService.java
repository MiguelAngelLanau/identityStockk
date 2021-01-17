/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stockkms.identity_stockk.rest;

import stockkms.identity_stockk.common.RESTuser;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;
import stockkms.identity_stockk.IdentityStore;

@Path("token")
@RequestScoped
public class StockkIdentityTokenService {
    @Context
    private UriInfo context;
    private IdentityStore identityStore;
    
    public StockkIdentityTokenService(){
        identityStore = IdentityStore.getInstance();
    }
    
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("{login}/{clearPwd}")
    public String getToken(@PathParam("login") String login, 
                         @PathParam("clearPwd") String clearPwd){
        return identityStore.getToken(login, clearPwd);
    }
    
    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("{token}")
    public void cancelToken(@PathParam("token") String token){
        identityStore.cancelToken(token);
    }
}
