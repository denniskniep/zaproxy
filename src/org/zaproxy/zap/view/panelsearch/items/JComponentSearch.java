package org.zaproxy.zap.view.panelsearch.items;

import javax.swing.JComponent;
import javax.swing.border.TitledBorder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JComponentSearch extends AbstractComponentSearch<JComponent>{

    @Override
    protected Object[] getComponentsInternal(JComponent component) {
        List<Object> components = new ArrayList<>(Arrays.asList(component.getComponents()));
        if(component.getBorder() instanceof TitledBorder){
            components.add(component.getBorder());
        }
        return components.toArray();
    }
}
