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
    public final boolean isResponsible(Object component){
        return componentType.isInstance(component);
    }

    @Override
    public final boolean isSearchMatching(Object component, SearchQuery query) {
        if(isResponsible(component)){
            return isSearchMatchingInternal((T)component, query);
        }
        return false;
    }

    @Override
    public final Object[] getComponents(Object component) {
        if(isResponsible(component)){
            return getComponentsInternal((T)component);
        }
        return new Component[]{};
    }

    @Override
    public final HighlightedComponent highlight(Object component){
        if(isResponsible(component)){
            return highlightInternal((T)component);
        }
        return null;
    }

    @Override
    public final HighlightedComponent highlightAsParent(Object component){
        if(isResponsible(component)){
            return highlightAsParentInternal((T)component);
        }
        return null;
    }

    @Override
    public final void undoHighlight(HighlightedComponent highlightedComponent) {
        if(isResponsible(highlightedComponent.getComponent())){
            undoHighlightInternal(highlightedComponent, (T)highlightedComponent.getComponent());
        }
    }

    @Override
    public final void undoHighlightAsParent(HighlightedComponent highlightedParentComponent) {
        if(isResponsible(highlightedParentComponent.getComponent())){
            undoHighlightAsParentInternal(highlightedParentComponent, (T)highlightedParentComponent.getComponent());
        }
    }

    protected boolean isSearchMatchingInternal(T component, SearchQuery query){
        return false;
    }

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
