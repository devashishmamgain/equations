package com.thenextpointer.crud;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.thenextpointer.exceptions.NonexistentEntityException;
import com.thenextpointer.exceptions.PreexistingEntityException;
import java.io.Serializable;
import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;

public class CrudAction<T, PK extends Serializable> extends ActionSupport implements SessionAware, ModelDriven<T> {    

    public enum Operator {
        CREATE, READ, UPDATE, DELETE
    };
    private static final long serialVersionUID = 1L;
    private String operator;
    private CrudService<T, PK> crudService;
    protected T t;
    private String errorMessage;
    protected Map session;
    
    @Override
    public void setSession(Map<String, Object> session) {
        this.session = session;
    }

    public void setEntity(T t) {
        this.t = t;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String performCrudAction() {
        if (Operator.CREATE.name().equals(operator)) {
            try {
                crudService.create(t);
            } catch (PreexistingEntityException e) {
                this.errorMessage = "Record already exists.";
                e.printStackTrace();                
                return com.opensymphony.xwork2.Action.ERROR;
            }
        } else if (Operator.UPDATE.name().equals(operator)) {
            try {
                crudService.update(t);
            } catch (NonexistentEntityException e) {
                e.printStackTrace();
                return com.opensymphony.xwork2.Action.ERROR;
            } catch (Exception e) {
                e.printStackTrace();
                return com.opensymphony.xwork2.Action.ERROR;
            }
        } else if (Operator.DELETE.name().equals(operator)) {
            try {
                crudService.delete(t);
            } catch (NonexistentEntityException e) {
                this.errorMessage = "No such record exists.";
                e.printStackTrace();
                return com.opensymphony.xwork2.Action.ERROR;
            }
        }
        return com.opensymphony.xwork2.Action.SUCCESS;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    @Override
    public T getModel() {
        return t;
    }

    public void setCrudService(CrudService<T, PK> crudService) {
        this.crudService = crudService;
    }
}
