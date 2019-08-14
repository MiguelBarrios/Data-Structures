import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class MesonetFrame extends JFrame
{

    /**
     * Frame  
     */
    private static final long serialVersionUID = 1L;

    /** Menu bar */
    private FileMenuBar fileMenuBar;//

    private StatisticsPanel statistics;//
    private ParameterPanel parameters;//
    private DataPanel dataPanel;//
    private MesonetMainPanel banner;//
    private JPanel buttonPanel;//
    private JButton calcButton;//
    private JButton exitButton;//

    public MesonetFrame()
    {
        // IMPLEMENT
        super("Mesonet Calculator");
        fileMenuBar = new FileMenuBar();
        this.setJMenuBar(fileMenuBar);

        // Create the custom panels
        statistics = new StatisticsPanel();
        parameters = new ParameterPanel();
        dataPanel = new DataPanel();
        banner = new MesonetMainPanel();

        parameters.setBackground(new Color(167, 203, 209));
        statistics.setBackground(new Color(167, 203, 209));
        
        this.add(banner, BorderLayout.NORTH);
        this.add(parameters, BorderLayout.CENTER);
        this.add(statistics, BorderLayout.WEST);
        this.add(dataPanel, BorderLayout.EAST);
        buildButtonPanel();
        
        calcButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                // change the opSign, clear the error message and compute result.
                ArrayList<String> files = fileMenuBar.getFileList();
                String[] dataFiles = files.toArray(new String[files.size()]);

                ArrayList<String> paramIDs = parameters.getParamIds();

                try
                {
                    DaysStatistics stats = new DaysStatistics(dataFiles);
                    stats.findStatistics();

                    String answer = "";
                    for (int i = 0; i < paramIDs.size(); ++i)
                    {
                        if (statistics.getStatisticsType().equals("MIN"))
                        {
                            answer += stats.getMinimumDay(paramIDs.get(i));
                        }
                        if (statistics.getStatisticsType().equals("MAX"))
                        {
                            answer += stats.getMaximumDay(paramIDs.get(i));
                        }
                    }

                    dataPanel.updateData(answer);

                }
                catch (Exception ex)
                {
                    System.out.println(ex.getMessage());
                }
            }
        });
        exitButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {     
                System.exit(0);
            }
        });

        setSize(600, 240);
        setVisible(true);
        setResizable(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void buildButtonPanel()
    {
        //
        // IMPLEMENT
        // Create a panel for buttons.
        buttonPanel = new JPanel();
        calcButton = new JButton("Calculate");
        exitButton = new JButton("Exit");

        // add(buttonPanel);
        buttonPanel.add(calcButton);
        buttonPanel.add(exitButton);

        // Register the action listener

        // adds calculate and exit button
        this.add(buttonPanel, BorderLayout.SOUTH);
        buttonPanel.setBackground(new Color(127, 176, 209));

    }

    private class ExitButtonListner implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e)
        {
            System.exit(0);
        }
    }

    private class CalcButtonListner implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e)
        {
            // IMPLEMENT

            // help with debugging
            // JOptionPane.showMessageDialog(null,
            // "Should dipslay what is calculated for " + type + " "
            // + paramId + " result: " + result);
            JOptionPane.showMessageDialog(null, "Should dipslay what is calculated for");

        }

    }

    ///////////////////////////////////////////////////////////////////
    /**
     * 
     * @author CS2334, modified by ???
     * @version 2018-x-x
     * 
     *          Menu bar that provides file loading and program exit capabilities.
     *
     */
    public class FileMenuBar extends JMenuBar
    {
        // Menu on the menu bar
        private JMenu menu;

        // Two options for the menu
        private JMenuItem menuOpen;
        private JMenuItem menuExit;

        // Reference to a file chooser pop-up
        private JFileChooser fileChooser;

        private ArrayList<String> listOfFiles;

        /**
         * Constructor: fully assemble the menu bar and attach the necessary action
         * listeners.
         */
        public FileMenuBar()
        {
            listOfFiles = new ArrayList<>();
            // Create the menu and add it to the menu bar
            menu = new JMenu("File");
            add(menu);

            // Create the menu items and add them to the menu
            menuOpen = new JMenuItem("Open Data File");
            menuOpen.setName("Menu Open");
            menuExit = new JMenuItem("Exit");
            menu.add(menuOpen);
            menu.add(menuExit);

            // Action listener for exit
            menuExit.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent e)
                {
                    System.exit(0);
                }
            });

            // Create the file chooser
            fileChooser = new JFileChooser(new File("./data/mesonet"));
            fileChooser.setMultiSelectionEnabled(true);

            // Action listener for file open
            menuOpen.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent e)
                {
                    // Ask for files
                    int returnVal = fileChooser.showOpenDialog(menuOpen);
                    // Did we get one?
                    if (returnVal == JFileChooser.APPROVE_OPTION)
                    {
                        // Yes
                        File[] files = fileChooser.getSelectedFiles();
                        // System.out.println(files.length);
                        try
                        {
                            for (File file : files)
                            {
                                String fileName = file.toString();
                                System.out.println(fileName);
                                listOfFiles.add(fileName);
                            }
                        }
                        catch (Exception e2)
                        {
                            // Catch all other exceptions
                            JOptionPane.showMessageDialog(fileChooser, "File load error");
                            MesonetFrame.this.setCursor(null);
                        }
                    }
                    else
                    {
                        System.out.println("No files.");
                    }
                }
            });

        }

        public ArrayList<String> getFileList()
        {
            return (ArrayList<String>) listOfFiles.clone();
        }
    }

}
