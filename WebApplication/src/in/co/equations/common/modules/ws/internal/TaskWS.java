package in.co.equations.common.modules.ws.internal;

import com.thenextpointer.spring.AppContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import in.co.equations.common.modules.taskqueue.TaskService;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 *
 * @author devashish
 */
@Path("/ws/internal/task/")
@Component
public class TaskWS {
    
    private TaskService taskService;
    
    public TaskWS() {
        ApplicationContext ctx = AppContext.getApplicationContext();
        taskService = (TaskService) ctx.getBean("taskService");        
    }
    
    @POST
    @Path("/clear")
    public String clear(@Context HttpServletRequest requestContext) {
       taskService.clearSessionAndCache();
       return "success";
    }
   
}
