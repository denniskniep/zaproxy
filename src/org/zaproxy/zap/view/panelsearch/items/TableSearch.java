package org.zaproxy.zap.view.panelsearch.items;

import javax.swing.JTable;
import java.util.ArrayList;

public class TableSearch extends AbstractComponentSearch<JTable>  {

    @Override
    protected Object[] getComponentsInternal(JTable component) {
        ArrayList<TableCellElement> elements = new ArrayList<>();
        for (int rIndex = 0; rIndex < component.getRowCount(); rIndex++) {
            for (int cIndex = 0; cIndex < component.getColumnCount(); cIndex++) {
                Object cellValue = component.getModel().getValueAt(rIndex, cIndex);
                elements.add(new TableCellElement(component, cIndex, rIndex, cellValue));
            }
        }

        return elements.toArray();
    }
}
