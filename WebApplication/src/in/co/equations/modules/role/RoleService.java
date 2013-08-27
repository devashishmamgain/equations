package in.co.equations.modules.role;

import com.thenextpointer.db.PersistenceService;
import java.io.Serializable;

public class RoleService implements Serializable {

    private PersistenceService persistenceService;

    public PersistenceService getPersistenceService() {
        return persistenceService;
    }

    public void setPersistenceService(PersistenceService persistenceService) {
        this.persistenceService = persistenceService;
    }

    public RoleService() {        
    }
}
