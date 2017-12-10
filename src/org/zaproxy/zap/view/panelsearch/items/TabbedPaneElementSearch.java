package org.zaproxy.zap.view.panelsearch.items;

import org.zaproxy.zap.view.panelsearch.ComponentWithTitle;
import org.zaproxy.zap.view.panelsearch.HighlightedComponent;
import org.zaproxy.zap.view.panelsearch.HighlighterUtils;
import org.zaproxy.zap.view.panelsearch.SearchQuery;

import java.awt.Component;

public class TabbedPaneElementSearch extends AbstractComponentSearch<TabbedPaneElement> {

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
        int tabIndex = component.getTabIndex();
        Component tabHeaderComponent = component.getTabbedPane().getTabComponentAt(tabIndex);
        if(tabHeaderComponent == null){
            return HighlighterUtils.highlightTitleBackgroundWithHtml(new TabbedPaneElementComponentWithTitle(component));
        }else{
            //ToDo: Handle specific TabHeaderControls
            return null;
        }
    }

    @Override
    protected void undoHighlightInternal(HighlightedComponent highlightedComponent, TabbedPaneElement component) {
        HighlighterUtils.undoHighlightTitleBackgroundWithHtml(new TabbedPaneElementComponentWithTitle(component), highlightedComponent);
    }

    @Override
    protected HighlightedComponent highlightAsParentInternal(TabbedPaneElement component) {
        int tabIndex = component.getTabIndex();
        Component tabHeaderComponent = component.getTabbedPane().getTabComponentAt(tabIndex);
        if(tabHeaderComponent == null){
            return HighlighterUtils.highlightTitleBorderWithHtml(new TabbedPaneElementComponentWithTitle(component));
        }else{
            //ToDo: Handle specific TabHeaderControls
            return null;
        }
    }

    @Override
    protected void undoHighlightAsParentInternal(HighlightedComponent highlightedComponent, TabbedPaneElement component) {
        HighlighterUtils.undoHighlightTitleBorderWithHtml(new TabbedPaneElementComponentWithTitle(component), highlightedComponent);
    }

    private static class TabbedPaneElementComponentWithTitle extends ComponentWithTitle {

        private TabbedPaneElement component;

        private TabbedPaneElementComponentWithTitle(TabbedPaneElement component) {
            this.component = component;
        }

        @Override
        public Object getComponent() {
            return component;
        }

        @Override
        public void setTitle(String title) {
            int tabIndex = component.getTabIndex();
            component.getTabbedPane().setTitleAt(tabIndex, title);
        }

        @Override
        public String getTitle() {
            int tabIndex = component.getTabIndex();
            return component.getTabbedPane().getTitleAt(tabIndex);
        }
    }
}
