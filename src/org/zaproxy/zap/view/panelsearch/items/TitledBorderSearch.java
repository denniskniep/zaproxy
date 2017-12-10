package org.zaproxy.zap.view.panelsearch.items;

import org.zaproxy.zap.view.panelsearch.ComponentWithTitle;
import org.zaproxy.zap.view.panelsearch.HighlightedComponent;
import org.zaproxy.zap.view.panelsearch.HighlighterUtils;
import org.zaproxy.zap.view.panelsearch.SearchQuery;

import javax.swing.border.TitledBorder;

public class TitledBorderSearch extends AbstractComponentSearch<TitledBorder>{

    @Override
    protected boolean isSearchMatchingInternal(TitledBorder component, SearchQuery query) {
        return query.match(component.getTitle());
    }

    @Override
    protected HighlightedComponent highlightInternal(TitledBorder component) {
        return HighlighterUtils.highlightTitleBackgroundWithHtml(new TitledBorderComponentWithTitle(component));
    }

    @Override
    protected void undoHighlightInternal(HighlightedComponent highlightedComponent, TitledBorder component) {
        HighlighterUtils.undoHighlightTitleBackgroundWithHtml(new TitledBorderComponentWithTitle(component), highlightedComponent);
    }

    private static class TitledBorderComponentWithTitle extends ComponentWithTitle {

        private TitledBorder component;

        public TitledBorderComponentWithTitle(TitledBorder component) {
            this.component = component;
        }

        @Override
        public Object getComponent() {
            return component;
        }

        @Override
        public void setTitle(String title) {
            component.setTitle(title);
        }

        @Override
        public String getTitle() {
            return component.getTitle();
        }
    }
}


