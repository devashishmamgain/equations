package in.co.equations.modules.user;

import com.google.appengine.api.datastore.Email;
import com.thenextpointer.crud.CrudService;
import com.thenextpointer.exceptions.NonexistentEntityException;
import com.thenextpointer.exceptions.PreexistingEntityException;
import com.thenextpointer.db.PMF;
import com.thenextpointer.db.PersistenceService;
import com.thenextpointer.pagination.ListService;
import java.util.List;
import javax.jdo.PersistenceManager;

public class EqUserService {
    
    private PersistenceService persistenceService;

    public EqUserService() {        
    
    }
    
    public PersistenceService getPersistenceService() {
        return persistenceService;
    }

    public void setPersistenceService(PersistenceService persistenceService) {
        this.persistenceService = persistenceService;
    }
    
    public CrudService getCrudService() {
        return persistenceService.getCrudService();
    }
    
    public ListService getListService() {
        return persistenceService.getListService();
    }

    public void saveOrUpdate(EqUser eqUser) throws NonexistentEntityException, PreexistingEntityException, Exception {
         if (eqUser.getId() != null) {            
            persistenceService.getCrudService().update(eqUser);
        } else {
            persistenceService.getCrudService().create(eqUser);
        }
    }

    public EqUser getEqUser(Email email) {
        PersistenceManager pm = PMF.get().getPersistenceManager();
        javax.jdo.Query query = pm.newQuery(EqUser.class, "email == param");
        query.declareParameters("String param");
        List<EqUser> eqUserList = (List<EqUser>) query.execute(email);
        return (eqUserList != null && !eqUserList.isEmpty()) ? eqUserList.get(0) : null;
    }

    public List<EqUser> getEqUserList() {
        PersistenceManager pm = PMF.get().getPersistenceManager();
        String query = "select from " + EqUser.class.getName();
        List<EqUser> eqUserList = (List<EqUser>) pm.newQuery(query).execute();
        return eqUserList;
    }

    public EqUser getEqUserByPhoneNumber(String phoneNumber) {
        EqUser eqUser = null;
        PersistenceManager pm = PMF.get().getPersistenceManager();
        javax.jdo.Query query = pm.newQuery(EqUser.class, "primaryContactNumber == param");
        query.declareParameters("String param");

        List<EqUser> eqUsers = (List<EqUser>) query.execute(phoneNumber);
        
        if (eqUsers.size() > 0) {
            eqUser = eqUsers.get(0);
        }
        return eqUser;
    }
}
