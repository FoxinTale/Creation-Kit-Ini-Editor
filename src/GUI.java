import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class GUI {
    static ArrayList<Integer> positions = new ArrayList<>();
    static int x = Main.screenSize[0] / 2;
    static int y = Main.screenSize[1] / 2;

    public static void checkListGUI(ArrayList<String> checkboxItems){
        int boxStart = 30;
        ArrayList<String> selected = new ArrayList<>();

        JFrame window = new JFrame();
        JFrame.setDefaultLookAndFeelDecorated(true);
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel();
        JScrollPane mainPane = new JScrollPane(mainPanel);
        ArrayList<JCheckBox> checkboxes = new ArrayList<>();
        JButton addBSAButton = new JButton("Add BSAs");
        JButton resetButton = new JButton("Reset Checkboxes");


        window.setSize(x, y);

        for (String checkboxItem : checkboxItems) {
            checkboxes.add(new JCheckBox(checkboxItem));
        }

        for (JCheckBox checkbox : checkboxes) {
            checkbox.setBounds(25, boxStart, 480, 25);
            boxStart += 30;
        }

        for (JCheckBox checkbox : checkboxes) {
            mainPanel.add(checkbox);
        }

        ActionListener mainButtonEvent = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                for (JCheckBox checkbox : checkboxes) {
                    if (checkbox.isSelected()) {
                        selected.add(checkbox.getText());
                    }
                }
                IniOps.addBSAsToArchive(selected);
            }
        };

        ActionListener resetButtonAction = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                for(int i = 0; i < checkboxes.size(); i++) {
                    if (checkboxes.get(i).isSelected()) {
                        checkboxes.get(i).setSelected(false);
                    }
                }
            }
        };

        addBSAButton.addActionListener(mainButtonEvent);
        resetButton.addActionListener(resetButtonAction);
        mainPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        mainPanel.setPreferredSize(new Dimension(x/2, boxStart + 30));
        mainPane.setBounds(15, 15, 640, 480);
        mainPanel.setLayout(null);

        addBSAButton.setBounds(675, 20, 200, 30);
        resetButton.setBounds(675, 60, 200, 30);

        window.add(mainPane);
        window.add(addBSAButton);
        window.add(resetButton);
        window.setResizable(false);
        window.setLayout(null);
        window.setVisible(true);
    }

     public static void MainGUI(ArrayList<String> checkboxItems){
        JFrame.setDefaultLookAndFeelDecorated(true);
        JFrame window = new JFrame();
        Box checkboxBox = Box.createVerticalBox();
        Box buttonBox = Box.createVerticalBox();
        JScrollPane scrollPanel = new JScrollPane(checkboxBox);
        JButton addBSAButton = new JButton("Add BSAs"); // Strings.mainButtonText

    //    int x = Main.screenSize[0] / 3;
     //   int y = Main.screenSize[1] / 2;

        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setTitle(Strings.windowTitle); // Strings.windowTitle
        window.setSize(x, Main.screenSize[1]/2);

        window.getContentPane().add(scrollPanel);
        checkboxBox.setBorder((BorderFactory.createLineBorder(Color.black)));

          for (String checkboxItem : checkboxItems) {
             JCheckBox checkbox = new JCheckBox(checkboxItem);
             checkboxBox.add(checkbox);
          }

        buttonBox.add(addBSAButton);
        ActionListener mainButtonEvent = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                processSelected(checkboxBox);
            }
        };

        scrollPanel.setBounds( 5, 5, (int) (x /1.75), y - 45);
        addBSAButton.setSize(new Dimension(100, 50));
        buttonBox.setBounds(x - x/4, 5, x/5, y /2);

        addBSAButton.addActionListener(mainButtonEvent);
        window.add(buttonBox);

        window.setResizable(false);
        window.setLayout(null);
        window.setVisible(true);
    }

    public static void iniGUI(){
        JFrame.setDefaultLookAndFeelDecorated(true);
        JFrame window = new JFrame("Creation Kit Ini Editor"); // Strings.windowTitle
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        JTabbedPane tabs = new JTabbedPane(JTabbedPane.LEFT);
        int x = Main.screenSize[0] / 2;
        int y = Main.screenSize[1] / 2;

        window.setSize(x, y);
        ArrayList<JLabel> generalLabels = new ArrayList<>();
        ArrayList<JTextField> generalTexts = new ArrayList<>();

        ArrayList<JLabel> displayLabels = new ArrayList<>();
        ArrayList<JTextField> displayTexts = new ArrayList<>();

        ArrayList<JLabel> prevMovementLabels = new ArrayList<>();
        ArrayList<JTextField> prevMovementTexts = new ArrayList<>();

        processList(IniOps.general, generalLabels, generalTexts);
        processList(IniOps.display, displayLabels, displayTexts);
        processList(IniOps.previewMovement, prevMovementLabels, prevMovementTexts);

        setComponentBounds(25, 325, 25, 23, generalLabels,generalTexts);
        setComponentBounds(25, 325, 25, 23, displayLabels, displayTexts);
        setComponentBounds(25, 325, 25, 23, prevMovementLabels, prevMovementTexts);

        JPanel generalTab = new JPanel();
        JPanel displayTab = new JPanel();
        JPanel prevMovementTab = new JPanel();

        generalTab.setPreferredSize(new Dimension(x/2, positions.get(0) + 20));
        displayTab.setPreferredSize(new Dimension(x /2, positions.get(1) + 20));
        prevMovementTab.setPreferredSize(new Dimension(x /2, positions.get(2) + 20));

        JScrollPane generalPane = new JScrollPane(generalTab);
        JScrollPane displayPane = new JScrollPane(displayTab);
        JScrollPane prevMovementPane = new JScrollPane(prevMovementTab);
        JPanel mainTab = new JPanel();

        generalTab.setLayout(null);
        displayTab.setLayout(null);
        prevMovementTab.setLayout(null);

        generalPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        displayPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        prevMovementPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        addComponentsToPanel(generalTab, generalLabels, generalTexts);
        addComponentsToPanel(displayTab, displayLabels, displayTexts);
        addComponentsToPanel(prevMovementTab, prevMovementLabels, prevMovementTexts);

        tabs.add("General", generalPane); // Strings.generalTabText
        tabs.add("Main", mainTab);
        tabs.add("Display", displayPane);
        tabs.add("Preview Movement", prevMovementPane);

        generalPane.setBounds(0,0,x-50, y - 75);
        tabs.setBounds(10,10, x - 30, y - 50);

        window.add(tabs);
        window.setResizable(false);
        window.setLayout(null);
        window.setVisible(true);
    }


    public static void processList(List<String> list, ArrayList<JLabel> labels, ArrayList<JTextField> texts){
        if(list.size() > 0) {
            String tempLabel;
            String tempValue;
            String[] tempValues;

            for (int i = 1; i < list.size(); i++) {
                tempValues = list.get(i).split("=");
                tempLabel = tempValues[0];
                if (tempValues.length == 1) {
                    tempValue = "";
                } else {
                    tempValue = tempValues[1];
                }

                labels.add(new JLabel(tempLabel));
                texts.add(new JTextField(tempValue));
            }
        }
    }

    public static void setComponentBounds(int x, int x2, int labelStart, int textStart, ArrayList<JLabel> labels, ArrayList<JTextField> texts){
         int labelPos = labelStart;
         int textPos = textStart;

        for (JLabel label : labels) {
            label.setBounds(x, labelPos, 300, 20);
            labelPos += 25;
        }

        for (JTextField text : texts) {
            text.setBounds(x2, textPos, 300, 20);
            textPos += 25;
        }
        positions.add(labelPos);
    }

    public static void addComponentsToPanel(JPanel panel, ArrayList<JLabel> labels, ArrayList<JTextField> texts){
        for (JLabel label : labels) {
            panel.add(label);
        }

        for (JTextField text : texts) {
            panel.add(text);
        }
    }

    public static void processSelected(Box checkboxes){
        JCheckBox justACheckBox;

         for(int i = 0; i < checkboxes.getComponentCount(); i++){
             justACheckBox = (JCheckBox) checkboxes.getComponent(i);
             if(justACheckBox.isSelected()){
                 Game.selectedBSAs.add(justACheckBox.getText());
             }
         }
    }


}
