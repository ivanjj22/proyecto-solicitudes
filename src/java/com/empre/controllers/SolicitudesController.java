
package com.empre.controllers;

import com.empre.facade.SolicitudFacade;
import com.empre.persistencia.Solicitud;
import java.io.Serializable;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.model.SelectItem;
import utilities.Forms;
import utilities.PutMessage;
import com.empre.beans.LoginBean;
import com.empre.facade.UsuarioFacade;
import com.empre.persistencia.Usuario;
import javax.faces.bean.ManagedProperty;

@SessionScoped
@ManagedBean (name = "solicitudesController")
public class SolicitudesController implements Serializable {
    @EJB
    private SolicitudFacade solicitudFacade;
    private Solicitud objCurrent;
    
    @EJB
    private UsuarioFacade usuarioFacade;
    private Usuario objCurrentusuario;
       

    public SolicitudesController() {
    }
    
    public Solicitud getSelected() {
        if (this.objCurrent == null) {
            this.objCurrent = new Solicitud();
        }
        return this.objCurrent;
    }  


    private SolicitudFacade getFacade(){
        return this.solicitudFacade;
    }
    
    public String busquelo(){
        return "listado";
    }
    
    public String obtenerNombreusuario(){
        FacesContext context = FacesContext.getCurrentInstance();
        LoginBean bean = context.getApplication().evaluateExpressionGet(context, "#{loginbean}", LoginBean.class);
        return bean.getUsuario();
    }
    
    public Usuario consultapersonalizada(){
        return usuarioFacade.listadopersonalizado(obtenerNombreusuario());
    }
    
    
    public String addSolicitud() {
        Calendar fecha = Calendar.getInstance();
        int hora = fecha.get(Calendar.HOUR_OF_DAY);
        int minutos = fecha.get(Calendar.MINUTE);
        int segundos = fecha.get(Calendar.SECOND);
        objCurrent.setFechasolicitud(new Date());
        objCurrent.setHorasolicitud(new Time(hora, minutos, segundos));
        objCurrent.setEstado(1);

        this.objCurrentusuario=consultapersonalizada();
        
        objCurrent.setCodusuario_solicita(objCurrentusuario);

        try {
            
            String resume, detail;
            detail = "exito";
            resume = "registro exitoso";
            getFacade().add(objCurrent);
            PutMessage.addsuccess(resume, detail);
            objCurrent= new Solicitud();
            return "crear";
        } catch (Exception e) {
            e.printStackTrace();
            String resume, detail;
            detail = e.getMessage();
            resume = "hay un error";
            PutMessage.adderror(resume, detail);
            return null;
        }
        
    }
    
    public String deteleSolicitud(Solicitud obj) {
        try{
            this.objCurrent=  obj;
            String resume,detail;
            detail=ResourceBundle.getBundle("/utilities/SolicitudLabels").getString("SolicitudMsgBorrarExito");
            resume="borrado exitoso";
            getFacade().delete(objCurrent);
            this.objCurrent= new Solicitud();
            PutMessage.addsuccess(resume, detail);
            return "administrar";
        }catch(Exception e){
            String resume,detail;
            detail=e.getMessage();
            resume="hay un error";
            PutMessage.adderror(resume, detail);
            return null;
        }
    }
    

    
    
    public List<Solicitud> mirelos(){
        return solicitudFacade.loadAll();
    }
    
    public String viewSolicitud(Solicitud obj, String page){
        this.objCurrent = obj;
        return page;
    }
    
    
    
    public String updateSolicitud() {
        try{
            String resume,detail;
            detail=ResourceBundle.getBundle("/utilities/SolicitudLabels").getString("SolicitudMsgEditarExito");
            resume="editado exitoso";
            getFacade().update(objCurrent);
            PutMessage.addsuccess(resume, detail);
            objCurrent= new Solicitud();
            return "administrar";
        }catch(Exception e){
            String resume,detail;
            detail=e.getMessage();
            resume="hay un error";
            PutMessage.adderror(resume, detail);
            return null;
        }
    }
    
    public SelectItem[] loadlist(String value){
         Forms frm = new Forms();
        return frm.addObject(solicitudFacade.loadAll(), value);
    }
    
    //*************************************************************************
    //Interface Converter
    //*************************************************************************
    @FacesConverter(forClass = Solicitud.class, value = "solicitudConverter")
    public static class SolicitudControllerConverter implements Converter{

        @Override
        public Object getAsObject(FacesContext context, UIComponent component, String value) {
            try{
                int id = Integer.parseInt(value);
                SolicitudesController controller = (SolicitudesController) context.getApplication().getELResolver().
                        getValue(context.getELContext(), null, "solicitudesController");
                return controller.solicitudFacade.find(id);
            }catch(NumberFormatException e){
                //putMessage.addError("Error", e.getMessage());
                return null;
            }
        }

        @Override
        public String getAsString(FacesContext context, UIComponent component, Object value) {
            if (value instanceof Solicitud){
                Solicitud obj = (Solicitud) value;
                return String.valueOf(obj.getCodsolicitud());
            }
            return null;
        }
    }    
    
    
}
