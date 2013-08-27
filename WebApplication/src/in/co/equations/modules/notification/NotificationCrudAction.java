package in.co.equations.modules.notification;

import com.google.appengine.api.datastore.Key;
import com.thenextpointer.crud.CrudAction;

/**
 *
 * @author devashish
 */
public class NotificationCrudAction extends CrudAction<Notification, Key> {

    private int typeId;

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {        
        this.typeId = typeId;
    }


    @Override
    public String performCrudAction() {
        t.setNotificationTypeId(typeId);
        return super.performCrudAction();
    }
}
