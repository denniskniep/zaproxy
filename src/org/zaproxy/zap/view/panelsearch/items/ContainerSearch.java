package org.zaproxy.zap.view.panelsearch.items;

import org.zaproxy.zap.view.panelsearch.SearchQuery;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import java.awt.Container;
import java.util.Arrays;
import java.util.List;

public class ContainerSearch implements ComponentSearch {

    @Override
    public boolean isResponsible(Object component) {
        return true;
    }

    @Override
    public boolean isSearchMatching(Object component, SearchQuery query) {
        return false;
    }

    @Override
    public Object[] getComponents(Object component) {
          if(component instanceof Container){
              return ((Container)component).getComponents();
          }
          return new Object[]{};
    }
}

