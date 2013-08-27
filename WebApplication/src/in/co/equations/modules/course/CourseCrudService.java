package in.co.equations.modules.course;

import com.google.appengine.api.datastore.Key;
import com.thenextpointer.crud.CrudServiceImpl;
import com.thenextpointer.db.PMF;
import javax.jdo.PersistenceManager;

/**
 *
 * @author devashish
 */
public class CourseCrudService extends CrudServiceImpl<Course, Key> {

    @Override
    public Course update(Course course) {
        Course existingCourse = null;
        PersistenceManager pm = PMF.get().getPersistenceManager();
        try {
            existingCourse = pm.getObjectById(Course.class, course.getKey());
            existingCourse.setDescription(course.getDescription());
            existingCourse.setName(course.getName());
        } finally {
            pm.close();
        }
        return existingCourse;
    }
    
    @Override
    public void delete(Course course) {
        PersistenceManager pm = getPersistenceManager();
        try {
            //Retrieves the persistent object to delete
            Object dataobject = pm.getObjectById(Course.class, course.getKey());
            pm.deletePersistent(dataobject);
        } finally {
            pm.close();
        }
    }
}
