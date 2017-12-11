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

import org.zaproxy.zap.view.panelsearch.HighlightedComponent;
import org.zaproxy.zap.view.panelsearch.HighlighterUtils;
import org.zaproxy.zap.view.panelsearch.SearchQuery;

import javax.swing.JLabel;

public class LabelSearch extends AbstractComponentSearch<JLabel> {

    @Override
    protected boolean isSearchMatchingInternal(JLabel component, SearchQuery query) {
        return query.match(component.getText());
    }

    @Override
    protected Object[] getComponentsInternal(JLabel component) {
        return new Object[]{};
    }

    @Override
    protected HighlightedComponent highlightInternal(JLabel component) {
        return HighlighterUtils.highlightBackground(component, HighlighterUtils.DefaultHighlightColor);
    }

    @Override
    protected void undoHighlightInternal(HighlightedComponent highlightedComponent, JLabel component) {
       HighlighterUtils.undoHighlightBackground(highlightedComponent, component);
    }
}