package org.zaproxy.zap.view.panelsearch.items;

import javax.swing.JTable;

public class TableCellElement {

    private Object value;
    private JTable table;
    private int columnIndex;
    private int rowIndex;

    public TableCellElement(JTable table, int columnIndex, int rowIndex, Object value) {
        this.table = table;
        this.columnIndex = columnIndex;
        this.rowIndex = rowIndex;
        this.value = value;
    }

    public Object getValue() {
        return value;
    }

    public JTable getTable() {
        return table;
    }

    public int getColumnIndex() {
        return columnIndex;
    }

    public int getRowIndex() {
        return rowIndex;
    }
}
