package org.zaproxy.zap.view;

import org.parosproxy.paros.view.AbstractParamContainerPanel;
import org.zaproxy.zap.view.panelsearch.FoundComponent;
import org.zaproxy.zap.view.panelsearch.Highlighter;
import org.zaproxy.zap.view.panelsearch.HighlighterResult;
import org.zaproxy.zap.view.panelsearch.InStringSearchQuery;
import org.zaproxy.zap.view.panelsearch.Search;
import org.zaproxy.zap.view.panelsearch.items.ComponentSearch;

import java.util.ArrayList;
import java.util.List;

public class AbstractParamContainerPanelSearchAndHighlight {

    private Search search;
    private Highlighter highlighter;
    private HighlighterResult currentHighlighterResult;
    private AbstractParamContainerPanel panel;

    public AbstractParamContainerPanelSearchAndHighlight(AbstractParamContainerPanel panel) {
        this.panel = panel;
        this.search = new Search(getComponentSearchItems());
        this.highlighter = new Highlighter(Highlighter.DefaultComponentHighlighterItems);
    }

    private List<ComponentSearch> getComponentSearchItems() {
        List<ComponentSearch> componentSearchItems = Search.DefaultComponentSearchItems;
        // Fakes that a treeNodes children is a paramPanel
        componentSearchItems.set(0, new AbstractParamContainerPanelComponentSearch());
        return componentSearchItems;
    }

    public void searchAndHighlight(String text){
        clearHighlight();
        if(text != null && !text.isEmpty()){

            InStringSearchQuery query = new InStringSearchQuery(text);
            ArrayList<FoundComponent> findings = search.searchFor(new Object[] { panel }, query);

            currentHighlighterResult = highlighter.highlight(findings);
        }
    }

    public void clearHighlight(){
        if(currentHighlighterResult != null){
            highlighter.undoHighlight(currentHighlighterResult);
        }
    }
}
