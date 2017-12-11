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

package org.zaproxy.zap.view.panelsearch.items;

import javax.swing.JTabbedPane;
import java.awt.Component;
import java.util.ArrayList;

public class TabbedPaneSearch extends AbstractComponentSearch<JTabbedPane>  {

    @Override
    protected Object[] getComponentsInternal(JTabbedPane component) {
        ArrayList<TabbedPaneElement> elements = new ArrayList<>();
        for(Component childComponent : component.getComponents()){
            int tabIndex = component.indexOfComponent(childComponent);
            elements.add(new TabbedPaneElement(childComponent, component, tabIndex));
        }
        return elements.toArray();
    }
}
