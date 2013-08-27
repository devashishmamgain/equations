package in.co.equations.modules.note;

import com.google.appengine.api.datastore.Key;
import com.thenextpointer.crud.CrudServiceImpl;
import com.thenextpointer.db.PMF;
import java.util.Date;
import javax.jdo.PersistenceManager;

/**
 *
 * @author devashish
 */
public class NoteCrudService extends CrudServiceImpl<Note, Key>{

    @Override
    public Note update(Note updatedNote) {
        Note existingNotes = null;
        PersistenceManager pm = PMF.get().getPersistenceManager();
        try {
            existingNotes = pm.getObjectById(Note.class, updatedNote.getKey());
            existingNotes.setSubject(updatedNote.getSubject() );
            existingNotes.setUpdatedAt(new Date());
            existingNotes.setEqUser(existingNotes.getEqUser());
            existingNotes.setNoteCategoryKey(updatedNote.getNoteCategoryKey());
            existingNotes.setNoteContents(updatedNote.getNoteContents());            
            existingNotes.setCreatedAt(updatedNote.getCreatedAt());                        
        } finally {
            pm.close();
        }
        return existingNotes;
    }

    @Override
    public void delete(Note updatedNote) {
        PersistenceManager pm = PMF.get().getPersistenceManager();
        try {
             Object dataobject = pm.getObjectById(Note.class, updatedNote.getKey());
             pm.deletePersistent(dataobject);
        } finally {
            pm.close();
        }

    }
}
