import javax.swing.*;
import java.awt.*;

public class GUI {
    public static void MakeGUI(){
        GraphicsDevice screen = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        int[] dimensions = {screen.getDisplayMode().getWidth(), screen.getDisplayMode().getHeight()};

        JFrame.setDefaultLookAndFeelDecorated(true);
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setTitle(Strings.windowTitle);
        window.setSize(dimensions[0] / 4, dimensions[1] / 2);


        window.setResizable(false);
        window.setLayout(null);
        window.setVisible(true);
    }
}
