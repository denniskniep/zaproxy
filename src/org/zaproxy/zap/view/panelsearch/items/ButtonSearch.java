package org.zaproxy.zap.view.panelsearch.items;

import org.zaproxy.zap.view.panelsearch.HighlightedComponent;
import org.zaproxy.zap.view.panelsearch.SearchQuery;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Component;

public class ButtonSearch extends AbstractComponentSearch<AbstractButton> {

    private static final String OPAQUE = "Opaque";
    private static final String BACKGROUND = "Background";

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
        HighlightedComponent highlightedComponent = new HighlightedComponent(component);
        highlightedComponent.put(OPAQUE, component.isOpaque());
        highlightedComponent.put(BACKGROUND, component.getBackground());

        component.setOpaque(true);
        component.setBackground(new Color(255, 204, 0));

        return highlightedComponent;
    }

    @Override
    protected void undoHighlightInternal(HighlightedComponent highlightedComponent, AbstractButton component) {
        component.setOpaque(highlightedComponent.get(OPAQUE));
        component.setBackground(highlightedComponent.get(BACKGROUND));
    }
}

