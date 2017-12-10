package org.zaproxy.zap.view.panelsearch.items;

import org.zaproxy.zap.view.panelsearch.HighlightedComponent;
import org.zaproxy.zap.view.panelsearch.HighlighterUtils;

import javax.swing.JTable;
import java.util.ArrayList;

public class TableSearch extends AbstractComponentSearch<JTable>  {

    @Override
    protected Object[] getComponentsInternal(JTable component) {
        ArrayList<TableCellElement> elements = new ArrayList<>();
        for (int rIndex = 0; rIndex < component.getRowCount(); rIndex++) {
            for (int cIndex = 0; cIndex < component.getColumnCount(); cIndex++) {
                Object cellValue = component.getModel().getValueAt(rIndex, cIndex);
                Object columnIdentifier = component.getColumnModel().getColumn(cIndex).getIdentifier();
                elements.add(new TableCellElement(component, columnIdentifier, cIndex, rIndex, cellValue));
            }
        }

        return elements.toArray();
    }

    @Override
    protected HighlightedComponent highlightAsParentInternal(JTable component) {
        return HighlighterUtils.highlightBorder(component, HighlighterUtils.DefaultHighlightColor);
    }

    @Override
    protected void undoHighlightAsParentInternal(HighlightedComponent highlightedComponent, JTable component) {
        HighlighterUtils.undoHighlightBorder(highlightedComponent, component);
    }
}
