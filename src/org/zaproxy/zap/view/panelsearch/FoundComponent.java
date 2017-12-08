package org.zaproxy.zap.view.panelsearch;

import org.zaproxy.zap.view.panelsearch.items.ComponentSearch;
import org.zaproxy.zap.view.panelsearch.items.Generic;

import java.util.ArrayList;
import java.util.List;

public class FoundComponent{
    private final Object component;
    private final List<Object> parents;
    private final ComponentSearch foundBy;

    public FoundComponent(Object component, ComponentSearch foundBy) {
        this.component = component;
        this.foundBy = foundBy;
        this.parents = new ArrayList<>();
    }

    public Object getComponent() {
        return component;
    }

    public <T> T getComponentCasted() {
        return Generic.cast(component);
    }

    public ComponentSearch getFoundBy() {
        return foundBy;
    }

    public void addParent(Object parent){
        parents.add(parent);
    }

    public List<Object> getParents() {
        return new ArrayList<>(parents);
    }

    public <T> T getParentAtCasted(int index) {
        return Generic.cast(parents.get(index));
    }
}
