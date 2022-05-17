import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Main {
/*
Start off by asking "would you like to try and auto-locate Skyrim, or would you like to point this tool towards where it is?"


 */
    public static int[] screenSize = new int[2];
    public static String skyrimInstall = "";
    public static void main(String[] args){
       // File steamLibraryFile = new File(RegReader.getSteamInstallPath() + "\\steamapps\\libraryfolders.vdf");
        File steamLibraryFile = new File ("C:\\Users\\Aubrey\\Documents\\libraryfolders.vdf");
        FileOps.readValveLibrary(steamLibraryFile);
        //GetSystemLang();
        //IniFix.writeIni();// Game.findFoldersAndFiles();
            //GUI.MakeGUI();
        // Do this magic as everyone has a different display resolution. This way, it'll always be the same size no matter what resolution.
       //  String strTmp = System.getProperty("java.io.tmpdir");
     //   System.out.println("OS current temporary directory: " + strTmp);

        GraphicsDevice screen = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        screenSize[0] = screen.getDisplayMode().getWidth();
        screenSize[1] = screen.getDisplayMode().getHeight();

        File skyrim = new File(skyrimInstall);
        Game.findFoldersAndFiles(skyrim);

        File creationKitConfig = new File(skyrim.getAbsolutePath() + File.separator + "CreationKit.ini");
     //   getSystemLang();
        IniOps.processConfigFile(creationKitConfig);
        //Game.readBSAFile();
    }

    public static void getSystemLang(){
        String language = Locale.getDefault().toString();

        File langFile;
        String langFolder = System.getProperty("user.dir") + File.separator + "lang" + File.separator;

        // More languages can be added easily this way, and will be added over time.
        switch(language){
            case "en_UK":
                langFile = new File (langFolder + "en_UK.txt");
                break;
            default:
                langFile = new File (langFolder + "en_US.txt");
        }
        FileOps.readLang(langFile);
    }
}
