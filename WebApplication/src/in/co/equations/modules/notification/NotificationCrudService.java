package in.co.equations.modules.notification;

import com.google.appengine.api.datastore.Key;
import com.thenextpointer.crud.CrudServiceImpl;
import com.thenextpointer.db.PMF;
import com.thenextpointer.exceptions.NonexistentEntityException;
import com.thenextpointer.exceptions.PreexistingEntityException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jdo.PersistenceManager;

/**
 *
 * @author devashish
 */
public class NotificationCrudService extends CrudServiceImpl<Notification, Key> {

    @Override
    public Notification create(Notification notification) throws PreexistingEntityException {
        notification.setUpdatedAt(new Date());
        return super.create(notification);
    }

    @Override
    public void delete(Notification notification) {
        try {
            super.delete(notification.getKey());
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(NotificationCrudService.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
        }
    }

    @Override
    public Notification update(Notification notification) {
        PersistenceManager pm = PMF.get().getPersistenceManager();
        try {
            Notification existingNotification = pm.getObjectById(Notification.class, notification.getKey().getId());
            existingNotification.setContent(notification.getContent());
            existingNotification.setCreatedByEqUserKey(notification.getCreatedByEqUserKey());
            existingNotification.setNotificationTypeKey(notification.getNotificationTypeKey());
            existingNotification.setSubject(notification.getSubject());
            existingNotification.setApplicationFee(notification.getApplicationFee());
            existingNotification.setBankName(notification.getBankName());
            existingNotification.setCreatedByEqUserKey(notification.getCreatedByEqUserKey());
            existingNotification.setExam(notification.getExam());
            existingNotification.setExamDate(notification.getExamDate());
            existingNotification.setLinks(notification.getLinks());
            existingNotification.setNotificationTypeKey(notification.getNotificationTypeKey());
            existingNotification.setSubmissionDate(notification.getSubmissionDate());
            existingNotification.setUpdatedAt(new Date());
            existingNotification.setChallanLink(notification.getChallanLink());
            existingNotification.setVacancies(notification.getVacancies());
        } finally {
            pm.close();
        }
        return notification;
    }

}
