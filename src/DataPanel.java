import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

public class DataPanel extends JPanel
{
    /**
     * 
     */
    private static final long serialVersionUID = 5777740971062336138L;

    private JTextArea resultDescription;

    public DataPanel()
    {
        // IMPLEMENTATION
        // final int COLUMN_FIELD_WIDTH = 40;
        resultDescription = new JTextArea(5, 30);

        Border blackline = BorderFactory.createEtchedBorder();
        this.setBorder(blackline);

        // Sub-panels with titles
        JPanel dataPanel = new JPanel();
        TitledBorder title = new TitledBorder(blackline, "Output");
        dataPanel.setBorder(title);
        dataPanel.setBackground(new Color(68, 125, 209));
        add(dataPanel); 

        // Add sub-panels to this panel
        // Layout manager
        resultDescription.setLayout(new GridBagLayout());

        GridBagConstraints layoutConst = new GridBagConstraints();
        layoutConst.gridheight = 0;
        layoutConst.insets = new Insets(100, 100, 100, 100);
        dataPanel.add(resultDescription, layoutConst);
        this.setBackground(new Color(68, 125, 209));
    }

    public synchronized void updateData(String result)
    {

        resultDescription.setText(result);
    }

}
