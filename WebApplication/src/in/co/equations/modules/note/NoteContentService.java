package in.co.equations.modules.note;

import com.thenextpointer.db.PersistenceService;
import java.io.Serializable;

public class NoteContentService implements Serializable {

    private PersistenceService persistenceService;

    public PersistenceService getPersistenceService() {
        return persistenceService;
    }

    public void setPersistenceService(PersistenceService persistenceService) {
        this.persistenceService = persistenceService;
    }

    public NoteContentService() {
    }
}
