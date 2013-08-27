package in.co.equations.modules.notification;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.thenextpointer.pagination.Filter;
import com.thenextpointer.pagination.ListAction;
import com.thenextpointer.pagination.Rule;
import in.co.equations.modules.notificationtype.NotificationType;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author devashish
 */
public class NotificationListAction extends ListAction<Notification> {
    private int type;

    public int getType() {
        return type;
    }

    public void setType(int type) {        
        this.type = type;
    }

    private Filter modify(Filter filter) {
        if (filter == null) {
            filter = new Filter();
        }

        Rule rule = new Rule();
        rule.setField("notificationTypeKey");
        rule.setOp("eq");
        rule.setDataType("com.google.appengine.api.datastore.Key");
        Key key = KeyFactory.createKey(NotificationType.class.getSimpleName(), type);
        rule.setData(key);
        Set<Rule> rules = filter.getRules();
        if (rules == null) {
            rules = new HashSet<Rule>();
        }

        rules.add(rule);
        filter.setRules(rules);
        return filter;
    }

    @Override
    public String doList() {
        filter = modify(filter);
        pagination.setCount(listService.getCount(filter));        
        results = (List<Notification>) listService.getResults(pagination, filter);        
        return com.opensymphony.xwork2.Action.SUCCESS;
    }

    @Override
    public List<Notification> getResults() {
        return results;
    }
}
