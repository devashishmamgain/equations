package com.thenextpointer.listener;

import com.google.apphosting.api.ApiProxy;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import ognl.OgnlRuntime;


/**
 *
 * @author Adarsh
 */


public class OgnlListener implements ServletContextListener {
    
    public static final String APP_ID = ApiProxy.getCurrentEnvironment().getAppId().replace("s~", "");
    public static final String APP_VERSION = ApiProxy.getCurrentEnvironment().getVersionId();

    @Override
    public void contextDestroyed(ServletContextEvent arg0) {
        // TODO Auto-generated method stub
    }
    @Override
    public void contextInitialized(ServletContextEvent arg0) {
       OgnlRuntime.setSecurityManager(null);
    }

}
