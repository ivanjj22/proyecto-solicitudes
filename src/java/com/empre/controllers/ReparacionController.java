/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.empre.controllers;


import com.empre.facade.ReparacionFacade;
import com.empre.persistencia.Reparacion;
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

/**
 *
 * @author Ivan Jaimes
 */

@SessionScoped
@ManagedBean (name = "reparacionController")
public class ReparacionController implements Serializable{
    @EJB
    private ReparacionFacade reparacionFacade;
    private Reparacion objCurrent; 

    public ReparacionController() {
    }
    
    public Reparacion getSelected() {
        if (this.objCurrent == null) {
            this.objCurrent = new Reparacion();
        }
        return this.objCurrent;
    }
    
    private ReparacionFacade getFacade(){
        return this.reparacionFacade;
    }
    
     public String busquelo(){
        return "listado";
    }
    public String addReparacion() {
        try {
            Calendar fecha = Calendar.getInstance();
            int hora = fecha.get(Calendar.HOUR_OF_DAY);
            int minutos = fecha.get(Calendar.MINUTE);
            int segundos = fecha.get(Calendar.SECOND);
            int dia = fecha.get(Calendar.DAY_OF_MONTH);
            int mes = fecha.get(Calendar.MONTH);
            int ano = fecha.get(Calendar.YEAR)-1900;
            this.objCurrent.setFecha(new Date(ano, mes, dia));
            this.objCurrent.setHora(new Time(hora, minutos, segundos));
            
            String resume, detail;
            detail = "exito";
            resume = "registro exitoso";
            getFacade().add(objCurrent);
            PutMessage.addsuccess(resume, detail);
            this.objCurrent = new Reparacion();
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
    
      public List<Reparacion> mirelos(){
        return reparacionFacade.loadAll();
    }
    
    public String deteleReparacion(Reparacion obj) {
        try{
            this.objCurrent=obj;
            String resume,detail;
            detail=ResourceBundle.getBundle("/utilities/ReparacionLabels").getString("ReparacionMsgBorrarExito");
            resume="borrado exitoso";
            getFacade().delete(objCurrent);
            this.objCurrent= new Reparacion();
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
    
    public String updateReparacion() {
 
        try{
            String resume,detail;
            detail=ResourceBundle.getBundle("/utilities/ReparacionLabels").getString("ReparacionMsgEditarExito");
            resume="editado exitoso";
            getFacade().update(objCurrent);
            PutMessage.addsuccess(resume, detail);
            objCurrent= new Reparacion();
            return "administrar";
        }catch(Exception e){
            String resume,detail;
            detail=e.getMessage();
            resume="hay un error";
            PutMessage.adderror(resume, detail);
            return null;
        }
    }
    
    public String viewReparacion(Reparacion obj, String page){
        this.objCurrent = obj;
        return page;
    }
    

    public SelectItem[] loadlist(String value){
        return Forms.addObject(reparacionFacade.loadAll(), value);
    }
    
    //*************************************************************************
    //Interface Converter
    //*************************************************************************
    @FacesConverter(forClass = Reparacion.class, value = "reparacionConverter")
    public static class ReparacionControllerConverter implements Converter{

        @Override
        public Object getAsObject(FacesContext context, UIComponent component, String value) {
            try{
                int id = Integer.parseInt(value);
                ReparacionController controller = (ReparacionController) context.getApplication().getELResolver().
                        getValue(context.getELContext(), null, "reparacionController");
                return controller.reparacionFacade.find(id);
            }catch(NumberFormatException e){
                //putMessage.addError("Error", e.getMessage());
                return null;
            }
        }

        @Override
        public String getAsString(FacesContext context, UIComponent component, Object value) {
            if (value instanceof Reparacion){
                Reparacion obj = (Reparacion) value;
                return String.valueOf(obj.getCodreparacion());
            }
            return null;
        }
    }    
    
    
}
