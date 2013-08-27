package in.co.equations.modules.note;

import com.thenextpointer.exceptions.PreexistingEntityException;
import in.co.equations.modules.editor.GoogleDatastoreKeyEditor;
import in.co.equations.modules.editor.GoogleDatastoreTextEditor;
import in.co.equations.modules.session.UserSession;
import in.co.equations.modules.user.EqUser;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;

/**
 *
 * @author devashish
 */
@Controller
public class NoteController {

    @Autowired
    private NoteService noteService;
    
    @Autowired
    private NoteCategoryService noteCategoryService;
    
    private UserSession userSession;

    public NoteController() {
    }

    public UserSession getUserSession() {
        return userSession;
    }

    public void setUserSession(UserSession userSession) {
        this.userSession = userSession;
    }

    public NoteService getNoteService() {
        return noteService;
    }

    public void setNoteService(NoteService noteService) {
        this.noteService = noteService;
    }

    public NoteCategoryService getNotecategoryService() {
        return noteCategoryService;
    }

    public void setNoteCategoryService(NoteCategoryService noteCateoryService) {
        this.noteCategoryService = noteCateoryService;
    }

    @ModelAttribute("noteCategories")
    public List<NoteCategory> populateNoteCategories() {
        return noteCategoryService.getNoteCategories();
    }

    @RequestMapping(value = "/admin/note/create.form", method = RequestMethod.POST)
    public String create(
            @ModelAttribute("note") Note note,
            BindingResult result, HttpSession session, SessionStatus status, HttpServletRequest request) throws PreexistingEntityException {
        //status.setComplete();
        userSession = (UserSession) session.getAttribute("userSession");
        EqUser eqUser = userSession.getEqUser();
        note.setEqUser(eqUser);
        noteService.getCrudService().create(note);
        return "admin/note/success";
    }

    @RequestMapping(value = "/admin/note/create.form", method = RequestMethod.GET)
    public String initForm(ModelMap model) {
        Note note = new Note();
        model.addAttribute("note", note);
        return "admin/note/edit";
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(com.google.appengine.api.datastore.Key.class,
                new GoogleDatastoreKeyEditor());
        binder.registerCustomEditor(com.google.appengine.api.datastore.Text.class,
                new GoogleDatastoreTextEditor());
    }
}
