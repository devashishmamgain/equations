package com.thenextpointer.db;

import com.thenextpointer.crud.CrudService;
import com.thenextpointer.pagination.Filter;
import com.thenextpointer.pagination.ListService;
import com.thenextpointer.pagination.Pagination;
import com.thenextpointer.pagination.Rule;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.jdo.PersistenceManager;

public class PersistenceService<T, PK extends Serializable> implements Serializable {

    private CrudService<T, PK> crudService;
    private ListService<T> listService;

    public PersistenceService() {        
    }

    public CrudService<T, PK> getCrudService() {
        return crudService;
    }

    public void setCrudService(CrudService<T, PK> crudService) {
        this.crudService = crudService;
    }

    public ListService<T> getListService() {
        return listService;
    }

    public void setListService(ListService<T> listService) {
        this.listService = listService;
    }
    
    public Object getSingleResult(Rule rule) {
        List<Rule> rules = new ArrayList<Rule>();
        rules.add(rule);
        return getSingleResult(rules);
    }
    
    public Object getSingleResult(List<Rule> rules) {
        Object object = null;
        Filter filter = new Filter(rules);   
        if (!rules.isEmpty() && rules.size() > 1) {
            filter.setGroupOp("AND");
        }
        
        Pagination pagination = new Pagination(1);        
        List<Object> objects = (List<Object>) getListService().getResults(pagination, filter); 
        
        //Todo: if objects is null or size is greater than 2 then throw some exception.
        
        if (objects != null && objects.size() > 0) {
            object = objects.get(0);
        }
        return object;
    }
    
    public List<Object> getResults(Rule rule) {
        List<Rule> rules = new ArrayList<Rule>();
        rules.add(rule);
        return getResults(rules);
    }
    
    public List<Object> getResults(List<Rule> rules) {
        return getResults(null, rules);
    }
    
    public List<Object> getResults(Pagination pagination, List<Rule> rules) {
        Filter filter = new Filter(rules);
        if (!rules.isEmpty() && rules.size() > 1) {
            filter.setGroupOp("AND");
        }

        return (List<Object>) getListService().getResults(pagination, filter);
    }
    
    public List<Object> getResults(Pagination pagination, Rule rule) {
        List<Rule> rules = new ArrayList<Rule>();
        rules.add(rule);
        return getResults(pagination, rules);
    }

    public PersistenceManager getPersistenceManager() {
        return new Datastore().getPersistanceManager();
    }
}
