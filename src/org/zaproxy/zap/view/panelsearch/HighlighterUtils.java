package org.zaproxy.zap.view.panelsearch;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import java.awt.Color;

public final class HighlighterUtils {

    private static final String OPAQUE = "Opaque";
    private static final String BACKGROUND = "Background";
    private static final String BORDER = "Border";

    public static final Color DefaultHighlightColor = new Color(255, 204, 0);

    public static HighlightedComponent highlightBackground(JComponent component, Color color){
        HighlightedComponent highlightedComponent = new HighlightedComponent(component);
        highlightedComponent.put(OPAQUE, component.isOpaque());
        highlightedComponent.put(BACKGROUND, component.getBackground());
        component.setOpaque(true);
        component.setBackground(color);
        return highlightedComponent;
    }

    public static void undoHighlightBackground(HighlightedComponent highlightedComponent, JComponent component){
        component.setOpaque(highlightedComponent.get(OPAQUE));
        component.setBackground(highlightedComponent.get(BACKGROUND));
    }

    public static HighlightedComponent highlightBorder(JComponent component, Color color){
        HighlightedComponent highlightedComponent = new HighlightedComponent(component);
        highlightedComponent.put(BORDER, component.getBorder());
        component.setBorder(BorderFactory.createLineBorder(color));
        return highlightedComponent;
    }

    public static void undoHighlightBorder(HighlightedComponent highlightedComponent, JComponent component){
        component.setBorder(highlightedComponent.get(BORDER));
    }
}
