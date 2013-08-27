package in.co.equations.modules.notificationtype;

import com.google.appengine.api.datastore.Key;
import com.thenextpointer.crud.CrudServiceImpl;
import com.thenextpointer.db.PMF;
import javax.jdo.PersistenceManager;

/**
 *
 * @author devashish
 */
public class NotificationTypeCrudService extends CrudServiceImpl<NotificationType, Key> {

    @Override
    public NotificationType update(NotificationType notificationType) {
        PersistenceManager pm = PMF.get().getPersistenceManager();
        try {
            NotificationType existingNotificationType = pm.getObjectById(NotificationType.class, notificationType.getKey().getId());
            existingNotificationType.setDescription(notificationType.getDescription());
            existingNotificationType.setKey(notificationType.getKey());
            existingNotificationType.setName(notificationType.getName());
            return existingNotificationType;
        } finally {
            pm.close();
        }
    }

    //Todo: need to look at it whether its working or not.
    @Override
     public void delete(NotificationType notificationType) {
        PersistenceManager pm = getPersistenceManager();
        try {
            //Retrieves the persistent object to delete
            Object dataobject = pm.getObjectById( NotificationType.class, notificationType.getKey());
            pm.deletePersistent(dataobject);
        } finally {
            pm.close();
        }
    }
}
