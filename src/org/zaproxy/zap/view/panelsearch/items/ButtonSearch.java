package org.zaproxy.zap.view.panelsearch.items;

import org.zaproxy.zap.view.panelsearch.HighlightedComponent;
import org.zaproxy.zap.view.panelsearch.HighlighterUtils;
import org.zaproxy.zap.view.panelsearch.SearchQuery;

import javax.swing.AbstractButton;
import java.awt.Component;

public class ButtonSearch extends AbstractComponentSearch<AbstractButton> {

    @Override
    protected boolean isSearchMatchingInternal(AbstractButton component, SearchQuery query) {
        return query.match(component.getText());
    }

    @Override
    protected Component[] getComponentsInternal(AbstractButton component) {
        return new Component[]{};
    }

    @Override
    protected HighlightedComponent highlightInternal(AbstractButton component) {
        return HighlighterUtils.highlightBackground(component, HighlighterUtils.DefaultHighlightColor);
    }

    @Override
    protected void undoHighlightInternal(HighlightedComponent highlightedComponent, AbstractButton component) {
        HighlighterUtils.undoHighlightBackground(highlightedComponent, component);
    }
}

