package com.thenextpointer.db;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.interceptor.ServletRequestAware;

/**
 *
 * @author devashish
 */
public class DatastoreManager implements ServletRequestAware {

    private HttpServletRequest request;

    @Override
    public void setServletRequest(HttpServletRequest request) {
        this.request = request;
    }

    public HttpServletRequest getServletRequest() {
        return this.request;
    }

    public Datastore getCurrentDatastore() {
        Datastore datastore = (Datastore) request.getAttribute("datastore");        
        return datastore;
    }
}
