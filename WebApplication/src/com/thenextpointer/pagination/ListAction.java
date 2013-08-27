package com.thenextpointer.pagination;

import com.google.gson.Gson;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import java.util.List;

public class ListAction<T> extends ActionSupport implements ModelDriven<T> {

    private static final long serialVersionUID = 1L;
    private T t;
    protected ListService<T> listService;
    protected Pagination pagination = new Pagination();
    protected Filter filter;
    private String filters;
    protected List<T> results;

    public void setEntity(T t) {
        this.t = t;
    }

    public String doList() {
        pagination.setCount(listService.getCount(filter));
        results = (List<T>) listService.getResults(pagination, filter);
        return com.opensymphony.xwork2.Action.SUCCESS;
    }

    public void setPagination(Pagination pagination) {
        this.pagination = pagination;
    }

    public Pagination getPagination() {
        return pagination;
    }

    public int getCount() {
        return pagination.getCount();
    }

    @Override
    public T getModel() {
        return t;
    }

    public String getFilters() {
        return filters;
    }

    public void setFilters(String filters) {
        this.filters = filters;
        Gson gson = new Gson();
        filter = gson.fromJson(filters, Filter.class);
    }

    public Filter getFilter() {
        return filter;
    }

    public void setFilter(Filter filter) {
        this.filter = filter;
    }

    public void setListService(ListService<T> listService) {
        this.listService = listService;
    }

    public void setResults(List<T> results) {
        this.results = results;
    }

    public List<T> getResults() {
        return results;
    }

    public int getTotal() {
        return pagination.getPageCount();
    }

    public int getPage() {
        return pagination.getPageId();
    }
}
