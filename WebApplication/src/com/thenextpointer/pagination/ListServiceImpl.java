package com.thenextpointer.pagination;

import com.thenextpointer.db.Datastore;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;

/**
 *
 * @author devashish
 */
public class ListServiceImpl<T> implements ListService<T> {

    private Class<T> type;

    public ListServiceImpl() {
        /*
         * System.out.println("#listserviceimpl: " +
         * ((java.lang.reflect.ParameterizedType)this.getClass().getGenericSuperclass()).getActualTypeArguments());
         * if
         * (((java.lang.reflect.ParameterizedType)this.getClass().getGenericSuperclass()).getActualTypeArguments()
         * != null) { type = (Class) ((java.lang.reflect.ParameterizedType)
         * this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        }
         */
    }

    public Class<T> getType() {
        return type;
    }

    public void setType(Class<T> type) {
        this.type = type;
    }

    @Override
    public int getCount(Filter filter) {
        return Integer.parseInt(executeQuery(null, filter, "count(this)").toString());
    }

    @Override
    public Collection<T> getResults(Pagination pagination, Filter filter) {
        return (Collection<T>) executeQuery(pagination, filter, null);
    }

    public Object executeQuery(Pagination pagination, Filter filter, String queryResult) {
        Query query = prepareQuery(pagination, filter);
        Map params = getParams(filter);
        if (queryResult != null && !queryResult.isEmpty()) {
            query.setResult(queryResult);
        }

        if (params.isEmpty()) {
            return query.execute();
        } else {
            return query.executeWithMap(params);
        }
    }

    public Query prepareQuery(Pagination pagination, Filter filter) {
        PersistenceManager pm = new Datastore().getPersistanceManager();
        Query query = pm.newQuery(type);

        if (pagination != null) {
            long startIndex = 0;
            if (pagination.getStartIndex() != 0) {
                startIndex = pagination.getStartIndex();
            } else if (pagination.getPageId() > 1) {
                startIndex = (pagination.getPageId() - 1) * pagination.getPageSize();
            }
            
            if (pagination.getPageSize() != null) {                
                long endIndex = startIndex + pagination.getPageSize();
                //Todo: Replace query.setRange as it is not efficient.
                query.setRange(startIndex, endIndex);
            }

            String sort = pagination.getSort();
            if (sort != null && !sort.isEmpty()) {
                query.setOrdering(sort + " " + pagination.getOrder());
            }
        }

        List params = new ArrayList();
        if (filter != null) {
            if (filter.getRules() != null && !filter.getRules().isEmpty()) {
                String filterString = "";
                String declareParameters = "";
                int i = 0;
                int totalRules = filter.getRules().size();
                for (Rule rule : filter.getRules()) {
                    params.add(rule.getDataObject());
                    String expr = Rule.OPERATOR.valueOf(rule.getOp()).getExpr();

                    if (expr.equals("contains")) {
                        filterString += rule.getField() + "." + expr + "(param" + i + ") ";
                    } else {
                        filterString += rule.getField() + expr + " param" + i + " ";
                    }

                    if (filter.getGroupOp() != null && !filter.getGroupOp().isEmpty() && i < (totalRules - 1)) {
                        //filterString += " " + filter.getGroupOp() + " ";
                        //Todo: Append the operator part.
                        filterString += " && ";
                    }
                    declareParameters += (rule.getDataType() == null ? "String" : rule.getDataType()) + " " + "param" + i + ", ";
                    i++;
                }

                query.setFilter(filterString);
                query.declareParameters(declareParameters.substring(0, declareParameters.length() - 2));
            }
        }

        return query;
    }

    public Map getParams(Filter filter) {
        Map params = new HashMap();
        if (filter != null && filter.getRules() != null) {
            int i = 0;
            for (Rule rule : filter.getRules()) {
                if (rule.getDataType() != null && !rule.getDataType().isEmpty() && !rule.getDataType().equals("String")) {
                    params.put("param" + i, rule.getDataObject());
                } else {
                    params.put("param" + i, rule.getData());
                }
                i++;
            }
        }

        return params;
    }
}
