package in.co.equations.modules.note;

import com.google.appengine.api.datastore.Key;
import com.thenextpointer.crud.CrudServiceImpl;
import com.thenextpointer.db.PMF;
import javax.jdo.PersistenceManager;

/**
 *
 * @author devashish
 */
public class NoteContentCrudService extends CrudServiceImpl<NoteContent, Key>{

    @Override
    public NoteContent update(NoteContent updatedNoteContent) {
        NoteContent existingNoteContent = null;
        PersistenceManager pm = PMF.get().getPersistenceManager();
        try {
            existingNoteContent = pm.getObjectById(NoteContent.class, updatedNoteContent.getKey());
            existingNoteContent.setContent(updatedNoteContent.getContent() );                        
            existingNoteContent.setNote(updatedNoteContent.getNote());
        } finally {
            pm.close();
        }
        return existingNoteContent;

    }
    @Override
    public void delete(NoteContent updatedNotesContent) {
        PersistenceManager pm = PMF.get().getPersistenceManager();
        try {
             Object dataobject = pm.getObjectById(Note.class, updatedNotesContent.getKey());
             pm.deletePersistent(dataobject);
        } finally {
            pm.close();
        }

    }
}
