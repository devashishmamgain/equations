package com.thenextpointer.pagination;

public class Rule {
    // ['eq','ne','lt','le','gt','ge','bw','bn','in','ni','ew','en','cn','nc']

    //Todo: Add the operator expression for the remaining
    public static enum OPERATOR {
        eq("=="), ne("!="), lt("<"), le("<="), gt(">"), ge(">="), bw(""), bn(""),
        in(""), ni(""), ew(""), en(""), cn("contains"), nc("");
        
        private String expr;

        private OPERATOR(String c) {
            expr = c;
        }

        public String getExpr() {
            return expr;
        }
    };
    
    private String field;
    private String op;
    private Object data;
    private String dataType;
    
    public Rule() {
        
    }

    public Rule(String field, String op, Object data, String dataType) {
        this.field = field;
        this.op = op;
        this.data = data;
        this.dataType = dataType;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getField() {
        return field;
    }

    public void setOp(String op) {
        this.op = op;
    }

    public String getOp() {
        return op;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getData() {
        return data.toString();
    }

    public Object getDataObject() {
        return data;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Rule other = (Rule) obj;
        if ((this.field == null) ? (other.field != null) : !this.field.equals(other.field)) {
            return false;
        }
        if ((this.op == null) ? (other.op != null) : !this.op.equals(other.op)) {
            return false;
        }
        if (this.data != other.data && (this.data == null || !this.data.equals(other.data))) {
            return false;
        }
        if ((this.dataType == null) ? (other.dataType != null) : !this.dataType.equals(other.dataType)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + (this.field != null ? this.field.hashCode() : 0);
        hash = 37 * hash + (this.op != null ? this.op.hashCode() : 0);
        hash = 37 * hash + (this.data != null ? this.data.hashCode() : 0);
        hash = 37 * hash + (this.dataType != null ? this.dataType.hashCode() : 0);
        return hash;
    }
}
