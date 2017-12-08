package org.zaproxy.zap.view.panelsearch;

import java.util.ArrayList;

public class HighlighterResult{

    private final ArrayList<HighlightedComponent> highlightedComponents;
    private final ArrayList<HighlightedComponent> highlightedParentComponents;

    public HighlighterResult(ArrayList<HighlightedComponent> highlightedComponents, ArrayList<HighlightedComponent> highlightedParentComponents) {
        this.highlightedComponents = highlightedComponents;
        this.highlightedParentComponents = highlightedParentComponents;
    }

    public ArrayList<HighlightedComponent> getHighlightedComponents() {
        return highlightedComponents;
    }

    public ArrayList<HighlightedComponent> getHighlightedParentComponents() {
        return highlightedParentComponents;
    }
}
