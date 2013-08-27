package in.co.equations.common.modules.role;

import com.google.appengine.api.datastore.Key;
import com.thenextpointer.crud.CrudServiceImpl;
import javax.jdo.PersistenceManager;

/**
 *
 * @author devashish
 */
public class RoleCrudService extends CrudServiceImpl<Role, Key> {

    @Override
    public Role update(Role role) {
        Role existingRole = null;
        PersistenceManager pm = getPersistenceManager();
        try {
            existingRole = pm.getObjectById(Role.class, role.getKey());
            existingRole.setKey(role.getKey());
            existingRole.setName(role.getName());
            existingRole.setLabel(role.getLabel());
            existingRole.setDescription(role.getDescription());
        } finally {
            //pm.close();
        }
        return existingRole;
    }

    @Override
    public void delete(Role role) {
        PersistenceManager pm = getPersistenceManager();
        try {
            //Retrieves the persistent object to delete
            Object dataobject = pm.getObjectById(Role.class, role.getKey());
            pm.deletePersistent(dataobject);
        } finally {
            //pm.close();
        }
    }
}
