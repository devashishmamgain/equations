package in.co.equations.common.modules.feedback;

import com.google.appengine.api.datastore.Key;
import com.thenextpointer.crud.CrudServiceImpl;
import com.thenextpointer.db.PMF;
import java.util.List;
import javax.jdo.PersistenceManager;

/**
 *
 * @author Adarsh
 */
public class FeedbackCrudService extends CrudServiceImpl<Feedback, Key> {
    public static List<Feedback> getEnquiryList() {
        PersistenceManager pm = PMF.get().getPersistenceManager();
        String query = "select from " + Feedback.class.getName();
        List<Feedback> enquiryList = (List<Feedback>) pm.newQuery(query).execute();
        return enquiryList;
    }
}
