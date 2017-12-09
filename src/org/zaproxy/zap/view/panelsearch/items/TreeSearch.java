package org.zaproxy.zap.view.panelsearch.items;

import javax.swing.JTree;
import java.util.ArrayList;

public class TreeSearch extends AbstractComponentSearch<JTree> {

    @Override
    protected Object[] getComponentsInternal(JTree component) {
       return getTreeNodeElement(component).toArray();
    }

    public ArrayList<TreeNodeElement> getTreeNodeElement(JTree component){
        Object rootNode = component.getModel().getRoot();
        return getTreeNodeElementRecursive(component, rootNode);
    }

    private ArrayList<TreeNodeElement> getTreeNodeElementRecursive(JTree tree, Object node) {
        ArrayList<TreeNodeElement> elements = new ArrayList<>();
        elements.add(new TreeNodeElement(node, tree));

        for(Object childNodes : getChildNodes(tree, node)){
            elements.addAll(getTreeNodeElementRecursive(tree, childNodes));
        }
        return elements;
    }

    private ArrayList<Object> getChildNodes(JTree tree, Object node){
        ArrayList<Object> nodes = new ArrayList<>();
        int childCount = tree.getModel().getChildCount(node);

        for (int i = 0; i < childCount; i++) {
            Object child = tree.getModel().getChild(node, i);
            nodes.add(child);
        }

        return nodes;
    }
}

