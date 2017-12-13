/*
 * Zed Attack Proxy (ZAP) and its related class files.
 *
 * ZAP is an HTTP/HTTPS proxy for assessing web application security.
 *
 * Copyright 2017 The ZAP Development Team
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.zaproxy.zap.extension.callback.ui;

import org.parosproxy.paros.Constant;
import org.parosproxy.paros.control.Control;
import org.parosproxy.paros.extension.AbstractPanel;
import org.zaproxy.zap.extension.callback.ExtensionCallback;
import org.zaproxy.zap.utils.DisplayUtils;
import org.zaproxy.zap.utils.FontUtils;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;
import java.awt.CardLayout;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CallbackPanel extends AbstractPanel {

    private static final long serialVersionUID = 1L;

    private JToolBar mainToolBar;
    private JPanel mainPanel = null;
    private JScrollPane scrollPane = null;
    private CallbackTable resultsTable = null;
    private CallbackTableModel model = null;
    private ExtensionCallback extensionCallback;

    public CallbackPanel(ExtensionCallback extensionCallback) {
        super();
        this.extensionCallback = extensionCallback;
        initialize();
    }

    private void initialize() {
        this.setLayout(new CardLayout());
        this.setName(Constant.messages.getString("callback.panel.name"));
        this.setIcon(new ImageIcon(CallbackPanel.class.getResource("/resource/icon/16/callbacks.png")));
        this.add(getMainPanel(), getMainPanel().getName());
    }

    private javax.swing.JPanel getMainPanel() {
        if (mainPanel == null) {
            mainPanel = new JPanel(new java.awt.GridBagLayout());
            mainPanel.setName("CallbackPanel");
            mainPanel.add(getToolBar(), newToolbarGBC());
            mainPanel.add(getJScrollPane(), newScrollPaneGBC());
        }

        return mainPanel;
    }

    private JToolBar getToolBar() {
        if (mainToolBar == null) {
            mainToolBar = new JToolBar();
            mainToolBar.setLayout(new java.awt.GridBagLayout());
            mainToolBar.setEnabled(true);
            mainToolBar.setFloatable(false);
            mainToolBar.setRollover(true);
            mainToolBar.setPreferredSize(new java.awt.Dimension(800,30));
            mainToolBar.setName("Callback Toolbar");

            mainToolBar.add(getClearButton(), newGBC(0));
            mainToolBar.add(Box.createHorizontalGlue(), newPlaceholderGBC(1));
            mainToolBar.add(getOptionsButton(), newOptionsGBC(2));
        }
        return mainToolBar;
    }

    private GridBagConstraints newGBC (int gridx) {
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = gridx;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(0,0,0,0);
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        return gridBagConstraints;
    }

    private GridBagConstraints newPlaceholderGBC (int gridx) {
        GridBagConstraints gridBagConstraints = newGBC(gridx);
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        return gridBagConstraints;
    }

    private GridBagConstraints newOptionsGBC (int gridx) {
        GridBagConstraints gridBagConstraints = newGBC(gridx);
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        return gridBagConstraints;
    }

    private GridBagConstraints newScrollPaneGBC() {
        GridBagConstraints scrollPaneConstraint = new GridBagConstraints();
        scrollPaneConstraint.gridx = 0;
        scrollPaneConstraint.gridy = 1;
        scrollPaneConstraint.weightx = 1.0;
        scrollPaneConstraint.weighty = 1.0;
        scrollPaneConstraint.insets = new java.awt.Insets(0,0,0,0);
        scrollPaneConstraint.fill = java.awt.GridBagConstraints.BOTH;
        scrollPaneConstraint.anchor = java.awt.GridBagConstraints.NORTHWEST;
        return scrollPaneConstraint;
    }

    private GridBagConstraints newToolbarGBC(){
        GridBagConstraints toolbarConstraint = new GridBagConstraints();
        toolbarConstraint.gridx = 0;
        toolbarConstraint.gridy = 0;
        toolbarConstraint.weightx = 1.0D;
        toolbarConstraint.insets = new java.awt.Insets(2,2,2,2);
        toolbarConstraint.fill = java.awt.GridBagConstraints.HORIZONTAL;
        toolbarConstraint.anchor = java.awt.GridBagConstraints.NORTHWEST;
        return toolbarConstraint;
    }

    private JButton getClearButton() {
        JButton clearButton = new JButton(Constant.messages.getString("callback.panel.clear.button.label"));
        clearButton.setToolTipText(Constant.messages.getString("callback.panel.clear.button.toolTip"));
        clearButton.setIcon(DisplayUtils.getScaledIcon(new ImageIcon(CallbackPanel.class.getResource("/resource/icon/fugue/broom.png"))));
        clearButton.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                deleteAllCallbacks();
            }
        });
        return clearButton;
    }

    private void deleteAllCallbacks() {
        extensionCallback.deleteCallbacks();
    }

    private JButton getOptionsButton() {
        JButton optionsButton =  new JButton();
        optionsButton.setToolTipText(Constant.messages.getString("callback.panel.options.button.label"));
        optionsButton.setIcon(DisplayUtils.getScaledIcon(new ImageIcon(CallbackPanel.class.getResource("/resource/icon/16/041.png"))));
        optionsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Control.getSingleton().getMenuToolsControl()
                        .options(Constant.messages.getString("callback.options.title"));
            }
        });
        return optionsButton;
    }

    private JScrollPane getJScrollPane() {
        if (scrollPane == null) {
            scrollPane = new JScrollPane();
            scrollPane.setFont(FontUtils.getFont("Dialog"));
            scrollPane.setHorizontalScrollBarPolicy(javax.swing.JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
            scrollPane.setViewportView(getResultsTable());
        }

        return scrollPane;
    }

    private CallbackTable getResultsTable () {
        if (this.resultsTable == null) {
            this.model = new CallbackTableModel();
            this.resultsTable = new CallbackTable(model);
        }
        return this.resultsTable;
    }

    protected CallbackTableModel getModel() {
        return model;
    }

    public void addCallbackRequest(CallbackRequest callbackRequest){
        model.addEntry(callbackRequest);
    }

    public void clearCallbackRequests() {
        model.clear();
    }
}