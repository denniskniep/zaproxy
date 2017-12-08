package org.zaproxy.zap.view.panelsearch.items;

import org.zaproxy.zap.view.panelsearch.SearchQuery;

public interface ComponentSearch {
    boolean isSearchMatching(Object component, SearchQuery query);
    Object[] getComponents(Object component);
}

