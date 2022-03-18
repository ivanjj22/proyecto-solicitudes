/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.empre.beans;

import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Ivan Jaimes
 */
@SessionScoped
@ManagedBean(name = "loginbean")
public class LoginBean implements Serializable {

    @ManagedProperty(value="#{usuario}")
    private String usuario;
    private String clave;

    public LoginBean() {
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String login() {
        String pagina = "login";

        try {
            HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            request.login(usuario, clave);
            if (request.isUserInRole("admin")) {
                pagina = "/app/admin/principal?faces-redirect=true";
            }
            if (request.isUserInRole("user")) {
                pagina = "/app/user/principal.xhtml";
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
