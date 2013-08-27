package in.co.equations.modules.notificationtype;

import com.thenextpointer.db.PersistenceService;
import java.util.List;

/**
 *
 * @author devashish
 */
public class NotificationTypeService {

    private PersistenceService persistenceService;

    public PersistenceService getPersistenceService() {
        return persistenceService;
    }

    public void setPersistenceService(PersistenceService persistenceService) {
        this.persistenceService = persistenceService;
    }

    public NotificationTypeService() {
    }

    public List<NotificationType> getNotificationTypes() {
        return (List<NotificationType>) persistenceService.getListService().getResults(null, null);
    }   
}
