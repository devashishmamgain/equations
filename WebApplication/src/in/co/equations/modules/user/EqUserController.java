package in.co.equations.modules.user;

import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.thenextpointer.exceptions.NonexistentEntityException;
import com.thenextpointer.exceptions.PreexistingEntityException;
import in.co.equations.modules.course.Course;
import in.co.equations.modules.course.CourseService;
import in.co.equations.modules.editor.GoogleDatastoreKeyEditor;
import in.co.equations.modules.role.Role;
import in.co.equations.modules.role.RoleService;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;

@Controller
public class EqUserController {

    private EqUserValidator eqUserValidator;
    private EqUserService eqUserService;
    private CourseService courseService;
    private RoleService roleService;
    
    public EqUserController() {
        this.eqUserValidator = new EqUserValidator();
    }

    public EqUserService getEqUserService() {
        return eqUserService;
    }

    public void setEqUserService(EqUserService eqUserService) {
        this.eqUserService = eqUserService;
    }

    public CourseService getCourseService() {
        return courseService;
    }

    public void setCourseService(CourseService courseService) {
        this.courseService = courseService;
    }

    public RoleService getRoleService() {
        return roleService;
    }

    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

    @ModelAttribute("availableRoles")
    public List<Role> populateRoles() {
        return (List<Role>) roleService.getPersistenceService().getListService().getResults(null, null);
    }

    @ModelAttribute("courses")
    public List<Course> populateCourses() {
        return (List<Course>) courseService.getPersistenceService().getListService().getResults(null, null);
    }

    @RequestMapping(value = "/views/logout.page", method = RequestMethod.GET)
    public String logout(HttpSession session) {
        UserService userService = UserServiceFactory.getUserService();
        //Todo: Clean up session or any other attributes/objects.
        session.invalidate();
        return "redirect:" + userService.createLogoutURL("/views/home.jsp");
    }

    @RequestMapping(value = "/admin/user/save.form", method = RequestMethod.POST)
    public String save(
            @ModelAttribute("eqUser") EqUser eqUser,
            BindingResult result, SessionStatus status) throws PreexistingEntityException {

        eqUserValidator.validate(eqUser, result);

        //Role role = (Role) eqUserService.getPersistenceService().getObjectById(Role.class, eqUser.getRoleId());

        if (result.hasErrors()) {
            //if validator failed            
            return "admin/user/edit";
        } else {
            status.setComplete();
            //form success            
            eqUserService.getCrudService().create(eqUser);
            return "admin/user/success";
        }
    }

    @RequestMapping(value = "/admin/user/update.form", method = RequestMethod.POST)
    public String update(
            @ModelAttribute("eqUser") EqUser eqUser,
            BindingResult result, SessionStatus status) throws NonexistentEntityException, Exception {
        eqUserValidator.validate(eqUser, result);

        if (result.hasErrors()) {
            //if validator failed
            return "admin/user/edit";
        } else {
            status.setComplete();
            //form success 
            eqUserService.getCrudService().update(eqUser);
            return "admin/user/success";
        }
    }

    @RequestMapping(value = "/admin/user/create.form", method = RequestMethod.GET)
    public String initForm(ModelMap model) {

        EqUser eqUser = new EqUser();

        //command object
        model.addAttribute("eqUser", eqUser);

        //return form view
        return "admin/user/edit";
    }

    //Todo: VERIFY IT
    @RequestMapping(value = "/admin/user/edit.form", method = RequestMethod.GET)
    public String edit(ModelMap model, @RequestParam("keyString") String keyString) {        
        EqUser eqUser = (EqUser) eqUserService.getCrudService().read(KeyFactory.stringToKey(keyString));
        
        //command object        
        model.addAttribute("eqUser", eqUser);

        //return form view
        return "admin/user/edit";
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        /*  binder.registerCustomEditor(String.class,
        new StringTrimmerEditor(false)
        );

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class,
        new CustomDateEditor(dateFormat, false)
        );
*/
        binder.registerCustomEditor(com.google.appengine.api.datastore.Key.class,
        new GoogleDatastoreKeyEditor()
        );

       // binder.registerCustomEditor(List.class, new CustomCollectionEditor(List.class));        
    }
}
