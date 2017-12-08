package org.zaproxy.zap.view.panelsearch.items;

import org.zaproxy.zap.view.panelsearch.SearchQuery;

public interface ComponentSearch {
    boolean isResponsible(Object component);
    boolean isSearchMatching(Object component, SearchQuery query);
    Object[] getComponents(Object component);
}

