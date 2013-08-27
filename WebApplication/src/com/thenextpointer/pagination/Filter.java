package com.thenextpointer.pagination;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Filter {

    private String groupOp;
    private Set<Rule> rules;
    
    public Filter() {
        
    }
    
    public Filter(Rule rule) {
        this.rules = new HashSet<Rule>(1);
        this.rules.add(rule);
    }
    
    public Filter(Collection rules) {
        this.rules = new HashSet<Rule>(rules);
    }

    public void setGroupOp(String groupOp) {
        this.groupOp = groupOp;
    }

    public String getGroupOp() {
        return groupOp;
    }

    public void setRules(Set<Rule> rules) {
        this.rules = rules;
    }
    
    public void setRules(List<Rule> rulesList) {
        this.rules = new HashSet<Rule>(rulesList);
    }

    public Set<Rule> getRules() {
        return rules;
    }
}