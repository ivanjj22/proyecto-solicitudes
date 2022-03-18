

package utilities;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;


public class PutMessage {
    
    
    public static void adderror(String resume, String detail){
        FacesMessage fcmsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, resume, detail);
        FacesContext.getCurrentInstance().addMessage(null, fcmsg);
    }
    
    public static void addsuccess(String resume, String detail){
        FacesMessage fcmsg = new FacesMessage(FacesMessage.SEVERITY_INFO, resume, detail);
        FacesContext.getCurrentInstance().addMessage("successInfo",fcmsg);
    }
}
