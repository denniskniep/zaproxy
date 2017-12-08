package org.zaproxy.zap.view.panelsearch.items;

import org.zaproxy.zap.view.panelsearch.HighlightedComponent;

public interface ComponentHighlighter {
    HighlightedComponent highlight(Object component);
    HighlightedComponent highlightAsParent(Object component);

    void undoHighlight(HighlightedComponent highlightedComponent);
    void undoHighlightAsParent(HighlightedComponent highlightedParentComponent);
}
