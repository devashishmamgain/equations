package in.co.equations.modules.note;

import com.thenextpointer.crud.CrudService;
import com.thenextpointer.db.PersistenceService;
import com.thenextpointer.pagination.Filter;
import com.thenextpointer.pagination.Rule;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class NoteService implements Serializable {

    private PersistenceService persistenceService;
    private NoteCategoryService noteCategoryService;

    public PersistenceService getPersistenceService() {
        return persistenceService;
    }

    public void setPersistenceService(PersistenceService persistenceService) {
        this.persistenceService = persistenceService;
    }

    public NoteCategoryService getNoteCategoryService() {
        return noteCategoryService;
    }

    public void setNoteCategoryService(NoteCategoryService noteCategoryService) {
        this.noteCategoryService = noteCategoryService;
    }
    
    public NoteService() {
    }
    
    public List<Note> getNotes(NoteCategory noteCategory) {
        List<Note> notes = new ArrayList<Note>();        
        Rule rule = new Rule("noteCategoryKey", Rule.OPERATOR.eq.name(), noteCategory.getKey(), "com.google.appengine.api.datastore.Key");              
        Filter filter = new Filter(rule);
        notes = (List<Note>) persistenceService.getListService().getResults(null, filter);        
        return notes;
    }

        public CrudService getCrudService() {
        return persistenceService.getCrudService();
    }

    
    public List<Note> getNotesByNoteCategoryName(String name) {                
        NoteCategory noteCategory = noteCategoryService.getNoteCategoryByName(name);
        return getNotes(noteCategory);
    }
}
