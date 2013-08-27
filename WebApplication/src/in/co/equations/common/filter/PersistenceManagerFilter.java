package in.co.equations.common.filter;

import com.thenextpointer.db.Datastore;
import com.thenextpointer.spring.AppContext;
import java.io.IOException;
import javax.servlet.*;
import org.springframework.context.ApplicationContext;

/**
 *
 * @author devashish
 */
public class PersistenceManagerFilter implements javax.servlet.Filter {

    private Datastore datastore;

    public Datastore getDatastore() {
        return datastore;
    }

    public void setDatastore(Datastore datastore) {
        this.datastore = datastore;
    }

    @Override
    public void init(FilterConfig filterConfig) {
        ApplicationContext ctx = AppContext.getApplicationContext();
        //datastore = (Datastore) ctx.getBean("datastore");
        datastore = new Datastore();
        datastore.initialize();
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        try {
            request.setAttribute("datastore", datastore);
            chain.doFilter(request, response);
        } finally {
            datastore.finishRequest();
        }
    }

    @Override
    public void destroy() {
        //throw new UnsupportedOperationException("Not supported yet.");
    }
}