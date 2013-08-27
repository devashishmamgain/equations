package in.co.equations.modules.note;

import com.thenextpointer.db.PersistenceService;
import com.thenextpointer.pagination.Rule;
import java.io.Serializable;
import java.util.List;

public class NoteCategoryService implements Serializable {

    private PersistenceService persistenceService;

    public PersistenceService getPersistenceService() {
        return persistenceService;
    }

    public void setPersistenceService(PersistenceService persistenceService) {
        this.persistenceService = persistenceService;
    }

    public NoteCategoryService() {
    }
    
    public NoteCategory getNoteCategoryByName(String name) {        
        Rule rule = new Rule("name", Rule.OPERATOR.eq.name(), name, "String");              
        return (NoteCategory) persistenceService.getSingleResult(rule);          
    }
    public List<NoteCategory> getNoteCategories() {
        return (List<NoteCategory>) persistenceService.getListService().getResults(null, null);
    }
}
