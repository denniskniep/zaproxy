package org.zaproxy.zap.view.panelsearch.items;

import org.zaproxy.zap.view.panelsearch.HighlightedComponent;
import org.zaproxy.zap.view.panelsearch.SearchQuery;

import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.Component;

public class TreeNodeSearch extends AbstractComponentSearch<DefaultMutableTreeNode> {

    @Override
    protected boolean isSearchMatchingInternal(DefaultMutableTreeNode component, SearchQuery query) {
        return query.match("");
    }

    @Override
    protected Component[] getComponentsInternal(DefaultMutableTreeNode component) {
        return new Component[]{};
    }

    @Override
    protected HighlightedComponent highlightInternal(DefaultMutableTreeNode component) {
        //component.setBackground(new Color(255, 204, 0));
        return new HighlightedComponent(component);
    }
}

