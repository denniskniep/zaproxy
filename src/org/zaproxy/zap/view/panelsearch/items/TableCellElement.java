package org.zaproxy.zap.view.panelsearch.items;

import javax.swing.JTable;

public class TableCellElement {

    private Object value;
    private JTable table;
    private Object columnIdentifier;
    private int columnIndex;
    private int rowIndex;

    public TableCellElement(JTable table, Object columnIdentifier, int columnIndex, int rowIndex, Object value) {
        this.table = table;
        this.columnIdentifier = columnIdentifier;
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

    public Object getColumnIdentifier() {
        return columnIdentifier;
    }

    public int getRowIndex() {
        return rowIndex;
    }

    public int getColumnIndex() {
        return columnIndex;
    }
}
