package org.zaproxy.zap.view.panelsearch;

public class InStringSearchQuery implements SearchQuery{

    private String searchingFor;

    public InStringSearchQuery(String searchingFor) {
        this.searchingFor = searchingFor;
    }

    @Override
    public boolean match(String value) {
        if(value == null) return false;
        return value.toLowerCase().indexOf(searchingFor.toLowerCase()) > -1;
    }
}
