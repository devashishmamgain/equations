package in.co.equations.modules.notification;

import com.google.appengine.api.datastore.KeyFactory;
import com.thenextpointer.crud.CrudService;
import in.co.equations.modules.notificationtype.NotificationType;
import com.thenextpointer.db.PMF;
import com.thenextpointer.db.PersistenceService;
import com.thenextpointer.pagination.ListService;
import java.util.List;
import javax.jdo.PersistenceManager;

/**
 *
 * @author devashish
 */
public class NotificationService {

    private PersistenceService persistenceService;

    public PersistenceService getPersistenceService() {
        return persistenceService;
    }

    public void setPersistenceService(PersistenceService persistenceService) {
        this.persistenceService = persistenceService;
    }

    public NotificationService() {
    }

    public List<Notification> getRecentNotifications() {
        PersistenceManager pm = PMF.get().getPersistenceManager();
        String query = "select from " + Notification.class.getName() + " order by createdAt desc";
        List<Notification> notifications = (List<Notification>) pm.newQuery(query).execute();
        return notifications;
    }

    public List<Notification> getRecentNotifications(int notificationTypeId) {
        PersistenceManager pm = PMF.get().getPersistenceManager();
        NotificationType notificationType = pm.getObjectById(NotificationType.class, KeyFactory.createKey(NotificationType.class.getSimpleName(), notificationTypeId));
        javax.jdo.Query query = pm.newQuery(Notification.class, "notificationTypeKey == param");
        query.declareParameters("String param");
        List<Notification> notifications = (List<Notification>) query.execute(notificationType.getKey());
        return notifications;
    }

    public CrudService getCrudService() {
        return persistenceService.getCrudService();
    }

    public ListService getListService() {
        return persistenceService.getListService();
    }
}