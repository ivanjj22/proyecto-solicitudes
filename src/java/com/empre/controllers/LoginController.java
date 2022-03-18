/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.empre.controllers;

import com.empre.models.Login;
import java.io.Serializable;
//import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Ivan Jaimes
 */
@Named(value = "loginController")
//@RequestScoped
public class LoginController implements Serializable{
    
    @Inject
    private Login obj; 

    /**
     * Creates a new instance of LoginController
     */
    public LoginController() {
    }
    
    
    public Login getSelected() {
        if (obj == null) {
            obj = new Login();
        }
        return obj;
    }
    
    public String login(){
        String pagina ="login";
        
        try {
            HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            request.login(obj.getUsuario(), obj.getClave());

            if(request.isUserInRole("admin")){
                pagina="/app/admin/principal";
            }
            if(request.isUserInRole("user")){
                pagina="/app/user/principal?faces-redirect=true";
            }
            
        
        } catch (Exception e) {
            e.printStackTrace();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Usuario o clave invalido"));
        }
        return pagina;
    }

    public String logout() {
        String result = "/faces/login/login.xhtml?faces-redirect=true";     

        try {
            FacesContext context = FacesContext.getCurrentInstance();
            HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
            request.logout();
        } catch (ServletException e) {
            e.printStackTrace();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error al cerrar cesion"));
        }

        return result;
    }
    
}
