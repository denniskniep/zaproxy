package org.zaproxy.zap.view.panelsearch.items;

import org.zaproxy.zap.view.panelsearch.ComponentWithBackground;
import org.zaproxy.zap.view.panelsearch.HighlightedComponent;
import org.zaproxy.zap.view.panelsearch.HighlighterUtils;
import org.zaproxy.zap.view.panelsearch.JComponentWithBackground;
import org.zaproxy.zap.view.panelsearch.SearchQuery;

import javax.swing.JComponent;
import javax.swing.JSpinner;
import java.awt.Color;

public class SpinnerSearch extends AbstractComponentSearch<JSpinner>{

    private static final String HIGHLIGHTED_EDITOR= "highlightedEditorComponent";

    @Override
    protected boolean isSearchMatchingInternal(JSpinner component, SearchQuery query) {
        return query.match(component.getValue().toString());
    }

    @Override
    protected HighlightedComponent highlightInternal(JSpinner component) {
        HighlightedComponent highlightedUpAndDownComponent = HighlighterUtils.highlightBackground(new JComponentWithBackground(component), HighlighterUtils.DefaultHighlightColor);
        HighlightedComponent highlightedEditorComponent =  HighlighterUtils.highlightBackground(new SpinnerSearchComponentWithBackground(component), HighlighterUtils.DefaultHighlightColor);

        highlightedUpAndDownComponent.put(HIGHLIGHTED_EDITOR, highlightedEditorComponent);
        return highlightedUpAndDownComponent;
    }

    @Override
    protected void undoHighlightInternal(HighlightedComponent highlightedComponent, JSpinner component) {
        HighlightedComponent highlightedUpAndDownComponent = highlightedComponent;
        HighlightedComponent highlightedEditorComponent = highlightedUpAndDownComponent.get(HIGHLIGHTED_EDITOR);

        HighlighterUtils.undoHighlightBackground(new JComponentWithBackground(component), highlightedUpAndDownComponent);
        HighlighterUtils.undoHighlightBackground(new SpinnerSearchComponentWithBackground(component), highlightedEditorComponent);
    }

    private static class SpinnerSearchComponentWithBackground extends ComponentWithBackground {
        private JSpinner component;

        public SpinnerSearchComponentWithBackground(JSpinner component) {
            this.component = component;
        }

        @Override
        public Object getComponent() {
            return component;
        }

        @Override
        public void setBackground(Color color) {
            getEditor().setBackground(color);
        }

        private JComponent getEditor() {
            return component.getEditor();
        }

        @Override
        public Color getBackground() {
            return getEditor().getBackground();
        }

        @Override
        public void setOpaque(boolean isOpaque) {
            getEditor().setOpaque(isOpaque);
        }

        @Override
        public boolean isOpaque() {
            return getEditor().isOpaque();
        }
    }
}
