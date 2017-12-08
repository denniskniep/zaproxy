package org.zaproxy.zap.view.panelsearch;

import org.zaproxy.zap.view.panelsearch.items.Generic;

import java.util.HashMap;
import java.util.Map;

public class HighlightedComponent {
    private final Object component;
    Map<String, Object> store = new HashMap<>();

    public HighlightedComponent(Object component) {
        this.component = component;
    }

    public Object getComponent() {
        return component;
    }

    public <T> void put(String key, T value){
        store.put(key, value);
    }

    public <T> T get(String key){
       return Generic.cast(store.get(key));
    }
}
