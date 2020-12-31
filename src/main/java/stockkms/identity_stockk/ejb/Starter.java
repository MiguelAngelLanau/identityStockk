
package stockkms.identity_stockk.ejb;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import stockkms.identity_stockk.common.StatusCode;

/**
 *
 * @author fsernafortea
 */
@Singleton
@Startup
public class Starter {


    @EJB TouchTimer timer;
    

    
    @PostConstruct
    public void init(){  
        timer.touch(StatusCode.BOOTING);

        System.out.println ("================================================");       
        System.out.println ("======= Stockk === Plantilla-DEmo Service is UP!");       
        System.out.println ("================================================"); 
    }
    
     
    @PreDestroy
    public void byeBye(){
        
        timer.touch(StatusCode.DOWN);
        System.out.println ("--------------------------------------------------");       
        System.out.println ("======= Stockk === Plantilla-DEmo Service is DOWN!");    
        System.out.println ("--------------------------------------------------");       
    }
}
