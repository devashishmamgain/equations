package in.co.equations.modules.ws;

import com.google.appengine.api.datastore.Email;
import com.thenextpointer.exceptions.NonexistentEntityException;
import com.thenextpointer.spring.AppContext;
import in.co.equations.modules.user.EqUser;
import in.co.equations.modules.user.EqUserService;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.xml.bind.annotation.XmlRootElement;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 *
 * @author devashish
 */
@Path("/ws/user/")
@Component
public class QuestionWS {

    private static final Logger LOG = Logger.getLogger(QuestionWS.class.getName());
    private EqUserService eqUserService;

    public QuestionWS() {
        ApplicationContext ctx = AppContext.getApplicationContext();
        eqUserService = (EqUserService) ctx.getBean("eqUserService");
        
    }

    @GET
    @Produces({"application/xml", "application/json"})
    @Path("/details")
    public EqUser getDetails(@QueryParam("emailId") String emailId)
            throws NonexistentEntityException, Exception {
        LOG.log(Level.INFO, "Received emailId:{0}", new Object[]{emailId});
        EqUser eqUser = eqUserService.getEqUser(new Email(emailId));
        return eqUser;
    }
}
