package org.zaproxy.zap.view.panelsearch.items;

import javax.swing.JComboBox;

public class ComboBoxElement {
    private Object item;
    private int itemIndex;
    private JComboBox comboBox;

    public ComboBoxElement(Object item, int itemIndex, JComboBox comboBox) {
        this.item = item;
        this.itemIndex = itemIndex;
        this.comboBox = comboBox;
    }

    public Object getItem() {
        return item;
    }

    public int getItemIndex() {
        return itemIndex;
    }

    public JComboBox getComboBox() {
        return comboBox;
    }
}
