package in.co.equations.authentication;

import com.google.appengine.api.datastore.Email;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.thenextpointer.exceptions.PreexistingEntityException;
import in.co.equations.modules.session.UserSession;
import in.co.equations.modules.user.EqUser;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import in.co.equations.modules.user.EqUserService;
import javax.servlet.http.HttpSession;

/**
 *
 * @author praveen
 */
@Controller
public class OAuthLogin {

    @Autowired
    private EqUserService eqUserService;    
    
    @RequestMapping(value = "/loginSubmit.page", method = RequestMethod.GET)
    public String loginSubmit(HttpServletRequest request, HttpServletResponse response, ModelMap model, HttpSession session) throws IOException, PreexistingEntityException {
        UserService userService = UserServiceFactory.getUserService();
        User user = userService.getCurrentUser();
        if (user != null) {
            Email email = new Email(user.getEmail());            
            EqUser eqUser = eqUserService.getEqUser(email);
            if (eqUser != null) {
                saveSession(session, eqUser);
                return ("redirect:/admin/user/create.form");
            }
        }         
        return "redirect:/login.page";
    }

    @RequestMapping(value = "/login.page", method = RequestMethod.GET)
    public String loginPage(HttpServletRequest request, HttpServletResponse response, ModelMap model, HttpSession session) throws IOException {
        UserService userService = UserServiceFactory.getUserService();
        return "redirect:" + userService.createLoginURL("/loginSubmit.page");
    }

    public void saveSession(HttpSession session, EqUser eqUser) {
        System.out.println("#saving session.");
        UserSession userSession = new UserSession();
        userSession.setEqUser(eqUser);
        session.setAttribute("userSession", userSession);
    }
}
