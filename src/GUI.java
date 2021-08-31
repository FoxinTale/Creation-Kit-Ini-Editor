import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class GUI {
     public static void MainGUI(ArrayList<String> checkboxItems){
    //public static void MakeGUI(){

        JFrame.setDefaultLookAndFeelDecorated(true);
        JFrame window = new JFrame();
        Box checkboxBox = Box.createVerticalBox();
        Box buttonBox = Box.createVerticalBox();
        JScrollPane scrollPanel = new JScrollPane(checkboxBox);
        JButton addBSAButton = new JButton("Add BSAs"); // Strings.mainButtonText

        int x = Main.screenSize[0] / 3;
        int y = Main.screenSize[1] / 2;

        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setTitle("Creation Kit INI Editor"); // Strings.windowTitle
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
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        int x = Main.screenSize[0] / 3;
        int y = Main.screenSize[1] / 2;
        window.setSize(x, y);

        window.setResizable(false);
        window.setLayout(null);
        window.setVisible(true);
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
