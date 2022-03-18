/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.empre.models;

import java.io.Serializable;
import javax.inject.Named;
//import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedProperty;

/**
 *
 * @author Ivan Jaimes
 */
@Named(value = "login")
//@RequestScoped
public class Login implements Serializable{
    
    @ManagedProperty("#{usuario}")
    private String usuario;
    private String clave;

    /**
     * Creates a new instance of Login
     */
    public Login() {
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

    


    
    
    
    
}
