package in.co.equations.modules.note;

import com.google.appengine.api.datastore.Key;
import com.thenextpointer.crud.CrudServiceImpl;
import com.thenextpointer.db.PMF;
import javax.jdo.PersistenceManager;

/**
 *
 * @author devashish
 */
public class NoteCategoryCrudService extends CrudServiceImpl<NoteCategory, Key> {

    @Override
    public NoteCategory update(NoteCategory noteCategory) {
        NoteCategory existingNotesCategory = null;
        PersistenceManager pm = PMF.get().getPersistenceManager();
        try {
            existingNotesCategory = pm.getObjectById(NoteCategory.class, noteCategory.getKey());
            existingNotesCategory.setName(noteCategory.getName());
            existingNotesCategory.setDescription(noteCategory.getDescription());
            existingNotesCategory.setLabel(noteCategory.getLabel());
        } finally {
            pm.close();
        }
        return existingNotesCategory;
    }

    @Override
    public void delete(NoteCategory noteCategory) {
        PersistenceManager pm = getPersistenceManager();
        try {
            //Retrieves the persistent object to delete
            Object dataobject = pm.getObjectById(NoteCategory.class, noteCategory.getKey());
            pm.deletePersistent(dataobject);
        } finally {
            pm.close();
        }
    }
}