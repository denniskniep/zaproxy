package org.parosproxy.paros.view;

import org.zaproxy.zap.view.panelsearch.items.AbstractComponentSearch;
import org.zaproxy.zap.view.panelsearch.items.TreeNodeElement;
import org.zaproxy.zap.view.panelsearch.items.TreeSearch;

import java.util.ArrayList;

public class AbstractParamContainerPanelComponentSearch extends AbstractComponentSearch<AbstractParamContainerPanel> {

    @Override
    protected Object[] getComponentsInternal(AbstractParamContainerPanel component) {

        // The tree contains the nodes and there is a valueChanged listener on the tree.
        // OnValueChanged the ParamPanel will be switched. So it behaves as a child component.
        // But in the ObjectModel they have no parent/child relation.
        // This class is for faking the parent/child relation between TreeNode and ParamPanel.
        // The name of the panel is the name of the Node

        TreeSearch search = new TreeSearch();
        //ToDo: Respect Hierarchy
        ArrayList<TreeNodeElement> treeNodeElements = search.getTreeNodeElement(component.getTreeParam());
        for (TreeNodeElement treeNodeElement : treeNodeElements){
            AbstractParamPanel panel = component.getParamPanel(treeNodeElement.getNode().toString());
            treeNodeElement.addFakeObjectModelChildren(panel);
        }
        return treeNodeElements.toArray();
    }
}