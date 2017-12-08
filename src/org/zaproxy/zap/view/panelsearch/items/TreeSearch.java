package org.zaproxy.zap.view.panelsearch.items;

import javax.swing.JTree;
import java.util.ArrayList;

public class TreeSearch extends AbstractComponentSearch<JTree> {

    @Override
    protected Object[] getComponentsInternal(JTree component) {
       return getTreeNodeElement(component).toArray();
    }

    public ArrayList<TreeNodeElement> getTreeNodeElement(JTree component){
        ArrayList<TreeNodeElement> elements = new ArrayList<>();

        Object rootNode = component.getModel().getRoot();
        int childCount = component.getModel().getChildCount(rootNode);

        for (int i = 0; i < childCount; i++) {
            Object child = component.getModel().getChild(rootNode, i);
            elements.add(new TreeNodeElement(child, component));
        }

        return elements;
    }
}

