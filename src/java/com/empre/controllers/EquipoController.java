/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.empre.controllers;


//import com.empre.ejb.EquipoFacadeRemote;
import com.empre.facade.EquipoFacade;
import com.empre.persistencia.Equipo;
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
@ManagedBean (name = "equipoController")
public class EquipoController implements Serializable{
    @EJB
    private EquipoFacade equipoFacade;
    private Equipo objCurrent; 

    public EquipoController() {
    }
    
    public Equipo getSelected() {
        if (this.objCurrent == null) {
            this.objCurrent = new Equipo();
        }
        return this.objCurrent;
    }
    
    private EquipoFacade getFacade(){
        return this.equipoFacade;
    }
    
     public String busquelo(){
        return "listado";
    }
    public String addEquipo() {
        try {
            String resume, detail;
            detail = "exito";
            resume = "registro exitoso";
            getFacade().add(objCurrent);
            PutMessage.addsuccess(resume, detail);
            this.objCurrent = new Equipo();
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
    
      public List<Equipo> mirelos(){
        return equipoFacade.loadAll();
    }
    
    public String deteleEquipo(Equipo obj) {
        try{
            this.objCurrent=obj;
            
            String resume,detail;
            detail=ResourceBundle.getBundle("/utilities/EquipoLabels").getString("EquipoMsgBorrarExito");
            resume="borrado exitoso";
            getFacade().delete(objCurrent);
            this.objCurrent= new Equipo();
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
    
    public String updateEquipo() {
 
        try{
            String resume,detail;
            detail=ResourceBundle.getBundle("/utilities/EquipoLabels").getString("EquipoMsgEditarExito");
            resume="editado exitoso";
            getFacade().update(objCurrent);
            PutMessage.addsuccess(resume, detail);
            objCurrent= new Equipo();
            return "administrar";
        }catch(Exception e){
            String resume,detail;
            detail=e.getMessage();
            resume="hay un error";
            PutMessage.adderror(resume, detail);
            return null;
        }
    }
    
    public String viewEquipo(Equipo obj, String page){
        this.objCurrent = obj;
        return page;
    }
    

    public SelectItem[] loadlist(String value){
         Forms frm = new Forms();
        return frm.addObject(equipoFacade.loadAll(), value);
    }
    
    //*************************************************************************
    //Interface Converter
    //*************************************************************************
    @FacesConverter(forClass = Equipo.class, value = "equipoConverter")
    public static class EquipoControllerConverter implements Converter{

        @Override
        public Object getAsObject(FacesContext context, UIComponent component, String value) {
            try{
                int id = Integer.parseInt(value);
                EquipoController controller = (EquipoController) context.getApplication().getELResolver().
                        getValue(context.getELContext(), null, "equipoController");
                return controller.equipoFacade.find(id);
            }catch(NumberFormatException e){
                //putMessage.addError("Error", e.getMessage());
                return null;
            }
        }

        @Override
        public String getAsString(FacesContext context, UIComponent component, Object value) {
            if (value instanceof Equipo){
                Equipo obj = (Equipo) value;
                return String.valueOf(obj.getCodequipo());
            }
            return null;
        }
    }    
    
    
}
