package org.zaproxy.zap.view.panelsearch;

import org.zaproxy.zap.view.panelsearch.items.ButtonSearch;
import org.zaproxy.zap.view.panelsearch.items.ComboBoxElementSearch;
import org.zaproxy.zap.view.panelsearch.items.ComboBoxSearch;
import org.zaproxy.zap.view.panelsearch.items.ComponentSearch;
import org.zaproxy.zap.view.panelsearch.items.ContainerSearch;
import org.zaproxy.zap.view.panelsearch.items.JComponentSearch;
import org.zaproxy.zap.view.panelsearch.items.LabelSearch;
import org.zaproxy.zap.view.panelsearch.items.SpinnerSearch;
import org.zaproxy.zap.view.panelsearch.items.TabbedPaneElementSearch;
import org.zaproxy.zap.view.panelsearch.items.TabbedPaneSearch;
import org.zaproxy.zap.view.panelsearch.items.TableCellElementSearch;
import org.zaproxy.zap.view.panelsearch.items.TableSearch;
import org.zaproxy.zap.view.panelsearch.items.TextFieldSearch;
import org.zaproxy.zap.view.panelsearch.items.TitledBorderSearch;
import org.zaproxy.zap.view.panelsearch.items.TreeNodeElementSearch;
import org.zaproxy.zap.view.panelsearch.items.TreeSearch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Search {

    public static final List<ComponentSearch> DefaultComponentSearchItems = Arrays.asList(
            new TreeSearch(),
            new ButtonSearch(),
            new TreeNodeElementSearch(),
            new LabelSearch(),
            new SpinnerSearch(),
            new TextFieldSearch(),
            new ComboBoxSearch(),
            new ComboBoxElementSearch(),
            new TableSearch(),
            new TableCellElementSearch(),
            new TabbedPaneSearch(),
            new TabbedPaneElementSearch(),
            new TitledBorderSearch(),
            new JComponentSearch(),
            new ContainerSearch() //Must be the last item, because it fits all!
    );

    private final List<ComponentSearch> componentSearchItems;

    public Search(List<ComponentSearch> componentSearchItems) {
        this.componentSearchItems = componentSearchItems;
    }

    public ArrayList<FoundComponent> searchFor(Object component, SearchQuery query) {
        return searchFor(new Object[]{ component }, query);
    }

    public ArrayList<FoundComponent> searchFor(Object[] components, SearchQuery query){
        ArrayList<FoundComponent> foundComponents = new ArrayList<>();

        for (Object component : components){

            for (ComponentSearch componentSearchItem : componentSearchItems){

                if(componentSearchItem.isResponsible(component)){
                    if(componentSearchItem.isSearchMatching(component, query)){
                        foundComponents.add(new FoundComponent(component));
                    }

                    Object[] childComponents = componentSearchItem.getComponents(component);
                    ArrayList<FoundComponent> foundChildComponents = searchFor(childComponents, query);

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

