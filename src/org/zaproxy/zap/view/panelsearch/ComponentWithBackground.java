package org.zaproxy.zap.view.panelsearch;

import java.awt.Color;

public abstract class ComponentWithBackground {
    public abstract Object getComponent();
    public abstract void setBackground(Color color);
    public abstract Color getBackground();
    public abstract void setOpaque(boolean isOpaque);
    public abstract boolean isOpaque();
}

