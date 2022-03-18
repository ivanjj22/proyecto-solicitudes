package com.empre.facade;

import com.empre.persistencia.Equipo;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


@Stateless
public class EquipoFacade extends AbstractFacade<Equipo> {

    @PersistenceContext(unitName = "ProyectoSolicitudesPU")
    private EntityManager em;
    
    public EquipoFacade() {
        super(Equipo.class);
    }
       

    @Override
    protected EntityManager getEntityManager() {
        return this.em;
    }

    
}
