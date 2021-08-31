import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Main {

    public static int[] screenSize = new int[2];

    public static void main(String[] args){
        //GetSystemLang();
        //IniFix.writeIni();
      // Game.findFoldersAndFiles();
        //GUI.MakeGUI();
        // Do this magic as everyone has a different display resolution. This way, it'll always be the same size no matter what resolution.

        GraphicsDevice screen = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        screenSize[0] = screen.getDisplayMode().getWidth();
        screenSize[1] = screen.getDisplayMode().getHeight();

        File skyrim = new File("F:\\Steam\\steamapps\\common\\Skyrim Special Edition"); //Make sure to change this, as not everyone has the same install location.

        File creationKitConfig = new File(skyrim.getAbsolutePath() + File.separator + "CreationKit.ini");
        IniOps.processConfigFile(creationKitConfig);
    }

    public static void GetSystemLang(){
        String language = Locale.getDefault().toString();

        File langFile;
        String langFolder = System.getProperty("user.dir") + File.separator + "lang" + File.separator;

        // More languages can be added easily this way.
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
