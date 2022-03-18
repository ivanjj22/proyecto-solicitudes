/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.empre.facade;


import com.empre.persistencia.Solicitud;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Ivan Jaimes
 */
@Stateless
public class SolicitudFacade extends AbstractFacade<Solicitud>{
    
    @PersistenceContext(unitName = "ProyectoSolicitudesPU")
    private EntityManager em;

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    public SolicitudFacade() {
        super(Solicitud.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return this.em;
    }

    public void persist(Object object){
        em.persist(object);
    }
}
