package org.zaproxy.zap.view.panelsearch.items;

import org.zaproxy.zap.view.panelsearch.HighlightedComponent;
import org.zaproxy.zap.view.panelsearch.HighlighterUtils;
import org.zaproxy.zap.view.panelsearch.SearchQuery;

import javax.swing.JLabel;

public class LabelSearch extends AbstractComponentSearch<JLabel> {

    @Override
    protected boolean isSearchMatchingInternal(JLabel component, SearchQuery query) {
        return query.match(component.getText());
    }

    @Override
    protected Object[] getComponentsInternal(JLabel component) {
        return new Object[]{};
    }

    @Override
    protected HighlightedComponent highlightInternal(JLabel component) {
        return HighlighterUtils.highlightBackground(component, HighlighterUtils.DefaultHighlightColor);
    }

    @Override
    protected void undoHighlightInternal(HighlightedComponent highlightedComponent, JLabel component) {
       HighlighterUtils.undoHighlightBackground(highlightedComponent);
    }
}