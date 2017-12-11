package org.zaproxy.zap.view.panelsearch.items;

import java.awt.Container;

public class ContainerSearch extends AbstractComponentSearch<Container> {

    @Override
    protected Object[] getComponentsInternal(Container component) {
        return component.getComponents();
    }
}

