/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.empre.controllers;


//import com.empre.ejb.UsuarioFacadeRemote;
import com.empre.facade.UsuarioFacade;
import com.empre.persistencia.Usuario;
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
@ManagedBean (name = "usuarioController")
public class UsuarioController implements Serializable{
    @EJB
    private UsuarioFacade usuarioFacade;
    private Usuario objCurrent; 

    public UsuarioController() {
    }
    
    public Usuario getSelected() {
        if (this.objCurrent == null) {
            this.objCurrent = new Usuario();
        }
        return this.objCurrent;
    }
    
    private UsuarioFacade getFacade(){
        return this.usuarioFacade;
    }
    
    
       
    public String busquelo(){
        return "listado";
    }

    public String addUsuario() {
        try {
            String resume, detail;
            detail = "exito";
            resume = "registro exitoso";
            getFacade().add(objCurrent);
            PutMessage.addsuccess(resume, detail);
            this.objCurrent = new Usuario();
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
    public List<Usuario> mirelos(){
        return usuarioFacade.loadAll();
    }
    
    
    public String deteleUsuario(Usuario obj) {
        try{
            this.objCurrent=obj;
            String resume,detail;
            detail=ResourceBundle.getBundle("/utilities/UsuarioLabels").getString("UsuarioMsgBorrarExito");
            resume="borrado exitoso";
            getFacade().delete(objCurrent);
            this.objCurrent= new Usuario();
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
       public String viewUsuario(Usuario obj, String page){
       this.objCurrent=obj;
        
        return page;
    }
    
    public String updateUsuario() {
 
        try{
            String resume,detail;
            detail=ResourceBundle.getBundle("/utilities/UsuarioLabels").getString("UsuarioMsgEditarExito");
            resume="editado exitoso";  
            getFacade().update(objCurrent);
            PutMessage.addsuccess(resume, detail);
            objCurrent= new Usuario();
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
        return frm.addObject(usuarioFacade.loadAll(), value);
    }
    
    //*************************************************************************
    //Interface Converter
    //*************************************************************************
    @FacesConverter(forClass = Usuario.class, value = "usuarioConverter")
    public static class UsuarioControllerConverter implements Converter{

        @Override
        public Object getAsObject(FacesContext context, UIComponent component, String value) {
            try{
                int id = Integer.parseInt(value);
                UsuarioController controller = (UsuarioController) context.getApplication().getELResolver().
                        getValue(context.getELContext(), null, "usuarioController");
                return controller.usuarioFacade.find(id);
            }catch(NumberFormatException e){
                //putMessage.addError("Error", e.getMessage());
                return null;
            }
        }

        @Override
        public String getAsString(FacesContext context, UIComponent component, Object value) {
            if (value instanceof Usuario){
                Usuario obj = (Usuario) value;
                return String.valueOf(obj.getCodusuario());
            }
            return null;
        }
    }    
}
