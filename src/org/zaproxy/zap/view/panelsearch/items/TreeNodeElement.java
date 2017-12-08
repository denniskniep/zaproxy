package org.zaproxy.zap.view.panelsearch.items;

import javax.swing.JTree;
import java.util.ArrayList;

public class TreeNodeElement {
    private Object node;
    private JTree tree;
    private ArrayList<Object> fakeObjectModelChildren = new ArrayList<>();

    public TreeNodeElement(Object node, JTree tree) {
        this.node = node;
        this.tree = tree;
    }

    public Object getNode() {
        return node;
    }

    public JTree getTree() {
        return tree;
    }

    public void addFakeObjectModelChildren(Object child){
        fakeObjectModelChildren.add(child);
    }

    public ArrayList<Object> getFakeObjectModelChildren() {
        return fakeObjectModelChildren;
    }
}
