package org.zaproxy.zap.view.panelsearch;

import org.zaproxy.zap.view.panelsearch.items.AbstractParamContainerPanelSearch;
import org.zaproxy.zap.view.panelsearch.items.ButtonSearch;
import org.zaproxy.zap.view.panelsearch.items.ComponentSearch;
import org.zaproxy.zap.view.panelsearch.items.ContainerSearch;
import org.zaproxy.zap.view.panelsearch.items.LabelSearch;
import org.zaproxy.zap.view.panelsearch.items.TreeNodeElementSearch;
import org.zaproxy.zap.view.panelsearch.items.TreeSearch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Search {

    private static final List<ComponentSearch> componentSearchItems = Arrays.asList(
            new AbstractParamContainerPanelSearch(),
            new TreeSearch(),
            new ButtonSearch(),
            new TreeNodeElementSearch(),
            new LabelSearch(),
            new ContainerSearch() //Must be the last item, because it fits all!
    );

    public ArrayList<FoundComponent> SearchFor(Object component, String text){
        InStringSearchQuery query = new InStringSearchQuery(text);
        return SearchFor(new Object[] { component }, query);
    }

    public ArrayList<FoundComponent> SearchFor(Object[] components, SearchQuery query){
        ArrayList<FoundComponent> foundComponents = new ArrayList<>();

        for (Object component : components){

            for (ComponentSearch componentSearchItem : componentSearchItems){

                if(componentSearchItem.isResponsible(component)){
                    if(componentSearchItem.isSearchMatching(component, query)){
                        foundComponents.add(new FoundComponent(component, componentSearchItem));
                    }

                    Object[] childComponents = componentSearchItem.getComponents(component);
                    ArrayList<FoundComponent> foundChildComponents = SearchFor(childComponents, query);

                    for (FoundComponent foundChildComponent : foundChildComponents){
                        foundChildComponent.addParent(component);
                        foundComponents.add(foundChildComponent);
                    }

                    break;
                }
            }
        }
        return foundComponents;
    }
}

