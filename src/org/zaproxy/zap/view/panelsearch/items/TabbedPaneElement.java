package org.zaproxy.zap.view.panelsearch.items;

import javax.swing.JTabbedPane;
import java.awt.Component;

public class TabbedPaneElement {

    private Component component;
    private JTabbedPane tabbedPane;
    private int tabIndex;

    public TabbedPaneElement(Component component, JTabbedPane tabbedPane, int tabIndex) {
        this.component = component;
        this.tabbedPane = tabbedPane;
        this.tabIndex = tabIndex;
    }

    public Component getComponent() {
        return component;
    }

    public JTabbedPane getTabbedPane() {
        return tabbedPane;
    }

    public int getTabIndex() {
        return tabIndex;
    }
}
