package org.zaproxy.zap.view.panelsearch;

import org.zaproxy.zap.view.panelsearch.items.ButtonSearch;
import org.zaproxy.zap.view.panelsearch.items.ComponentHighlighter;
import org.zaproxy.zap.view.panelsearch.items.LabelSearch;
import org.zaproxy.zap.view.panelsearch.items.TreeNodeElementSearch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Highlighter {

    private static final List<ComponentHighlighter> componentHighlighterItems = Arrays.asList(
            new ButtonSearch(),
            new TreeNodeElementSearch(),
            new LabelSearch()
    );

    public HighlighterResult highlight(ArrayList<FoundComponent> foundComponents){
        ArrayList<HighlightedComponent> highlightedComponents = new ArrayList<>();
        ArrayList<HighlightedComponent> highlightedParentComponents = new ArrayList<>();

        for (FoundComponent foundComponent : foundComponents){
            highlightedComponents.addAll(highlightComponents(Arrays.asList(foundComponent.getComponent())));
            highlightedParentComponents.addAll(highlightComponents(foundComponent.getParents()));
        }

        return new HighlighterResult(highlightedComponents, highlightedParentComponents);
    }

    private ArrayList<HighlightedComponent> highlightComponents(List<Object> components){
        ArrayList<HighlightedComponent> highlightedComponents = new ArrayList<>();
        for (Object component : components){
            HighlightedComponent highlightedComponent = highlightComponent(component);
            if(highlightedComponent != null){
                highlightedComponents.add(highlightedComponent);
            }
        }
        return highlightedComponents;
    }

    private HighlightedComponent highlightComponent(Object component){
        for (ComponentHighlighter componentHighlighter : componentHighlighterItems){
            HighlightedComponent highlightedComponent = componentHighlighter.highlight(component);
            if(highlightedComponent != null) {
                return highlightedComponent;
            }
        }
        return null;
    }

    public void undoHighlight(HighlighterResult highlighterResult){
        for (HighlightedComponent highlightedComponent : highlighterResult.getHighlightedComponents()){
            for (ComponentHighlighter componentHighlighter : componentHighlighterItems){
                componentHighlighter.undoHighlight(highlightedComponent);
            }
        }

        for (HighlightedComponent highlightedParentComponent : highlighterResult.getHighlightedParentComponents()){
            for (ComponentHighlighter componentHighlighter : componentHighlighterItems){
                componentHighlighter.undoHighlightAsParent(highlightedParentComponent);
            }
        }
    }
}


