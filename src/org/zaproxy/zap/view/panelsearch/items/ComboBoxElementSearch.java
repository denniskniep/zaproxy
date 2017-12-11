package org.zaproxy.zap.view.panelsearch.items;

import org.zaproxy.zap.view.panelsearch.HighlightedComponent;
import org.zaproxy.zap.view.panelsearch.HighlighterUtils;
import org.zaproxy.zap.view.panelsearch.SearchQuery;

import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.plaf.basic.BasicComboBoxRenderer;
import java.awt.Component;
import java.util.ArrayList;

public class ComboBoxElementSearch extends AbstractComponentSearch<ComboBoxElement> {

    @Override
    protected boolean isSearchMatchingInternal(ComboBoxElement component, SearchQuery query) {
        return query.match(component.getItem().toString());
    }

    @Override
    protected HighlightedComponent highlightInternal(ComboBoxElement component) {
        HighlightComboBoxItemRenderer cellRenderer = wrapComboBoxItemRenderer(component);
        cellRenderer.addHighlightedIndex(component.getItemIndex());
        return new HighlightedComponent(component);
    }

    @Override
    protected void undoHighlightInternal(HighlightedComponent highlightedComponent, ComboBoxElement component) {
        revertWrappedComboBoxItemRenderer(component);
    }

    private HighlightComboBoxItemRenderer wrapComboBoxItemRenderer(ComboBoxElement component){
        if(!(getRenderer(component) instanceof HighlightComboBoxItemRenderer)){
            ListCellRenderer currentRenderer = getRenderer(component);
            setRenderer(component, new HighlightComboBoxItemRenderer(currentRenderer));
        }
        return (HighlightComboBoxItemRenderer) getRenderer(component);
    }

    private void revertWrappedComboBoxItemRenderer(ComboBoxElement component){
        if(getRenderer(component) instanceof HighlightComboBoxItemRenderer){
            HighlightComboBoxItemRenderer highlightRenderer = (HighlightComboBoxItemRenderer) getRenderer(component);
            setRenderer(component, highlightRenderer.getOriginalRenderer());
        }
    }

    private ListCellRenderer getRenderer(ComboBoxElement component) {
        return component.getComboBox().getRenderer();
    }

    private void setRenderer(ComboBoxElement component, ListCellRenderer renderer){
        component.getComboBox().setRenderer(renderer);
    }


    public static class HighlightComboBoxItemRenderer implements ListCellRenderer<Object> {

        private ArrayList<Integer> highlightedIndexes = new ArrayList<>();
        private ListCellRenderer originalRenderer;

        public HighlightComboBoxItemRenderer(ListCellRenderer originalRenderer) {
            this.originalRenderer = originalRenderer;
        }

        @Override
        public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            Component item = originalRenderer.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            if(highlightedIndexes.contains(index)){
                trySetOpaque(item, true);
                item.setBackground(HighlighterUtils.DefaultHighlightColor);
            }else{
                trySetOpaque(item, false);
                item.setBackground(null);
            }
            return item;
        }

        private void trySetOpaque(Component item, boolean isOpaque) {
            if (item instanceof BasicComboBoxRenderer) {
                ((BasicComboBoxRenderer)item).setOpaque(isOpaque);
            }
        }

        public ListCellRenderer getOriginalRenderer(){
            //ToDo: reset rendererLike in tree?
            return originalRenderer;
        }

        public void addHighlightedIndex(int itemIndex) {
            highlightedIndexes.add(itemIndex);
        }
    }
}
