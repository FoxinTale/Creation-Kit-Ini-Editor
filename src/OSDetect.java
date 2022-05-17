import javax.swing.*;

public class OSDetect {
    public static String getOS() {
        String OS = System.getProperty("os.name"); // This gets the name of the current operating system.
        if (OS.equals("Windows 11") || OS.equals("Windows 10") || OS.equals("Windows 8.1") || OS.equals("Windows 7")) {
            return "Windows";
        }

        if (OS.equals("Windows Vista") || OS.equals("Windows XP")) {
            String message = "Why are you still using this computer?";
            JOptionPane.showMessageDialog(new JFrame(), message, "Outdated OS", JOptionPane.ERROR_MESSAGE);
            // For older Windows systems. Which, frankly, why are you still using?
            System.exit(0);
        }

        if (OS.equals("Linux") || OS.equals("Unix")) {
            return "Linux";
        }

        if (OS.equals("Mac")) {
            return "Mac";
        }
        return "Unknown OS";
    }
}