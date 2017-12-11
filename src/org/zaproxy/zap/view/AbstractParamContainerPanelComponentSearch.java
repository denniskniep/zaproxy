/*
 *  Zed Attack Proxy (ZAP) and its related class files.
 *
 *  ZAP is an HTTP/HTTPS proxy for assessing web application security.
 *
 *  Copyright 2017 The ZAP Development Team
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
import org.parosproxy.paros.view.AbstractParamPanel;
import org.zaproxy.zap.view.panelsearch.items.AbstractComponentSearch;
import org.zaproxy.zap.view.panelsearch.items.TreeNodeElement;
import org.zaproxy.zap.view.panelsearch.items.TreeSearch;

import java.util.ArrayList;

public class AbstractParamContainerPanelComponentSearch extends AbstractComponentSearch<AbstractParamContainerPanel> {

    @Override
    protected Object[] getComponentsInternal(AbstractParamContainerPanel component) {

        // The tree contains the nodes and there is a valueChanged listener on the tree.
        // OnValueChanged the ParamPanel will be switched. So it behaves as a child component.
        // But in the ObjectModel they have no parent/child relation.
        // This class is for faking the parent/child relation between TreeNode and ParamPanel.
        // The name of the panel is the name of the Node

        TreeSearch search = new TreeSearch();
        ArrayList<TreeNodeElement> treeNodeElements = search.getTreeNodeElement(component.getTreeParam());
        for (TreeNodeElement treeNodeElement : treeNodeElements){
            AbstractParamPanel panel = component.getParamPanel(treeNodeElement.getNode().toString());
            treeNodeElement.addFakeObjectModelChildren(panel);
        }
        return treeNodeElements.toArray();
    }
}