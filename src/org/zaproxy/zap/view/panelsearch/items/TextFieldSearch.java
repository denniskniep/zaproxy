package org.zaproxy.zap.view.panelsearch.items;

import org.zaproxy.zap.view.panelsearch.HighlightedComponent;
import org.zaproxy.zap.view.panelsearch.HighlighterUtils;
import org.zaproxy.zap.view.panelsearch.SearchQuery;

import javax.swing.JTextField;

public class TextFieldSearch extends AbstractComponentSearch<JTextField> {

    @Override
    protected boolean isSearchMatchingInternal(JTextField component, SearchQuery query) {
        return query.match(component.getText());
    }

    @Override
    protected HighlightedComponent highlightInternal(JTextField component) {
        return HighlighterUtils.highlightBackground(component, HighlighterUtils.DefaultHighlightColor);
    }

    @Override
    protected void undoHighlightInternal(HighlightedComponent highlightedComponent, JTextField component) {
        HighlighterUtils.undoHighlightBackground(highlightedComponent, component);
    }
}
