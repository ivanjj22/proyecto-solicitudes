
package com.empre.facade;
import com.empre.persistencia.Tipo;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class TipoFacade extends AbstractFacade<Tipo>{
    @PersistenceContext(unitName = "ProyectoSolicitudesPU")
    private EntityManager em;

    public TipoFacade() {
        super(Tipo.class);
    }
    
    @Override
    protected EntityManager getEntityManager() {
        return this.em;
    }


    public void persist(Object object){
        em.persist(object);
    }
}
