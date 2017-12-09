package org.zaproxy.zap.view.panelsearch.items;

import org.zaproxy.zap.view.panelsearch.HighlightedComponent;
import org.zaproxy.zap.view.panelsearch.HighlighterUtils;
import org.zaproxy.zap.view.panelsearch.SearchQuery;

import javax.swing.JComboBox;
import java.util.ArrayList;

public class ComboBoxSearch extends AbstractComponentSearch<JComboBox> {

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
        return HighlighterUtils.highlightBackground(component, HighlighterUtils.DefaultHighlightColor);
    }

    @Override
    protected void undoHighlightInternal(HighlightedComponent highlightedComponent, JComboBox component) {
        HighlighterUtils.undoHighlightBackground(highlightedComponent, component);
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
        return HighlighterUtils.highlightBorder(component, HighlighterUtils.DefaultHighlightColor);
    }

    @Override
    protected void undoHighlightAsParentInternal(HighlightedComponent highlightedComponent, JComboBox component) {
        HighlighterUtils.undoHighlightBorder(highlightedComponent, component);
    }

}