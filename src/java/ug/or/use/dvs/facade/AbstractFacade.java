/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ug.or.use.dvs.facade;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

/**
 *
 * @author Professor
 */
public abstract class AbstractFacade<T> {

    private Class<T> entityClass;

    public AbstractFacade(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    protected abstract EntityManager getEntityManager();

    public void create(T entity) {
        getEntityManager().persist(entity);
    }

    public void edit(T entity) {
        getEntityManager().merge(entity);
    }

    public void remove(T entity) {
        getEntityManager().remove(getEntityManager().merge(entity));
    }

    public T find(Object id) {
        return getEntityManager().find(entityClass, id);
    }

    public List<T> findAll() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        return getEntityManager().createQuery(cq).getResultList();
    }

    public List<T> findRange(int[] range) {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        q.setMaxResults(range[1] - range[0]);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }

    public int count() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        javax.persistence.criteria.Root<T> rt = cq.from(entityClass);
        cq.select(getEntityManager().getCriteriaBuilder().count(rt));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }

    /**
     * This method returns an Entity with one row of data specific to the given
     * column value which must be unique - return column where column value
     *
     * @param entity
     * @param columnName
     * @param uniqueColumnValue
     * @return
     */

    public T getEntityRowGivenColumnValue(String columnName, Object uniqueColumnValue) {
        T result;
        javax.persistence.criteria.CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        javax.persistence.criteria.CriteriaQuery<T> cq = cb.createQuery(entityClass);
        javax.persistence.criteria.Root<T> rt = cq.from(entityClass);
        cq.select(rt).where(cb.equal(rt.get(columnName), uniqueColumnValue));
        javax.persistence.TypedQuery<T> q = getEntityManager().createQuery(cq);
        try {
            result = q.getSingleResult();
        } catch (NoResultException ex) {
            result = null;
        }
        return result;
    }

    /**
     * This method returns Entity rows of data specific to the given column
     * value - return columns where column value
     *
     * @param entity
     * @param columnName
     * @param columnValue
     * @param orderByColumn
     * @return
     */
    public List<T> getEntityRowsGivenColumnValue(String columnName, Object columnValue) {
        List<T> resultList;
        javax.persistence.criteria.CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        javax.persistence.criteria.CriteriaQuery<T> cq = cb.createQuery(entityClass);
        javax.persistence.criteria.Root<T> rt = cq.from(entityClass);
        cq.select(rt).where(cb.equal(rt.get(columnName), columnValue));
        javax.persistence.TypedQuery<T> q = getEntityManager().createQuery(cq);
        try {
            resultList = q.getResultList();
        } catch (NoResultException ex) {
            resultList = null;
        }
        return resultList;
    }

    /**
     * This method selects rows depending on the specified column and value.
     *
     * @param columnName-This is the name of the column against which we want to
     * filter data
     * @param columnValue-The value of the column
     * @param orderBy_ascOrdesc-ascending or descending order. You can specify
     * asc or desc in double quotes
     * @param orderByColumn-the name of the column by which we are ordering the
     * data
     * @return
     */
    public List<T> getEntityRowsGivenColumnValue(String columnName, Object columnValue, String orderBy_ascOrdesc, String orderByColumn) {
        List<T> resultList;
        javax.persistence.criteria.CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        javax.persistence.criteria.CriteriaQuery<T> cq = cb.createQuery(entityClass);
        javax.persistence.criteria.Root<T> rt = cq.from(entityClass);
        cq.select(rt).where(cb.equal(rt.get(columnName), columnValue));
        if (orderBy_ascOrdesc.contains("asc")) {
            cq.orderBy(cb.asc(rt.get(orderByColumn)));
        } else if (orderBy_ascOrdesc.contains("desc")) {
            cq.orderBy(cb.desc(rt.get(orderByColumn)));
        }
        javax.persistence.TypedQuery<T> q = getEntityManager().createQuery(cq);
        try {
            resultList = q.getResultList();
        } catch (NoResultException ex) {
            resultList = null;
        }
        return resultList;
    }

    /**
     * This method returns Rows of an entity ordered in ascending order. This
     * data can later help you to know the get the last record of a result set.
     * :)
     *
     * @param entity
     * @param idColumn
     * @return
     */
    public T getLastEntityRow(String idColumn) {
        T result;
        javax.persistence.criteria.CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        javax.persistence.criteria.CriteriaQuery<T> cq = cb.createQuery(entityClass);
        javax.persistence.criteria.Root<T> rt = cq.from(entityClass);
        cq.select(rt).orderBy(cb.desc(rt.get(idColumn)));
        javax.persistence.TypedQuery<T> q = getEntityManager().createQuery(cq);
        try {
            result = q.setFirstResult(0).setMaxResults(1).getSingleResult();
        } catch (NoResultException ex) {
            result = null;
        }
        return result;
    }
}
