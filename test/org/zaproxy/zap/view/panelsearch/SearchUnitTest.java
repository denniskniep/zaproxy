package org.zaproxy.zap.view.panelsearch;

import org.junit.Test;

import javax.swing.JButton;
import javax.swing.JPanel;
import java.util.ArrayList;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class SearchUnitTest {

    @Test
    public void shouldFindButtonByText() {
        String btnText = "myCusTomText";
        String panelName = "xyu";
        JPanel panel = new JPanel();
        panel.setName(panelName);
        panel.add(new JButton(btnText));

        Search search = new Search(Search.DefaultComponentSearchItems);
        SearchQuery query = new InStringSearchQuery("stom");
        ArrayList<FoundComponent> findings = search.searchFor(panel, query);

        FoundComponent foundComponent = findings.get(0);
        JPanel foundPanel = foundComponent.getParentAtCasted(0);
        assertThat(foundPanel.getName(), is(panelName));

        JButton foundButton = foundComponent.getComponentCasted();
        assertThat(foundButton.getText(), is(btnText));
    }
}
