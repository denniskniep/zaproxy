package org.zaproxy.zap.view.panelsearch.items;

import org.zaproxy.zap.view.panelsearch.HighlightedComponent;
import org.zaproxy.zap.view.panelsearch.HighlighterUtils;
import org.zaproxy.zap.view.panelsearch.SearchQuery;

import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import java.awt.Color;
import java.awt.Component;
import java.util.ArrayList;
import java.util.HashMap;

public class TableCellElementSearch extends AbstractComponentSearch<TableCellElement> {

    @Override
    protected boolean isSearchMatchingInternal(TableCellElement component, SearchQuery query) {
        if( query.match(component.getValue().toString())){
            return true;
        }
        return false;
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
        if(!(getColumnRenderer(component) instanceof HighlightTableCellRenderer)){
            TableCellRenderer currentColumnRenderer = getColumnRenderer(component);
            HighlightTableCellRenderer highlightTableCellRenderer = new HighlightTableCellRenderer(currentColumnRenderer);
            int cIndex = component.getTable().getColumn(component.getColumnIdentifier()).getModelIndex();
            for (int rIndex = 0; rIndex < component.getTable().getRowCount(); rIndex++) {
                TableCellRenderer currentCellRenderer = component.getTable().getCellRenderer(rIndex, cIndex);
                highlightTableCellRenderer.addCellRenderer(rIndex, currentCellRenderer);
            }

            setColumnRenderer(component, highlightTableCellRenderer);
        }
        return (HighlightTableCellRenderer) getColumnRenderer(component);
    }

    private void revertWrappedTableCellRenderer(TableCellElement component){
        if(getColumnRenderer(component) instanceof HighlightTableCellRenderer){
            HighlightTableCellRenderer highlightRenderer = (HighlightTableCellRenderer) getColumnRenderer(component);
            setColumnRenderer(component, highlightRenderer.getOriginalColumnRenderer());
        }
    }

    private TableCellRenderer getColumnRenderer(TableCellElement component){
        return component.getTable().getColumn(component.getColumnIdentifier()).getCellRenderer();
    }

    private void setColumnRenderer(TableCellElement component, TableCellRenderer renderer){
        component.getTable().getColumn(component.getColumnIdentifier()).setCellRenderer(renderer);
    }

    public static class HighlightTableCellRenderer implements TableCellRenderer{

        private TableCellRenderer fallBackRenderer;
        private TableCellRenderer originalColumnRenderer;
        private HashMap<Integer, TableCellRenderer> originalCellRenderers = new HashMap<>();
        private Color selectColor;

        private ArrayList<TableCellElement> highlighted = new ArrayList<>();

        public HighlightTableCellRenderer(TableCellRenderer originalColumnRenderer) {
            this.originalColumnRenderer = originalColumnRenderer;
            this.fallBackRenderer = new DefaultTableCellRenderer();
            this.selectColor = UIManager.getColor("Table.selectionBackground");
            if(this.selectColor == null){
                this.selectColor = new Color(57,105,138);
            }
        }

        public void addCellRenderer(int row, TableCellRenderer originalCellRenderer){
            originalCellRenderers.put(row, originalCellRenderer);
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            Component item = getRender(row).getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

            Object columnIdentifier = table.getColumnModel().getColumn(column).getIdentifier();
            if (highlighted.stream().anyMatch(e -> e.getColumnIdentifier().equals(columnIdentifier) && e.getValue().equals(value))) {
                trySetOpaque(item, true);
                item.setBackground(HighlighterUtils.DefaultHighlightColor);
            } else{
                //We must reset the cells Opaque and Background
                //ToDo: What if there is already a custom TreeCellRenderer with Background: red?


                if(isSelected){
                    trySetOpaque(item, true);
                    item.setBackground(selectColor);
                }else{
                    trySetOpaque(item, false);
                    item.setBackground(null);
                }

            }
            return item;
        }

        private void trySetOpaque(Component item, boolean isOpaque) {
            if (item instanceof DefaultTableCellRenderer) {
                ((DefaultTableCellRenderer)item).setOpaque(isOpaque);
            }
        }

        public TableCellRenderer getOriginalColumnRenderer(){
            return originalColumnRenderer;
        }

        public void addHighlighted(TableCellElement element) {
            highlighted.add(element);
        }

        public TableCellRenderer getRender(int row) {

            TableCellRenderer usedCellRenderer = null;
            TableCellRenderer originalCellRenderer = originalCellRenderers.get(row);
            if(originalCellRenderer != null){
                usedCellRenderer = originalCellRenderer;
            }
            else if(originalColumnRenderer != null){
                usedCellRenderer = originalColumnRenderer;
            }

            String className = usedCellRenderer.getClass().getName();
            if(usedCellRenderer == null || className.equals("javax.swing.plaf.synth.SynthTableUI$SynthTableCellRenderer")) {
                return fallBackRenderer;
            }

            return usedCellRenderer;
        }
    }
}
