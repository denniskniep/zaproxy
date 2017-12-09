package org.zaproxy.zap.view.panelsearch.items;

import org.zaproxy.zap.view.panelsearch.HighlightedComponent;
import org.zaproxy.zap.view.panelsearch.HighlighterUtils;
import org.zaproxy.zap.view.panelsearch.SearchQuery;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import java.awt.Component;
import java.util.ArrayList;

public class TableCellElementSearch extends AbstractComponentSearch<TableCellElement> {

    @Override
    protected boolean isSearchMatchingInternal(TableCellElement component, SearchQuery query) {
        return query.match(component.getValue().toString());
    }

    @Override
    protected HighlightedComponent highlightInternal(TableCellElement component) {
        HighlightTableCellRenderer cellRenderer = wrapTableCellRenderer(component);
        cellRenderer.addHighlighted(component);
        return new HighlightedComponent(component);
    }

    @Override
    protected void undoHighlightInternal(HighlightedComponent highlightedComponent, TableCellElement component) {
        revertWrappedTableCellRenderer(component);
    }

    private HighlightTableCellRenderer wrapTableCellRenderer(TableCellElement component){
        if(!(getCellRenderer(component) instanceof HighlightTableCellRenderer)){
            TableCellRenderer currentRenderer = getCellRenderer(component);
            setCellRenderer(component, new HighlightTableCellRenderer(currentRenderer));
        }
        return (HighlightTableCellRenderer)getCellRenderer(component);
    }

    private void revertWrappedTableCellRenderer(TableCellElement component){
        if(getCellRenderer(component) instanceof HighlightTableCellRenderer){
            HighlightTableCellRenderer highlightRenderer = (HighlightTableCellRenderer) getCellRenderer(component);
            setCellRenderer(component, highlightRenderer.getOriginalRenderer());
        }
    }

    private TableCellRenderer getCellRenderer(TableCellElement component){
        return component.getTable().getColumn(component.getColumnIdentifier()).getCellRenderer();
    }

    private void setCellRenderer(TableCellElement component, TableCellRenderer renderer){
        component.getTable().getColumn(component.getColumnIdentifier()).setCellRenderer(renderer);
    }

    public static class HighlightTableCellRenderer implements TableCellRenderer{

        private TableCellRenderer fallBackRenderer;
        private TableCellRenderer originalRenderer;
        private ArrayList<TableCellElement> highlighted = new ArrayList<>();

        public HighlightTableCellRenderer(TableCellRenderer originalRenderer) {
            this.originalRenderer = originalRenderer;
            this.fallBackRenderer = new DefaultTableCellRenderer();
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            Component item = getRender().getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            Object columnIdentifier = table.getColumnModel().getColumn(column).getIdentifier();
            if (highlighted.stream().anyMatch(e -> e.getColumnIdentifier().equals(columnIdentifier) && e.getRowIndex() == row)) {
                trySetOpaque(item, true);
                item.setBackground(HighlighterUtils.DefaultHighlightColor);
            } else {
                trySetOpaque(item, false);
                item.setBackground(null);
            }
            return item;
        }

        private void trySetOpaque(Component item, boolean isOpaque) {
            if (item instanceof DefaultTableCellRenderer) {
                ((DefaultTableCellRenderer)item).setOpaque(isOpaque);
            }
        }

        public TableCellRenderer getOriginalRenderer(){
            return originalRenderer;
        }

        public void addHighlighted(TableCellElement element) {
            highlighted.add(element);
        }

        public TableCellRenderer getRender() {
            if(originalRenderer == null){
                return fallBackRenderer;
            }
            return originalRenderer;
        }
    }
}
