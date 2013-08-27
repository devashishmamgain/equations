package in.co.equations.modules.notification;

import com.google.appengine.api.datastore.KeyFactory;
import com.thenextpointer.exceptions.NonexistentEntityException;
import com.thenextpointer.exceptions.PreexistingEntityException;
import in.co.equations.modules.notificationtype.NotificationTypeService;
import in.co.equations.modules.notificationtype.NotificationType;
import in.co.equations.modules.editor.GoogleDatastoreKeyEditor;
import in.co.equations.modules.editor.GoogleDatastoreTextEditor;
import in.co.equations.modules.session.UserSession;
import in.co.equations.modules.user.EqUser;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
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

/**
 *
 * @author devashish
 */

@Controller
public class NotificationController {

    private NotificationService notificationService;
    private NotificationTypeService notificationTypeService;
    
    private UserSession userSession;

    public NotificationController() {              
    }

    public UserSession getUserSession() {
        return userSession;
    }

    public void setUserSession(UserSession userSession) {
        this.userSession = userSession;
    }

    public NotificationService getNotificationService() {
        return notificationService;
    }

    public void setNotificationService(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    public NotificationTypeService getNotificationTypeService() {
        return notificationTypeService;
    }

    public void setNotificationTypeService(NotificationTypeService notificationTypeService) {
        this.notificationTypeService = notificationTypeService;
    }

    @ModelAttribute("notificationTypes")
    public List<NotificationType> populateNotificationTypes() {
        return (List<NotificationType>) notificationTypeService.getPersistenceService().getListService().getResults(null, null);
    }

    @RequestMapping(value = "/admin/notification/save.form", method = RequestMethod.POST)
    public String save(
            @ModelAttribute("notification") Notification notification,
            BindingResult result, HttpSession session, SessionStatus status, HttpServletRequest request) throws PreexistingEntityException {
        //status.setComplete();
        userSession = (UserSession) session.getAttribute("userSession");
        EqUser eqUser = userSession.getEqUser();
        notification.setCreatedByEqUserKey(eqUser.getId());
        notificationService.getCrudService().create(notification);
        return "admin/notification/success";
    }

    @RequestMapping(value = "/admin/notification/update.form", method = RequestMethod.POST)
    public String update(
            @ModelAttribute("notification") Notification notification,
            BindingResult result, SessionStatus status) throws NonexistentEntityException, Exception {
        status.setComplete();
        notificationService.getCrudService().update(notification);
        return "admin/notification/success";
    }

    //Todo: VERIFY IT
    @RequestMapping(value = "/admin/notification/delete.form", method = RequestMethod.GET)
    public String delete(ModelMap model, @RequestParam("keyString") String keyString) throws NonexistentEntityException {        
        Notification notification = (Notification) notificationService.getPersistenceService().getCrudService().read(keyString);
        notificationService.getCrudService().delete(notification);
        return "redirect:/notification/list.page";
    }

    @RequestMapping(value = "/admin/notification/create.form", method = RequestMethod.GET)
    public String initForm(ModelMap model) {
        Notification notification = new Notification();        
        model.addAttribute("notification", notification);        
        return "admin/notification/edit";
    }

    //Todo: VERIFY IT
    @RequestMapping(value = "/admin/notification/edit.form", method = RequestMethod.GET)
    public String edit(ModelMap model, @RequestParam("keyString") String keyString) {
        Notification notification = (Notification) notificationService.getCrudService().read(KeyFactory.stringToKey(keyString));
        model.addAttribute("notification", notification);
        return "admin/notification/edit";
    }

    @RequestMapping(value = "/notification/list.page", method = RequestMethod.GET)
    public String notifications(HttpServletRequest request, ModelMap model) {
        String url = request.getRequestURI();
        model.addAttribute("notifications", notificationService.getRecentNotifications());
        return "notification/list";
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {        
        binder.registerCustomEditor(com.google.appengine.api.datastore.Key.class,
                new GoogleDatastoreKeyEditor());
         binder.registerCustomEditor(com.google.appengine.api.datastore.Text.class,
                new GoogleDatastoreTextEditor());
    }
    
}
