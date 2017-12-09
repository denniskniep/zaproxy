package org.zaproxy.zap.view.panelsearch;

import org.zaproxy.zap.view.panelsearch.items.ButtonSearch;
import org.zaproxy.zap.view.panelsearch.items.ComponentHighlighter;
import org.zaproxy.zap.view.panelsearch.items.LabelSearch;
import org.zaproxy.zap.view.panelsearch.items.TreeNodeElementSearch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;

public class Highlighter {

    public static final List<ComponentHighlighter> DefaultComponentHighlighterItems = Arrays.asList(
            new ButtonSearch(),
            new TreeNodeElementSearch(),
            new LabelSearch()
    );

    private final List<ComponentHighlighter> componentHighlighterItems;

    public Highlighter(List<ComponentHighlighter> componentHighlighterItems) {
        this.componentHighlighterItems = componentHighlighterItems;
    }

    public HighlighterResult highlight(ArrayList<FoundComponent> foundComponents){
        ArrayList<HighlightedComponent> highlightedComponents = new ArrayList<>();
        ArrayList<HighlightedComponent> highlightedParentComponents = new ArrayList<>();

        for (FoundComponent foundComponent : foundComponents){
            highlightedComponents.addAll(highlightComponents(Arrays.asList(foundComponent.getComponent()), (h, c) -> h.highlight(c)));
            highlightedParentComponents.addAll(highlightComponents(foundComponent.getParents(), (h, c) -> h.highlightAsParent(c)));
        }

        return new HighlighterResult(highlightedComponents, highlightedParentComponents);
    }

    private ArrayList<HighlightedComponent> highlightComponents(List<Object> components,  BiFunction<ComponentHighlighter, Object, HighlightedComponent> highlightAction){
        ArrayList<HighlightedComponent> highlightedComponents = new ArrayList<>();
        for (Object component : components){
            HighlightedComponent highlightedComponent = highlightComponent(component, highlightAction);
            if(highlightedComponent != null){
                highlightedComponents.add(highlightedComponent);
            }
        }
        return highlightedComponents;
    }

    private HighlightedComponent highlightComponent(Object component, BiFunction<ComponentHighlighter, Object, HighlightedComponent> highlightAction){
        for (ComponentHighlighter componentHighlighter : componentHighlighterItems){
            HighlightedComponent highlightedComponent = highlightAction.apply(componentHighlighter, component);
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


