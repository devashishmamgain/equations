package in.co.equations.common.modules.ws.internal;

import com.thenextpointer.spring.AppContext;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.cache.CacheException;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import in.co.equations.common.modules.internal.Tools;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 *
 * @author devashish
 */
@Path("/ws/internal/tools/")
@Component
public class ToolsWS {

    private static final Logger Log = Logger.getLogger(ToolsWS.class.getName());

    public ToolsWS() {
        ApplicationContext ctx = AppContext.getApplicationContext();
    }

    @POST
    @Path("/clear")
    public String clear(@Context HttpServletRequest requestContext) {
        try {
            Tools.clearCache();
            Tools.clearSessions();
            return "success";
        } catch (CacheException ex) {
            Logger.getLogger(ToolsWS.class.getName()).log(Level.SEVERE, null, ex);
            return "error";
        }
    }
}
