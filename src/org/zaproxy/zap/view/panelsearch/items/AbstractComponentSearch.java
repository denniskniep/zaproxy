/*
 *  Zed Attack Proxy (ZAP) and its related class files.
 *
 *  ZAP is an HTTP/HTTPS proxy for assessing web application security.
 *
 *  Copyright 2014 The ZAP Development Team
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

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
