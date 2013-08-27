package com.thenextpointer.pagination;

public class Pagination {

    private Integer pageSize = 5;
    private int pageId = 1;
    private String sort;
    private String order = "ASC";
    private int count;
    private int pageCount;
    private int startIndex;
    
    public Pagination() {
        
    }
    
    public Pagination(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getPageCount() {
        pageCount = (int) count / pageSize;
        pageCount = (0 == count % pageSize) ? pageCount : pageCount + 1;
        return pageCount;
    }

    public void setPageId(int pageId) {
        this.pageId = pageId;
    }

    public int getPageId() {
        return pageId;
    }

    public int getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(int startIndex) {
        this.startIndex = startIndex;
    }
}
