package org.zaproxy.zap.view.panelsearch;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import java.awt.Color;

public final class HighlighterUtils {

    private static final String OPAQUE = "Opaque";
    private static final String BACKGROUND = "Background";
    private static final String BORDER = "Border";
    private static final String TITLE = "title";

    public static final Color DefaultHighlightColor = new Color(255, 204, 0);

    public static HighlightedComponent highlightBackground(JComponent component, Color color){
        return highlightBackground(new JComponentWithBackground(component), color);
    }

    public static HighlightedComponent highlightBackground(ComponentWithBackground componentWithBackground, Color color){
        HighlightedComponent highlightedComponent = new HighlightedComponent(componentWithBackground.getComponent());
        highlightedComponent.put(OPAQUE, componentWithBackground.isOpaque());
        highlightedComponent.put(BACKGROUND, componentWithBackground.getBackground());
        componentWithBackground.setOpaque(true);
        componentWithBackground.setBackground(color);
        return highlightedComponent;
    }

    public static void undoHighlightBackground(HighlightedComponent highlightedComponent, JComponent component){
        undoHighlightBackground(new JComponentWithBackground(component), highlightedComponent);
    }

    public static void undoHighlightBackground(ComponentWithBackground componentWithBackground, HighlightedComponent highlightedComponent){
        componentWithBackground.setOpaque(highlightedComponent.get(OPAQUE));
        componentWithBackground.setBackground(highlightedComponent.get(BACKGROUND));
    }

    public static HighlightedComponent highlightTitleBorderWithHtml(ComponentWithTitle componentWithTitle){
        return highlightTitleWithHtml(componentWithTitle, "<html><div style=' border: 1px solid; border-color: #FFCC00;'>%s</div></html>");
    }

    public static void undoHighlightTitleBorderWithHtml(ComponentWithTitle componentWithTitle, HighlightedComponent highlightedComponent){
        undoHighlightTitleWithHtml(componentWithTitle, highlightedComponent);
    }

    public static HighlightedComponent highlightTitleBackgroundWithHtml(ComponentWithTitle componentWithTitle){
        return highlightTitleWithHtml(componentWithTitle, "<html><span style='background-color:#FFCC00;'>%s</span></html>");
    }

    public static void undoHighlightTitleBackgroundWithHtml(ComponentWithTitle componentWithTitle, HighlightedComponent highlightedComponent){
        undoHighlightTitleWithHtml(componentWithTitle, highlightedComponent);
    }

    private static HighlightedComponent highlightTitleWithHtml(ComponentWithTitle componentWithTitle, String format){
        HighlightedComponent highlightedComponent = new HighlightedComponent(componentWithTitle.getComponent());
        String title = componentWithTitle.getTitle();
        if(!title.startsWith("<html>")){
            highlightedComponent.put(TITLE, title);
            String titleWithinHtml = String.format(format, title);
            componentWithTitle.setTitle(titleWithinHtml);
            return highlightedComponent;
        }
        return null;
    }

    private static void undoHighlightTitleWithHtml(ComponentWithTitle componentWithTitle, HighlightedComponent highlightedComponent){
        String title = highlightedComponent.get(TITLE);
        componentWithTitle.setTitle(title);
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
