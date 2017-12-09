package org.zaproxy.zap.view.panelsearch.items;

import org.zaproxy.zap.view.panelsearch.SearchQuery;

public class ComboBoxElementSearch extends AbstractComponentSearch<ComboBoxElement> {

    @Override
    protected boolean isSearchMatchingInternal(ComboBoxElement component, SearchQuery query) {
        return query.match(component.getItem().toString());
    }
}
