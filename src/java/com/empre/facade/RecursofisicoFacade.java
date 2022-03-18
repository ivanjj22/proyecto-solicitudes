/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.empre.facade;
import com.empre.persistencia.Recursofisico;
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
public class RecursofisicoFacade extends AbstractFacade<Recursofisico> {
    
    @PersistenceContext(unitName = "ProyectoSolicitudesPU")
    private EntityManager em;

    public RecursofisicoFacade() {
        super(Recursofisico.class);
    }
    
    @Override
    protected EntityManager getEntityManager() {
        return this.em;
    }


    public void persist(Object object){
        em.persist(object);
    }
    
    public List<Recursofisico> listadopersonalizado() {
        String sql;
        sql = "SELECT u.nombreusuario FROM recursofisico as r INNER JOIN usuario as u ON u.codrecurso = r.codrecurso WHERE r.codrecurso = 44;";
        Query consulta = getEntityManager().createNativeQuery(sql);
        List<Recursofisico> arreglo = consulta.getResultList();
        return arreglo;
    }
   
}
