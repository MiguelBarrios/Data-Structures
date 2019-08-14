import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

public class StatisticsPanel extends JPanel
{
    /**
     * 
     */
    private static final long serialVersionUID = -5778130703074619169L;
    public final static String MAX_BUTTON = "MAXIMUM";
    public final static String MIN_BUTTON = "MINIMUM";
    private JRadioButton maxButton;
    private JRadioButton minButton;

    private ButtonGroup bg;

    public StatisticsPanel()
    {
        // IMPLEMENTATION
        maxButton = new JRadioButton(MAX_BUTTON);
        minButton = new JRadioButton(MIN_BUTTON);

        bg = new ButtonGroup();
        bg.add(maxButton);
        bg.add(minButton);

        Border blackline = BorderFactory.createEtchedBorder();

        // this.setBorder(blackline);

        // Sub-panels with titles
        JPanel statisticsPanel = new JPanel();
        TitledBorder title = new TitledBorder(blackline, "Statistics");
        statisticsPanel.setBorder(title);

        // Add sub-panels to this panel
        // Layout manager
        statisticsPanel.setLayout(new GridLayout(2, 1, 0, 45));

        statisticsPanel.add(maxButton);
        statisticsPanel.add(minButton);
        this.add(statisticsPanel);

        statisticsPanel.setBackground(new Color(167, 203, 209));
        statisticsPanel.setName("Statistics");

    }

    public String getStatisticsType()
    {
        // IMPLEMENTATION
        if (maxButton.isSelected())
        {
            return "MAX";
        }
        if (minButton.isSelected())
        {
            return "MIN";
        }
        return null;
    }
}