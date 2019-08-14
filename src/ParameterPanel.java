import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

public class ParameterPanel extends JPanel
{
    /**
     * 
     */
    private static final long serialVersionUID = 4236142307230121244L;
    public final String TAIR = "TAIR";
    public final String TA9M = "TA9M";
    public final String SRAD = "SRAD";
    public final String WSPD = "WSPD";

    // Check boxes for the available parameters
    private JCheckBox airTemp;
    private JCheckBox ta9m;
    private JCheckBox srad;
    private JCheckBox wspd;

    public ParameterPanel()
    {
        System.out.println("Building Parameter panel");

        // Create a GridLayout Manager
        // IMPLEMENT
        airTemp = new JCheckBox(TAIR);
        ta9m = new JCheckBox(TA9M);
        srad = new JCheckBox(SRAD);
        wspd = new JCheckBox(WSPD);

        Border blackline = BorderFactory.createEtchedBorder();

        // Sub-panels with titles
        JPanel shapePanel = new JPanel();
        TitledBorder title = new TitledBorder(blackline, "Param");
        shapePanel.setBorder(title);

        // Add sub-panels to this panel
        // Layout manager
        shapePanel.setLayout(new GridLayout(4, 1));
        shapePanel.add(airTemp);
        shapePanel.add(ta9m);
        shapePanel.add(srad);
        shapePanel.add(wspd);
        this.add(shapePanel);

        shapePanel.setBackground(new Color(167, 203, 209));
        shapePanel.setName("Param");
    }

    public ArrayList<String> getParamIds()
    {
        // create ArrayList<String> to hold selected parameters
        ArrayList<String> selected = new ArrayList<String>();

        if (airTemp.isSelected())
        {
            selected.add(TAIR);
        }
        if (ta9m.isSelected())
        {
            selected.add(TA9M);
        }
        if (srad.isSelected())
        {
            selected.add(SRAD);
        }
        if (wspd.isSelected())
        {
            selected.add(WSPD);
        }
        return selected;
    }

}
