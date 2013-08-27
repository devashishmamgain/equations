package in.co.equations.common.modules.taskqueue;

import com.google.appengine.api.taskqueue.Queue;
import com.google.appengine.api.taskqueue.QueueFactory;
import com.google.appengine.api.taskqueue.TaskOptions;

public class TaskService {

    public String clearSessionAndCache() {
        Queue queue = QueueFactory.getQueue("clear-queue");
        queue.add(TaskOptions.Builder.withUrl("/rest/ws/tools/clear")
                .method(TaskOptions.Method.POST));
        return "success";
    }
}
