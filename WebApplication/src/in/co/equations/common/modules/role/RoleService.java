package in.co.equations.common.modules.role;

import com.thenextpointer.crud.CrudService;
import com.thenextpointer.db.PersistenceService;
import com.thenextpointer.pagination.ListService;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RoleService implements Serializable {

    private PersistenceService persistenceService;
    private static Map<String, Role> roleMap;
    
    public RoleService() {      
        
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
    
    public synchronized void initRoleMap() {
        roleMap = new HashMap<String, Role>();
        List<Role> roles = (List<Role>) persistenceService.getListService().getResults(null, null);
        for (Role role: roles) {
            roleMap.put(role.getName(), role);
        }
    }    
    
    public Role getRoleByName(String name) {
        if (roleMap == null || roleMap.isEmpty()) {
            initRoleMap();
        }
        return roleMap.get(name);
    }
}