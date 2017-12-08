package org.zaproxy.zap.view.panelsearch.items;

import org.zaproxy.zap.view.panelsearch.SearchQuery;

import javax.swing.JComponent;
import javax.swing.JPanel;
import java.awt.Component;
import java.awt.Container;
import java.util.Arrays;
import java.util.List;

public class ContainerSearch implements ComponentSearch {

    private static final List<Class> containerClasses = Arrays.asList(
        Container.class,
        JPanel.class,
        JComponent.class
    );

    @Override
    public boolean isSearchMatching(Object component, SearchQuery query) {
        return false;
    }

    @Override
    public Object[] getComponents(Object component) {
          if(containerClasses.stream().anyMatch((c) -> c.isInstance(component))){
              return ((Container)component).getComponents();
          }
          return new Object[]{};
    }
}

