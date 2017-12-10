package org.zaproxy.zap.view.panelsearch;

import javax.swing.JComponent;
import java.awt.Color;

public class JComponentWithBackground extends ComponentWithBackground {

    private JComponent component;

    public JComponentWithBackground(JComponent component) {
        this.component = component;
    }

    @Override
    public Object getComponent() {
        return component;
    }

    @Override
    public void setBackground(Color color) {
        component.setBackground(color);
    }

    @Override
    public Color getBackground() {
        return component.getBackground();
    }

    @Override
    public void setOpaque(boolean isOpaque) {
        component.setOpaque(isOpaque);
    }

    @Override
    public boolean isOpaque() {
        return component.isOpaque();
    }
}
