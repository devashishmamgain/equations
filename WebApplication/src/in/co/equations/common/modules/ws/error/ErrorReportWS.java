package in.co.equations.common.modules.ws.error;

import com.google.appengine.api.datastore.Text;
import com.thenextpointer.crud.CrudService;
import com.thenextpointer.exceptions.PreexistingEntityException;
import com.thenextpointer.spring.AppContext;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import in.co.equations.common.modules.error.ErrorReport;
import in.co.equations.common.modules.mail.MailService;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 *
 * @author devashish
 */
@Path("/ws/error/")
@Component
public class ErrorReportWS {

    private CrudService crudService;
    private MailService mailService;

    public ErrorReportWS() {
        ApplicationContext ctx = AppContext.getApplicationContext();
        mailService = (MailService) ctx.getBean("mailService");
        crudService = (CrudService) ctx.getBean("crudService");
    }

    @GET
    @Path("/submit")
    public String submitErrorReport(@Context HttpServletRequest requestContext, @QueryParam("info") String info,
            @QueryParam("stackTrace") String stackTrace) {
        try {
            String ip = requestContext.getRemoteAddr().toString();
            ErrorReport errorReport = new ErrorReport();
            errorReport.setIp(ip);
            errorReport.setInfo(info);
            errorReport.setStackTrace(new Text(stackTrace));
            crudService.create(errorReport);
            mailService.sendErrorMail(info, stackTrace);
            return "success";
        } catch (PreexistingEntityException ex) {
            Logger.getLogger(ErrorReportWS.class.getName()).log(Level.SEVERE, null, ex);
            return "error";
        }
    }
}
