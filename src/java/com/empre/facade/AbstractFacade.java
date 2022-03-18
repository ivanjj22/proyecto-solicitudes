package com.empre.facade;

import java.util.List;
import javax.persistence.EntityManager;

public abstract class AbstractFacade<T> {
    //la T es un parametro que viene, de afuera de qe clase de cualquiera
    private Class<T> entityClass;
    protected abstract EntityManager getEntityManager();

    public AbstractFacade(Class<T> entityClass) {
        this.entityClass = entityClass;
    }
    
    public void add(T entity){
        getEntityManager().persist(entity);
    }
    
    
    public void update(T entity){
        getEntityManager().merge(entity);
    }
    
    public void delete(T entity){
        getEntityManager().remove(getEntityManager().merge(entity));
    }
    
    public List<T> loadAll(){
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        return getEntityManager().createQuery(cq).getResultList();
    }

    public T find (Object id){
        return getEntityManager().find(entityClass, id);
    }
}
