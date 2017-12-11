/*
 *  Zed Attack Proxy (ZAP) and its related class files.
 *
 *  ZAP is an HTTP/HTTPS proxy for assessing web application security.
 *
 *  Copyright 2014 The ZAP Development Team
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package org.zaproxy.zap.view;

import org.parosproxy.paros.view.AbstractParamContainerPanel;
import org.zaproxy.zap.view.panelsearch.FoundComponent;
import org.zaproxy.zap.view.panelsearch.Highlighter;
import org.zaproxy.zap.view.panelsearch.HighlighterResult;
import org.zaproxy.zap.view.panelsearch.InStringSearchQuery;
import org.zaproxy.zap.view.panelsearch.Search;
import org.zaproxy.zap.view.panelsearch.items.ComponentSearch;

import javax.swing.SwingUtilities;
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
        List<ComponentSearch> componentSearchItems = new ArrayList<>(Search.DefaultComponentSearchItems);
        // Fakes that a treeNodes children is a paramPanel
        componentSearchItems.add(0, new AbstractParamContainerPanelComponentSearch());
        return componentSearchItems;
    }

    public void searchAndHighlight(String text){
        clearHighlight();
        if(text != null && !text.isEmpty()){

            InStringSearchQuery query = new InStringSearchQuery(text);
            ArrayList<FoundComponent> findings = search.searchFor(new Object[] { panel }, query);

            currentHighlighterResult = highlighter.highlight(findings);
            SwingUtilities.invokeLater(() -> panel.repaint());
        }
    }

    public void clearHighlight(){
        if(currentHighlighterResult != null){
            highlighter.undoHighlight(currentHighlighterResult);
            SwingUtilities.invokeLater(() -> panel.repaint());
        }
    }
}
