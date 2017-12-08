package org.zaproxy.zap.view.panelsearch.items;

import org.zaproxy.zap.view.panelsearch.HighlightedComponent;
import org.zaproxy.zap.view.panelsearch.SearchQuery;

import java.awt.Component;
import java.lang.reflect.ParameterizedType;

public abstract class AbstractComponentSearch<T> implements ComponentSearch, ComponentHighlighter {

    private Class componentType;

    public AbstractComponentSearch() {
        this.componentType = ((Class)((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0]);
    }

    @Override
    public boolean isSearchMatching(Object component, SearchQuery query) {
        if(componentType.isInstance(component)){
            return isSearchMatchingInternal((T)component, query);
        }
        return false;
    }

    @Override
    public Object[] getComponents(Object component) {
        if(componentType.isInstance(component)){
            return getComponentsInternal((T)component);
        }
        return new Component[]{};
    }

    @Override
    public HighlightedComponent highlight(Object component){
        if(componentType.isInstance(component)){
            return highlightInternal((T)component);
        }
        return null;
    }

    @Override
    public HighlightedComponent highlightAsParent(Object component){
        if(componentType.isInstance(component)){
            return highlightAsParentInternal((T)component);
        }
        return null;
    }

    @Override
    public void undoHighlight(HighlightedComponent highlightedComponent) {
        if(componentType.isInstance(highlightedComponent.getComponent())){
            undoHighlightInternal(highlightedComponent, (T)highlightedComponent.getComponent());
        }
    }

    @Override
    public void undoHighlightAsParent(HighlightedComponent highlightedParentComponent) {
        if(componentType.isInstance(highlightedParentComponent.getComponent())){
            undoHighlightAsParentInternal(highlightedParentComponent, (T)highlightedParentComponent.getComponent());
        }
    }

    protected abstract boolean isSearchMatchingInternal(T component, SearchQuery query);

    protected Object[] getComponentsInternal(T component){
        return new Object[]{};
    }

    protected HighlightedComponent highlightInternal(T component){
        return null;
    }

    protected HighlightedComponent highlightAsParentInternal(T component){
        return null;
    }

    protected void undoHighlightInternal(HighlightedComponent highlightedComponent, T component){
    }

    protected void undoHighlightAsParentInternal(HighlightedComponent highlightedComponent, T component){
    }
}
