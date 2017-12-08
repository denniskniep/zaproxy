package org.zaproxy.zap.view.panelsearch.items;

import org.zaproxy.zap.view.panelsearch.HighlightedComponent;
import org.zaproxy.zap.view.panelsearch.SearchQuery;

import javax.swing.AbstractButton;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Component;

public class LabelSearch extends AbstractComponentSearch<JLabel> {

    private static final String OPAQUE = "Opaque";
    private static final String BACKGROUND = "Background";

    @Override
    protected boolean isSearchMatchingInternal(JLabel component, SearchQuery query) {
        return query.match(component.getText());
    }

    @Override
    protected Component[] getComponentsInternal(JLabel component) {
        return new Component[]{};
    }

    @Override
    protected HighlightedComponent highlightInternal(JLabel component) {
        HighlightedComponent highlightedComponent = new HighlightedComponent(component);
        highlightedComponent.put(OPAQUE, component.isOpaque());
        highlightedComponent.put(BACKGROUND, component.getBackground());

        component.setOpaque(true);
        component.setBackground(new Color(255, 204, 0));

        return highlightedComponent;
    }

    @Override
    protected void undoHighlightInternal(HighlightedComponent highlightedComponent, JLabel component) {
        component.setOpaque(highlightedComponent.get(OPAQUE));
        component.setBackground(highlightedComponent.get(BACKGROUND));
    }
}
