package org.zaproxy.zap.view.panelsearch.items;

import org.zaproxy.zap.view.panelsearch.HighlightedComponent;
import org.zaproxy.zap.view.panelsearch.HighlighterUtils;
import org.zaproxy.zap.view.panelsearch.SearchQuery;

import javax.swing.BorderFactory;
import javax.swing.JTree;
import javax.swing.border.Border;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreeCellRenderer;
import java.awt.Color;
import java.awt.Component;
import java.util.ArrayList;

public class TreeNodeElementSearch extends AbstractComponentSearch<TreeNodeElement> {

    @Override
    protected boolean isSearchMatchingInternal(TreeNodeElement component, SearchQuery query) {
        if(component.getNode() == null) return false;
        return query.match(component.getNode().toString());
    }

    @Override
    protected Object[] getComponentsInternal(TreeNodeElement component) {
        return component.getFakeObjectModelChildren().toArray();
    }

    @Override
    protected HighlightedComponent highlightInternal(TreeNodeElement component) {
        HighlightTreeCellRenderer cellRenderer = wrapTreeCellRenderer(component);
        cellRenderer.addHighlightedNode(component.getNode());
        return new HighlightedComponent(component);
    }

    @Override
    protected void undoHighlightInternal(HighlightedComponent highlightedComponent, TreeNodeElement component) {
        revertWrappedTreeCellRenderer(component);
    }

    @Override
    protected HighlightedComponent highlightAsParentInternal(TreeNodeElement component) {
        HighlightTreeCellRenderer cellRenderer = wrapTreeCellRenderer(component);
        cellRenderer.addHighlightedParentNode(component.getNode());
        return new HighlightedComponent(component);
    }

    @Override
    protected void undoHighlightAsParentInternal(HighlightedComponent highlightedComponent, TreeNodeElement component) {
        revertWrappedTreeCellRenderer(component);
    }

    private HighlightTreeCellRenderer wrapTreeCellRenderer(TreeNodeElement component){
        if(!(component.getTree().getCellRenderer() instanceof HighlightTreeCellRenderer)){
            TreeCellRenderer currentCellRenderer = component.getTree().getCellRenderer();
            component.getTree().setCellRenderer(new HighlightTreeCellRenderer(currentCellRenderer));
        }
        return (HighlightTreeCellRenderer)component.getTree().getCellRenderer();
    }

    private void revertWrappedTreeCellRenderer(TreeNodeElement component){
        if(component.getTree().getCellRenderer() instanceof HighlightTreeCellRenderer){
            HighlightTreeCellRenderer highlightCellRenderer = (HighlightTreeCellRenderer)component.getTree().getCellRenderer();
            component.getTree().setCellRenderer(highlightCellRenderer.getOriginalCellRenderer());
        }
    }

    public static class HighlightTreeCellRenderer implements TreeCellRenderer {

        private ArrayList<Object> highlightedNodes = new ArrayList<>();
        private ArrayList<Object> highlightedParentNodes = new ArrayList<>();
        private TreeCellRenderer originalCellRenderer;

        public HighlightTreeCellRenderer(TreeCellRenderer originalCellRenderer) {

            //ToDo: Maybe deepCopy?
            this.originalCellRenderer = originalCellRenderer;
        }

        public void addHighlightedNode(Object node){
            highlightedNodes.add(node);
        }

        public void addHighlightedParentNode(Object node){
            highlightedParentNodes.add(node);
        }

        @Override
        public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean expanded, boolean leaf, int row, boolean hasFocus) {
            Component cell = originalCellRenderer.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);

            if(highlightedNodes.contains(value)){
                trySetOpaque(cell, true);
                trySetBorder(cell, null);
                cell.setBackground(HighlighterUtils.DefaultHighlightColor);
            }else if(highlightedParentNodes.contains(value)){
                trySetOpaque(cell, false);
                trySetBorder(cell, HighlighterUtils.DefaultHighlightColor);
                cell.setBackground(null);
            }
            else{
                //We must reset the cells Opaque and Background
                //ToDo: What if there is already a custom TreeCellRenderer with Background: red?
                trySetOpaque(cell, false);
                trySetBorder(cell, null);
                cell.setBackground(null);
            }
            return cell;
        }

        private void trySetOpaque(Component cell, boolean isOpaque) {
            if (cell instanceof DefaultTreeCellRenderer) {
                ((DefaultTreeCellRenderer) cell).setOpaque(isOpaque);
            }
        }

        private void trySetBorder(Component cell, Color color){
            if (cell instanceof DefaultTreeCellRenderer) {
                Border border = null;
                if(color != null){
                    border = BorderFactory.createLineBorder(color);
                }
                ((DefaultTreeCellRenderer) cell).setBorder(border);
            }
        }

        public TreeCellRenderer getOriginalCellRenderer() {
            if (originalCellRenderer instanceof DefaultTreeCellRenderer) {
                DefaultTreeCellRenderer originalDefaultTreeCellRenderer = (DefaultTreeCellRenderer) originalCellRenderer;
                trySetOpaque(originalDefaultTreeCellRenderer, false);
                trySetBorder(originalDefaultTreeCellRenderer, null);
                originalDefaultTreeCellRenderer.setBackground(null);
            }

            return originalCellRenderer;
        }
    }
}



