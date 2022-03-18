/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.empre.controllers;

//import com.empre.ejb.RecursofisicoFacadeRemote;
import com.empre.facade.RecursofisicoFacade;
import com.empre.persistencia.Recursofisico;
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
@ManagedBean (name = "recursofisicoController")
public class RecursofisicoController implements Serializable{
    
    @EJB
    private RecursofisicoFacade recursofisicoFacade;
    private Recursofisico objCurrent; 

    public RecursofisicoController() {
    }
    
    public Recursofisico getSelected() {
        if (this.objCurrent == null) {
            this.objCurrent = new Recursofisico();
        }
        return this.objCurrent;
    }
    
    private RecursofisicoFacade getFacade(){
        return this.recursofisicoFacade;
    }
    
    public String busquelo(){
        return "listado";
    }
    
    public String addRecursofisico() {
        try {
            String resume, detail;
            detail = "exito";
            resume = "registro exitoso";
            getFacade().add(objCurrent);
            PutMessage.addsuccess(resume, detail);
            objCurrent= new Recursofisico();
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
    
    public List<Recursofisico> consultapersonalizada(){
        return recursofisicoFacade.listadopersonalizado();
    }
    
    public List<Recursofisico> mirelos(){
        return recursofisicoFacade.loadAll();
    }
    
    public String deteleRecursofisico(Recursofisico obj) {
        try{
            this.objCurrent=obj;
            
            String resume,detail;
            detail=ResourceBundle.getBundle("/utilities/RecursofisicoLabels").getString("RecursofisicoMsgBorrarExito");
            resume="borrado exitoso";
            getFacade().delete(objCurrent);
            this.objCurrent= new Recursofisico();
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
    
    public String viewRecursofisico(Recursofisico obj, String page){
       
        this.objCurrent = obj;
       
        return page;
    }
    
    public String updateRecursofisico() { 
        try{
            String resume,detail;
            detail=ResourceBundle.getBundle("/utilities/RecursofisicoLabels").getString("RecursofisicoMsgEditarExito");
            resume="editado exitoso";
            getFacade().update(objCurrent);
            objCurrent = null;
            PutMessage.addsuccess(resume, detail);
            objCurrent= new Recursofisico();
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
        return frm.addObject(recursofisicoFacade.loadAll(), value);
    }
    
    
    @FacesConverter(forClass = Recursofisico.class, value = "recursofisicoConverter")
    public static class RecursofisicoControllerConverter implements Converter{

        @Override
        public Object getAsObject(FacesContext context, UIComponent component, String value) {
            try{
                int id = Integer.parseInt(value);
                RecursofisicoController controller = (RecursofisicoController) context.getApplication().getELResolver().
                        getValue(context.getELContext(), null, "recursofisicoController");
                return controller.recursofisicoFacade.find(id);
            }catch(NumberFormatException e){
                //putMessage.addError("Error", e.getMessage());
                return null;
            }
        }

        @Override
        public String getAsString(FacesContext context, UIComponent component, Object value) {
            if (value instanceof Recursofisico){
                Recursofisico obj = (Recursofisico) value;
                return String.valueOf(obj.getCodrecurso());
            }
            return null;
        }
    }
}
