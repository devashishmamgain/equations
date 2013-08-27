package com.thenextpointer.db;

import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Transaction;

/**
 *
 * @author devashish
 */
public class Datastore {

    private static PersistenceManagerFactory pmFactory = null;
    private static final ThreadLocal<PersistenceManager> PER_THREAD_PM = new ThreadLocal<PersistenceManager>();

    public void initialize() {
        if (pmFactory != null) {
            throw new IllegalStateException("initialize() already called");
        }
        pmFactory = PMF.get();
    }

    public PersistenceManager getPersistanceManager() {
        PersistenceManager pm = PER_THREAD_PM.get();
        if (pm == null) {
            pm = pmFactory.getPersistenceManager();
            //pm.currentTransaction().begin();
            PER_THREAD_PM.set(pm);
        }
        return pm;
    }

    public void finishRequest() {
        PersistenceManager pm = PER_THREAD_PM.get();
        if (pm != null) {
            PER_THREAD_PM.remove();

            Transaction tx = pm.currentTransaction();
            if (tx.isActive()) {
                tx.rollback();
            } else {
                //tx.commit();
            }

            if (!pm.isClosed()) {
                pm.close();
            }
        }
    }
}
