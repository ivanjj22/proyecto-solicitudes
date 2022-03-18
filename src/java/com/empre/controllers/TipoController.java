/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.empre.controllers;

//import com.empre.ejb.TipoFacadeRemote;
import com.empre.facade.TipoFacade;
import com.empre.persistencia.Tipo;
import java.io.Serializable;
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
@ManagedBean (name = "tipoController")
public class TipoController implements Serializable{
    
    @EJB
    private TipoFacade tipoFacade;
    private Tipo objCurrent; 

    public TipoController() {
    }
    
    public Tipo getSelected() {
        if (this.objCurrent == null) {
            this.objCurrent = new Tipo();
        }
        return this.objCurrent;
    }
    
    private TipoFacade getFacade(){
        return this.tipoFacade;
    }
    
    public String busquelo(){
        return "listado";
    }
    
    public String addTipo() {
        String resume, detail;
        try {
            detail = "exito";
            resume = "registro exitoso";
            getFacade().add(objCurrent);
            PutMessage.addsuccess(resume, detail);
            objCurrent= new Tipo();
            return "crear";
        } catch (Exception e) {
            e.printStackTrace();
            detail = e.getMessage();
            resume = "hay un error";
            PutMessage.adderror(resume, detail);
            return null;
        }
    }
    
    public List<Tipo> mirelos(){
        return tipoFacade.loadAll();
    }
    
    public String deteleTipo(Tipo obj) {
        String resume,detail;
        try{
            this.objCurrent=obj;
            detail=ResourceBundle.getBundle("/utilities/TipoLabels").getString("TipoMsgBorrarExito");
            resume="borrado exitoso";
            getFacade().delete(objCurrent);
            this.objCurrent= new Tipo();
            PutMessage.addsuccess(resume, detail);
            return "administrar";
        }catch(Exception e){
            detail=e.getMessage();
            resume="hay un error";
            PutMessage.adderror(resume, detail);
            return null;
        }
    }
    
    public String viewTipo(Tipo obj, String page){
       this.objCurrent=obj;
        
        return page;
    }
    
    public String updateTipo() { 
        try{
            String resume,detail;
            detail=ResourceBundle.getBundle("/utilities/TipoLabels").getString("TipoMsgEditarExito");
            resume="editado exitoso";
            getFacade().update(objCurrent);
            PutMessage.addsuccess(resume, detail);
            objCurrent= new Tipo();
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
        return frm.addObject(tipoFacade.loadAll(), value);
    }
    
    //*************************************************************************
    //Interface Converter
    //*************************************************************************
    @FacesConverter(forClass = Tipo.class, value = "tipoConverter")
    public static class TipoControllerConverter implements Converter{

        @Override
        public Object getAsObject(FacesContext context, UIComponent component, String value) {
            try{
                int id = Integer.parseInt(value);
                TipoController controller = (TipoController) context.getApplication().getELResolver().
                        getValue(context.getELContext(), null, "tipoController");
                return controller.tipoFacade.find(id);
            }catch(NumberFormatException e){
                //putMessage.addError("Error", e.getMessage());
                return null;
            }
        }

        @Override
        public String getAsString(FacesContext context, UIComponent component, Object value) {
            if (value instanceof Tipo){
                Tipo obj = (Tipo) value;
                return String.valueOf(obj.getCodtipo());
            }
            return null;
        }
    }    
}
