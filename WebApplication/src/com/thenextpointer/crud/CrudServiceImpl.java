package com.thenextpointer.crud;

import com.thenextpointer.db.Datastore;
import com.thenextpointer.exceptions.NonexistentEntityException;
import com.thenextpointer.exceptions.PreexistingEntityException;
import java.io.Serializable;
import javax.jdo.JDOObjectNotFoundException;
import javax.jdo.PersistenceManager;

/**
 *
 * @author devashish
 */
public class CrudServiceImpl<T, PK extends Serializable> implements CrudService<T, PK> {

    private Class<T> type;

    public CrudServiceImpl() {
        if (this.getClass().getGenericSuperclass() instanceof java.lang.reflect.ParameterizedType) {
            type = (Class) ((java.lang.reflect.ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        }
    }

    public PersistenceManager getPersistenceManager() {
        //return PMF.get().getPersistenceManager();
        return new Datastore().getPersistanceManager();
    }

    public Class<T> getType() {
        return type;
    }

    public void setType(Class<T> type) {
        this.type = type;
    }

    @Override
    public T create(T t) throws PreexistingEntityException {
        PersistenceManager pm = getPersistenceManager();
        try {
            //pm.setDetachAllOnCommit(true);
            pm.makePersistent(t);
        } finally {
            // pm.close();
        }
        return t;
    }

    @Override
    public T read(PK id) {
        PersistenceManager pm = getPersistenceManager();
        Object object = null;
        try {
            object = pm.getObjectById(type, id);
        } catch (javax.jdo.JDOObjectNotFoundException ex) {
        }
        // pm.close();
        return (T) object;
    }

    @Override
    public T update(T t) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void delete(T t) {
        PersistenceManager pm = getPersistenceManager();
        try {
            pm.deletePersistent(t);
        } finally {
            //pm.close();
        }
    }

    @Override
    public void delete(PK id) throws NonexistentEntityException {
        PersistenceManager pm = getPersistenceManager();
        try {
            Object object = pm.getObjectById(type, id);
            pm.deletePersistent(object);
        } catch(JDOObjectNotFoundException ex) {
            throw new NonexistentEntityException("Object not exists");
        } finally {
            //pm.close();
        }
    }
}