package in.co.equations.modules.notification;

import in.co.equations.modules.notificationtype.NotificationTypeService;
import in.co.equations.modules.notificationtype.NotificationType;
import com.opensymphony.xwork2.ActionSupport;
import com.thenextpointer.pagination.Filter;
import com.thenextpointer.pagination.ListServiceImpl;
import com.thenextpointer.pagination.Pagination;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author Adarsh
 */
public class NotificationListService extends ListServiceImpl<Notification> {

    /* forward name="success" path="" */
    private NotificationService notificationservice = new NotificationService();
    private NotificationTypeService notificationTypeService = new NotificationTypeService();
    private List<NotificationType> notificationsTypes = null;
    private List<Notification> notifications = null;
    private int notificationTypeId;

    @Override
    public Collection<Notification> getResults(Pagination pagination, Filter filter) {
       return (Collection<Notification>) executeQuery(pagination, filter, null);
    }

    public String doListNotiType() throws Exception {
        notificationsTypes = (List<NotificationType>) notificationTypeService.getNotificationTypes();
        return ActionSupport.SUCCESS;
    }

    public String doNotificationList() throws Exception {
        this.notifications = notificationservice.getRecentNotifications(notificationTypeId);
        return ActionSupport.SUCCESS;
    }

    public List<NotificationType> getNotificationTypes() {
        return this.notificationsTypes;
    }

    public List<Notification> getNotifications() {
        return this.notifications;
    }

    public int getNotificationTypeId() {
        return notificationTypeId;
    }

    public void setNotificationTypeId(int notificationTypeId) {
        this.notificationTypeId = notificationTypeId;
    }
}
