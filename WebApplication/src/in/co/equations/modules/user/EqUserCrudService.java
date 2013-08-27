package in.co.equations.modules.user;

import com.google.appengine.api.datastore.Key;
import com.thenextpointer.crud.CrudServiceImpl;
import com.thenextpointer.db.PMF;
import javax.jdo.PersistenceManager;

/**
 *
 * @author devashish
 */
public class EqUserCrudService extends CrudServiceImpl<EqUser, Key> {

    @Override
    public EqUser update(EqUser eqUser) {
        EqUser eqUserExisting = null;
        PersistenceManager pm = PMF.get().getPersistenceManager();
        try {
            eqUserExisting = pm.getObjectById(EqUser.class, eqUser.getId());
            eqUserExisting.setIdNo(eqUser.getIdNo());
            eqUserExisting.setEmail(eqUser.getEmail());
            eqUserExisting.setName(eqUser.getName());
            eqUserExisting.setAlternateContactNumber(eqUser.getAlternateContactNumber());
            eqUserExisting.setCorrespondenceAddress(eqUser.getCorrespondenceAddress());
            eqUserExisting.setPermanentAddress(eqUser.getPermanentAddress());
            eqUserExisting.setPrimaryContactNumber(eqUser.getPrimaryContactNumber());
            eqUserExisting.setRoleId(eqUser.getRoleId());
            eqUserExisting.setCoursesKey(eqUser.getCoursesKey());
            eqUserExisting.setSmsNotification(eqUser.isSmsNotification());
        } finally {
            pm.close();
        }
        return eqUserExisting;
    }

    @Override
    public void delete(EqUser eqUser) {
        PersistenceManager pm = PMF.get().getPersistenceManager();
        try {
            Object dataobject = pm.getObjectById(EqUser.class, eqUser.getId());
            pm.deletePersistent(dataobject);
        } finally {
            pm.close();
        }

    }
}
