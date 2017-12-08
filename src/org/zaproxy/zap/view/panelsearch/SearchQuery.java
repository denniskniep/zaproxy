package org.zaproxy.zap.view.panelsearch;

public interface SearchQuery {
    boolean match(String value);
}
