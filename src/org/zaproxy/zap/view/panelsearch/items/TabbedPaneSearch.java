package org.zaproxy.zap.view.panelsearch.items;

import javax.swing.JTabbedPane;
import java.awt.Component;
import java.util.ArrayList;

public class TabbedPaneSearch extends AbstractComponentSearch<JTabbedPane>  {

    @Override
    protected Object[] getComponentsInternal(JTabbedPane component) {
        ArrayList<TabbedPaneElement> elements = new ArrayList<>();
        for(Component childComponent : component.getComponents()){
            int tabIndex = component.indexOfComponent(childComponent);
            elements.add(new TabbedPaneElement(childComponent, component, tabIndex));
        }
        return elements.toArray();
    }
}
