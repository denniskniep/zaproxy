package org.zaproxy.zap.view.panelsearch;

import org.zaproxy.zap.view.panelsearch.items.ButtonSearch;
import org.zaproxy.zap.view.panelsearch.items.ComponentHighlighter;
import org.zaproxy.zap.view.panelsearch.items.LabelSearch;
import org.zaproxy.zap.view.panelsearch.items.TreeNodeSearch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Highlighter {

    private static final List<ComponentHighlighter> componentHighlighterItems = Arrays.asList(
            new ButtonSearch(),
            new TreeNodeSearch(),
            new LabelSearch()
    );

    public HighlighterResult highlight(ArrayList<FoundComponent> foundComponents){
        ArrayList<HighlightedComponent> highlightedComponents = new ArrayList<>();
        ArrayList<HighlightedComponent> highlightedParentComponents = new ArrayList<>();

        for (FoundComponent foundComponent : foundComponents){
            for (ComponentHighlighter componentHighlighter : componentHighlighterItems){
                HighlightedComponent highlightedComponent = componentHighlighter.highlight(foundComponent.getComponent());
                if(highlightedComponent != null){
                    highlightedComponents.add(highlightedComponent);
                }

                for (Object parentComponent : foundComponent.getParents()){
                    HighlightedComponent highlightedParentComponent = componentHighlighter.highlightAsParent(parentComponent);
                    if(highlightedParentComponent != null){
                        highlightedParentComponents.add(highlightedParentComponent);
                    }
                }
            }
        }

        return new HighlighterResult(highlightedComponents, highlightedParentComponents);
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


