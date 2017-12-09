package org.zaproxy.zap.view.panelsearch.items;

import org.zaproxy.zap.view.panelsearch.HighlightedComponent;
import org.zaproxy.zap.view.panelsearch.SearchQuery;

public class TabbedPaneElementSearch extends AbstractComponentSearch<TabbedPaneElement> {

    private static final String TITLE = "title";

    @Override
    protected boolean isSearchMatchingInternal(TabbedPaneElement component, SearchQuery query) {
        String tabTitle = component.getTabbedPane().getTitleAt(component.getTabIndex());
        return query.match(tabTitle);
    }

    @Override
    protected Object[] getComponentsInternal(TabbedPaneElement component) {
        return new Object[]{ component.getComponent() };
    }

    @Override
    protected HighlightedComponent highlightInternal(TabbedPaneElement component) {
        HighlightedComponent highlightedComponent = new HighlightedComponent(component);
        int tabIndex = component.getTabIndex();
        String title = component.getTabbedPane().getTitleAt(tabIndex);
        highlightedComponent.put(TITLE, title);
        component.getTabbedPane().setTitleAt(tabIndex, "<html><span style='background-color:#FFCC00;'>"+title+"</span></html>");

        //ToDo: Handle specific TabHeaderControls
        //component.getTabbedPane().getTabComponentAt(tabIndex)

        return highlightedComponent;
    }

    @Override
    protected void undoHighlightInternal(HighlightedComponent highlightedComponent, TabbedPaneElement component) {
        int tabIndex = component.getTabIndex();
        String title = highlightedComponent.get(TITLE);
        component.getTabbedPane().setTitleAt(tabIndex, title);
    }

    @Override
    protected HighlightedComponent highlightAsParentInternal(TabbedPaneElement component) {
        HighlightedComponent highlightedComponent = new HighlightedComponent(component);
        int tabIndex = component.getTabIndex();
        String title = component.getTabbedPane().getTitleAt(tabIndex);
        if(!title.startsWith("<html>")){
            highlightedComponent.put(TITLE, title);
            component.getTabbedPane().setTitleAt(tabIndex, "<html><div style=' border: 1px solid; border-color: #FFCC00;'>"+title+"</div></html>");
        }

        return highlightedComponent;
    }

    @Override
    protected void undoHighlightAsParentInternal(HighlightedComponent highlightedComponent, TabbedPaneElement component) {
        int tabIndex = component.getTabIndex();
        String title = highlightedComponent.get(TITLE);
        if(title != null){
            component.getTabbedPane().setTitleAt(tabIndex, title);
        }
    }
}
