
package stockkms.identity_stockk.ejb;


import javax.ejb.Schedule;
import javax.ejb.Stateless;
import javax.ws.rs.ClientErrorException;
import stockkms.identity_stockk.rest.client.ServicesDirectoryRESTclient;
import stockkms.identity_stockk.common.RESTstockkService;
import stockkms.identity_stockk.common.StatusCode;


@Stateless
public class TouchTimer {


    
    
    @Schedule(hour="*", minute = "*", second = "24", persistent = false)
    public void timer(){
        touch(StatusCode.NOMINAL);
    }
    
    
    public void touch(StatusCode _status) {
        RESTstockkService service = new RESTstockkService();
        service.setName("stockk_IDENTITY");
        service.setUri("http://155.210.71.106:7030/stockk-identity/rest");  // <== colocar la URL que corresponda a la aplicación
        service.setStatus(_status);
        service.setMs(System.currentTimeMillis());
        
        try{
            ServicesDirectoryRESTclient directory = new ServicesDirectoryRESTclient();
            directory.registerService(service);
            directory.close();
        }catch (ClientErrorException cee){
               System.out.println ("ERROR: cannot register to the serviceDirectory service");  
        }
    }


}
