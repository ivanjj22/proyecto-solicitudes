package com.empre.facade;

import com.empre.persistencia.Reparacion;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class ReparacionFacade extends AbstractFacade<Reparacion> {

    @PersistenceContext(unitName = "ProyectoSolicitudesPU")
    private EntityManager em;
    
    public ReparacionFacade() {
        super(Reparacion.class);
    }
       

    @Override
    protected EntityManager getEntityManager() {
        return this.em;
    }

    
}
