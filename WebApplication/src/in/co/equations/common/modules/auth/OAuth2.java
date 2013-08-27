package in.co.equations.common.modules.auth;

import java.io.IOException;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.scribe.model.OAuthConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class OAuth2 {

    private static final Logger LOG = Logger.getLogger(OAuth2.class.getName());
    @Autowired
    private AuthService authService;

    public void setAuthService(AuthService authService) {
        this.authService = authService;
    }

    public OAuth2() {
    }

    /*
     * @RequestMapping(value = "/contacts.page") public String oauthContacts() {
     * return
     * "redirect:https://accounts.google.com/o/oauth2/auth?scope=https%3A%2F%2Fwww.google.com%2Fm8%2Ffeeds/&redirect_uri=http%3A%2F%2Fonlinesmsutility.appspot.com%2Foauth2callback.page&response_type=code&client_id=430004786428-o5kdbnc5at5p8o7bqb1p3cban131t0ri.apps.googleusercontent.com";
     * }
     */
    @RequestMapping(value = "/oauth2callback.page", method = RequestMethod.GET)
    public String oauthResponse(@RequestParam(required = false) String code,
            @RequestParam(required = false) String error,
            HttpServletRequest request, HttpSession session) throws IOException, ServletException {
        //Todo: write code here for auth callback.
        return "redirect:/user/sms/inbox.action";
    }

    @RequestMapping(value = "/auth.page", method = RequestMethod.GET)
    public String requestAuthorization(HttpSession session) throws IOException, ServletException {        
        String authUrl = authService.getOAuthService().getAuthorizationUrl(OAuthConstants.EMPTY_TOKEN) + "&access_type=offline&approval_prompt=force";
        return "redirect:" + authUrl;
    }
}
