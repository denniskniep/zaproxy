package org.zaproxy.zap.view.panelsearch.items;

import org.zaproxy.zap.view.panelsearch.HighlightedComponent;
import org.zaproxy.zap.view.panelsearch.SearchQuery;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import java.awt.Color;
import java.util.ArrayList;

public class ComboBoxSearch extends AbstractComponentSearch<JComboBox> {

    private static final String BORDER = "Border";
    private static final String OPAQUE = "Opaque";
    private static final String BACKGROUND = "Background";

    @Override
    protected boolean isSearchMatchingInternal(JComboBox component, SearchQuery query) {
        Object selectedItem = component.getSelectedItem();
        if(selectedItem != null) {
            return query.match(selectedItem.toString());
        }
        return false;
    }

    @Override
    protected HighlightedComponent highlightInternal(JComboBox component) {
        HighlightedComponent highlightedComponent = new HighlightedComponent(component);
        highlightedComponent.put(OPAQUE, component.isOpaque());
        highlightedComponent.put(BACKGROUND, component.getBackground());

        component.setOpaque(true);
        component.setBackground(new Color(255, 204, 0));

        return highlightedComponent;
    }

    @Override
    protected void undoHighlightInternal(HighlightedComponent highlightedComponent, JComboBox component) {
        component.setOpaque(highlightedComponent.get(OPAQUE));
        component.setBackground(highlightedComponent.get(BACKGROUND));
    }

    @Override
    protected Object[] getComponentsInternal(JComboBox component) {
        ArrayList<ComboBoxElement> items = new ArrayList<>();
        for (int i = 0; i < component.getItemCount(); i++) {
            Object item = component.getItemAt(i);
            items.add(new ComboBoxElement(item, i, component));
        }
        return items.toArray();
    }

    @Override
    protected HighlightedComponent highlightAsParentInternal(JComboBox component) {
        HighlightedComponent highlightedComponent = new HighlightedComponent(component);
        highlightedComponent.put(BORDER, component.getBorder());
        component.setBorder(BorderFactory.createLineBorder(new Color(255, 204, 0)));
        return highlightedComponent;
    }

    @Override
    protected void undoHighlightAsParentInternal(HighlightedComponent highlightedComponent, JComboBox component) {
        component.setBorder(highlightedComponent.get(BORDER));
    }

}