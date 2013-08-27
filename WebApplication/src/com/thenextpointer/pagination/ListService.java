package com.thenextpointer.pagination;

import java.io.Serializable;
import java.util.Collection;

public interface ListService<T> extends Serializable {
    public int getCount(Filter filters);
    public Collection<T> getResults(Pagination pagination, Filter filters);
}
