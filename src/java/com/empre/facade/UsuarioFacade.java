/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.empre.facade;

import com.empre.persistencia.Usuario;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Ivan Jaimes
 */
@Stateless
public class UsuarioFacade extends AbstractFacade<Usuario>{

    @PersistenceContext(unitName = "ProyectoSolicitudesPU")
    private EntityManager em;
    
    public UsuarioFacade() {
        super(Usuario.class);
    }
       

    @Override
    protected EntityManager getEntityManager() {
        return this.em;
    }
    
    public Usuario listadopersonalizado(String seleccionado) {
        String sql;
        sql = "select * from usuario where nombreusuario="+"'"+seleccionado+"'";
        Query consulta = getEntityManager().createNativeQuery(sql, Usuario.class);
        Usuario u = (Usuario) consulta.getResultList().get(0);
        return u;
    }
}
